package br.gov.br.seplag_api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.br.seplag_api.api.model.ServidorEfetivoDTO;
import br.gov.br.seplag_api.domain.service.ServidorEfetivoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/servidore-efetivo")
public class ServidorEfetivoController {
	
	@Autowired
    private ServidorEfetivoService servidorEfetivoService;
	
	@GetMapping
    public ResponseEntity<Page<ServidorEfetivoDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "id") String ordenacao,
            @RequestParam(defaultValue = "ASC") String direcao) {
        
        Page<ServidorEfetivoDTO> servidores = servidorEfetivoService.listarTodosPaginado(pagina, tamanho, ordenacao, direcao);
        return ResponseEntity.ok(servidores);
    }
	
	@PostMapping
    public ResponseEntity<ServidorEfetivoDTO> criar(@RequestBody @Valid ServidorEfetivoDTO servidorDTO) {
        ServidorEfetivoDTO novoServidor = servidorEfetivoService.salvar(servidorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServidor);
    }

}
