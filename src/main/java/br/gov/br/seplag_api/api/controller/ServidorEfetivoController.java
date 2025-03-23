package br.gov.br.seplag_api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.br.seplag_api.api.model.ServidorEfetivoDTO;
import br.gov.br.seplag_api.domain.service.ServidorEfetivoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/servidor-efetivo")
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
	
	@GetMapping("/{id}")
    public ResponseEntity<ServidorEfetivoDTO> buscarPorId(@PathVariable Integer id) {
        return servidorEfetivoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
	@GetMapping("/unidade/{id}")
    public ResponseEntity<Page<ServidorEfetivoDTO>> listarPorUnidadeId(
    		@PathVariable Integer id,
    		@RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "id") String ordenacao,
            @RequestParam(defaultValue = "ASC") String direcao) {

        var servidores = servidorEfetivoService.listarPorUnidadeId(id, pagina, tamanho, ordenacao, direcao);
        return ResponseEntity.ok(servidores);
    }
	
	@PostMapping
    public ResponseEntity<ServidorEfetivoDTO> criar(@RequestBody @Valid ServidorEfetivoDTO servidorDTO) {
        ServidorEfetivoDTO novoServidor = servidorEfetivoService.salvar(servidorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServidor);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<ServidorEfetivoDTO> atualizar(@PathVariable Integer id, @RequestBody ServidorEfetivoDTO servidorDTO) {
        servidorDTO.id = id;
        return servidorEfetivoService.atualizar(servidorDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        if (servidorEfetivoService.excluir(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
