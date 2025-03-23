package br.gov.br.seplag_api.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.gov.br.seplag_api.domain.model.FotoPessoa;
import br.gov.br.seplag_api.domain.service.FotoService;

@RestController
@RequestMapping("/fotos")
public class FotoController {

	@Autowired
    private FotoService fotoService;
    
    @PostMapping("/upload/{pessoaId}")
    public ResponseEntity<?> uploadFoto(@PathVariable Integer pessoaId, @RequestParam("file") MultipartFile file) {
        try {
            FotoPessoa foto = fotoService.uploadFoto(pessoaId, file);
            return ResponseEntity.ok(foto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PostMapping("/upload-multiplos/{pessoaId}")
    public ResponseEntity<?> uploadMultiplasFotos(@PathVariable Integer pessoaId, @RequestParam("files") List<MultipartFile> files) {
        try {
            List<FotoPessoa> fotos = fotoService.uploadMultiplasFotos(pessoaId, files);
            return ResponseEntity.ok(fotos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @GetMapping("/link/{fotoId}")
    public ResponseEntity<?> getTemporaryLink(@PathVariable Integer fotoId) {
        try {
            String url = fotoService.getTemporaryLink(fotoId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("url", url);
            response.put("expiracao", "5 minutos");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<?> listarFotosPorPessoa(@PathVariable Long pessoaId) {
        try {
            List<FotoPessoa> fotos = fotoService.listarFotosPorPessoa(pessoaId);
            return ResponseEntity.ok(fotos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @GetMapping("/pessoa/{pessoaId}/links")
    public ResponseEntity<?> listarLinksTemporariosPorPessoa(@PathVariable Long pessoaId) {
        try {
            List<FotoPessoa> fotos = fotoService.listarFotosPorPessoa(pessoaId);
            
            List<Map<String, Object>> resultados = fotos.stream().map(foto -> {
                Map<String, Object> result = new HashMap<>();
                result.put("id", foto.getId());
                result.put("data", foto.getData());
                result.put("url", fotoService.getTemporaryLink(foto.getId()));
                return result;
            }).collect(Collectors.toList());
            
            return ResponseEntity.ok(resultados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
