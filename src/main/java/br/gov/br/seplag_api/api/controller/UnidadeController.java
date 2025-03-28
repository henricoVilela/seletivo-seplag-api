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

import br.gov.br.seplag_api.api.model.UnidadeDTO;
import br.gov.br.seplag_api.domain.service.UnidadeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/unidade")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @GetMapping
    public ResponseEntity<Page<UnidadeDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "id") String ordenacao,
            @RequestParam(defaultValue = "ASC") String direcao) {
        
        Page<UnidadeDTO> servidores = unidadeService.listarTodosPaginado(pagina, tamanho, ordenacao, direcao);
        return ResponseEntity.ok(servidores);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDTO> buscarPorId(@PathVariable Integer id) {
        return unidadeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UnidadeDTO> criar(@RequestBody @Valid UnidadeDTO unidadeDTO) {
        UnidadeDTO novaUnidade = unidadeService.salvar(unidadeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaUnidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeDTO> atualizar(@PathVariable Integer id, @RequestBody @Valid UnidadeDTO unidadeDTO) {
        unidadeDTO.id = id;
        return unidadeService.atualizar(unidadeDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        if (unidadeService.excluir(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}