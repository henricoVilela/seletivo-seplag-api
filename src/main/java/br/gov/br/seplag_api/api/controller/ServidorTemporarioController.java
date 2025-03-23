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

import br.gov.br.seplag_api.api.model.ServidorTemporarioDTO;
import br.gov.br.seplag_api.domain.service.ServidorTemporarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/servidor-temporario")
public class ServidorTemporarioController {
	
	@Autowired
	private ServidorTemporarioService servidorTemporarioService;
	
	@GetMapping
    public ResponseEntity<Page<ServidorTemporarioDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "id") String ordenacao,
            @RequestParam(defaultValue = "ASC") String direcao) {
        
        Page<ServidorTemporarioDTO> servidores = servidorTemporarioService.listarTodosPaginado(pagina, tamanho, ordenacao, direcao);
        return ResponseEntity.ok(servidores);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<ServidorTemporarioDTO> buscarPorId(@PathVariable Integer id) {
        return servidorTemporarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
	@PostMapping
    public ResponseEntity<ServidorTemporarioDTO> criar(@RequestBody @Valid ServidorTemporarioDTO servidorDTO) {
		var novoServidor = servidorTemporarioService.salvar(servidorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServidor);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<ServidorTemporarioDTO> atualizar(@PathVariable Integer id, @RequestBody ServidorTemporarioDTO servidorDTO) {
        servidorDTO.id = id;
        return servidorTemporarioService.atualizar(servidorDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        if (servidorTemporarioService.excluir(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
