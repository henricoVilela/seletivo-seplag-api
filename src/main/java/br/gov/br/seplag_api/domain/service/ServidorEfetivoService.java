package br.gov.br.seplag_api.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.br.seplag_api.api.model.ServidorEfetivoDTO;
import br.gov.br.seplag_api.api.model.converter.ServidorConverter;
import br.gov.br.seplag_api.api.model.converter.UnidadeConverter;
import br.gov.br.seplag_api.commos.MatriculaUtils;
import br.gov.br.seplag_api.domain.exception.ResourceNotFoundException;
import br.gov.br.seplag_api.domain.model.Endereco;
import br.gov.br.seplag_api.domain.model.ServidorEfetivo;
import br.gov.br.seplag_api.repository.EnderecoRepository;
import br.gov.br.seplag_api.repository.ServidorEfetivoRepository;
import br.gov.br.seplag_api.repository.UnidadeRepository;

@Service
public class ServidorEfetivoService {
	
	@Autowired
    private ServidorEfetivoRepository servidorRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private UnidadeRepository unidadeRepository;
    
    @Autowired
    private FotoService fotoService;

    public Page<ServidorEfetivoDTO> listarTodosPaginado(int pagina, int tamanho, String ordenacao, String direcao) {
        Sort.Direction direcaoSort = direcao.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(direcaoSort, ordenacao));
        
        return servidorRepository.findAll(pageable)
                .map(ServidorConverter::convert);
    }
    

    public Optional<ServidorEfetivoDTO> buscarPorId(Integer id) {
        return servidorRepository.findById(id)
                .map(s -> ServidorConverter.convert(s, TipoConversao.COMPLETA));
    }
    
    public Page<ServidorEfetivoDTO> listarPorUnidadeId(Integer unidadeId, int pagina, int tamanho, String ordenacao, String direcao) {
    	var unidade = unidadeRepository.findById(unidadeId)
    		.orElseThrow(() -> new ResourceNotFoundException("Unidade não encontrada"));
    	
    	Sort.Direction direcaoSort = direcao.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
    	Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(direcaoSort, ordenacao));
    	
    	var unidadeDto = UnidadeConverter.convert(unidade);
    	unidadeDto.enderecos = null;
    	
    	return servidorRepository.listaServidoresPorEnderecos(unidade.getEnderecos(), pageable)
    		.map((servidor) -> {
    			var dto = ServidorConverter.convert(servidor);
    			dto.linksFotos = fotoService.getMultiTemporaryLink(servidor.getFotos());
    			dto.unidade = unidadeDto;
    			dto.idade = servidor.idade();
    			
    			dto.dataNascimento = null;
    			dto.pai = null;
    			dto.mae = null;
    			dto.matricula = null;
    			dto.sexo = null;
    			dto.id = null;
    			
    			return dto;
    		});
    }

    @Transactional
    public ServidorEfetivoDTO salvar(ServidorEfetivoDTO servidorDTO) {
    	servidorDTO.id = null;
        ServidorEfetivo servidor = ServidorConverter.convert(servidorDTO);
        servidor.setMatricula(gerarMatriculaUnica());
        
        adicionaEnderecos(servidorDTO, servidor);
        
        servidor = servidorRepository.save(servidor);
        return ServidorConverter.convert(servidor);
    }

    @Transactional
    public Optional<ServidorEfetivoDTO> atualizar(ServidorEfetivoDTO servidorDTO) {
        if (!servidorRepository.existsById(servidorDTO.id)) {
            return Optional.empty();
        }
        ServidorEfetivo servidor = ServidorConverter.convert(servidorDTO);
        adicionaEnderecos(servidorDTO, servidor);
        
        servidor = servidorRepository.save(servidor);
        
        return Optional.of(ServidorConverter.convert(servidor, TipoConversao.COMPLETA));
    }

    @Transactional
    public boolean excluir(Integer id) {
        if (!servidorRepository.existsById(id)) {
            return false;
        }
        servidorRepository.deleteById(id);
        return true;
    }
    
    public String gerarMatriculaUnica() {
        String matricula;
        do {
            matricula = MatriculaUtils.gerarMatricula();
        } while (servidorRepository.existsByMatricula(matricula)); // Verifica se a matrícula já existe
        return matricula;
    }
    
    private void adicionaEnderecos(ServidorEfetivoDTO servidorDTO, ServidorEfetivo servidor) {
    	if (servidorDTO.enderecos != null && !servidorDTO.enderecos.isEmpty()) {
        	var enderecosIds = servidorDTO.enderecos.stream()
        		.map(e -> e.id)
        		.toList();
        	
            List<Endereco> enderecos = enderecoRepository.findAllById(enderecosIds);
            servidor.setEnderecos(enderecos);
        }
    }
    
}
