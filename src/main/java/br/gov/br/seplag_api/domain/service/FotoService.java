package br.gov.br.seplag_api.domain.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.gov.br.seplag_api.domain.model.FotoPessoa;
import br.gov.br.seplag_api.domain.model.Pessoa;
import br.gov.br.seplag_api.repository.FotoPessoaRepository;
import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;

@Service
public class FotoService {
	
	@Autowired
    private MinioClient minioClient;
    
    @Autowired
    private FotoPessoaRepository fotoPessoaRepository;
    
    @Value("${minio.bucket}")
    private String bucketName;
    
    public void checkBucket() {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar bucket no MinIO", e);
        }
    }
    
    public FotoPessoa uploadFoto(Integer pessoaId, MultipartFile file) {
        try {
            checkBucket();
            
            String hash = UUID.randomUUID().toString();
            
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(hash)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
            );
            
            FotoPessoa fotoPessoa = new FotoPessoa();
            fotoPessoa.setPessoa(new Pessoa(pessoaId));
            fotoPessoa.setData(LocalDateTime.now());
            fotoPessoa.setBucket(bucketName);
            fotoPessoa.setHash(hash);
            
            return fotoPessoaRepository.save(fotoPessoa);
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload da foto", e);
        }
    }
    
    public List<FotoPessoa> uploadMultiplasFotos(Integer pessoaId, List<MultipartFile> files) {
        List<FotoPessoa> fotos = new ArrayList<>();
        
        for (MultipartFile file : files) {
            fotos.add(uploadFoto(pessoaId, file));
        }
        
        return fotos;
    }
    
    public String getTemporaryLink(Integer fotoId) {
        try {
            FotoPessoa foto = fotoPessoaRepository.findById(fotoId)
                .orElseThrow(() -> new RuntimeException("Foto não encontrada"));
            
            String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .bucket(foto.getBucket())
                    .object(foto.getHash())
                    .method(Method.GET)
                    .expiry(5, TimeUnit.MINUTES)
                    .build()
            );
            
            return url;
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar link temporário", e);
        }
    }
    
    public List<FotoPessoa> listarFotosPorPessoa(Long pessoaId) {
        return fotoPessoaRepository.findByPessoaId(pessoaId);
    }
}
