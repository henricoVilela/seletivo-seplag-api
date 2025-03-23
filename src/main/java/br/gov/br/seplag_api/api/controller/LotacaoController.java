package br.gov.br.seplag_api.api.controller;

import java.util.List;

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

import br.gov.br.seplag_api.api.model.LotacaoDTO;
import br.gov.br.seplag_api.domain.service.LotacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/lotacao")
public class LotacaoController {

	@Autowired
	private LotacaoService lotacaoService;
	 
	@GetMapping
    public ResponseEntity<Page<LotacaoDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "id") String ordenacao,
            @RequestParam(defaultValue = "ASC") String direcao) {
        
        Page<LotacaoDTO> lotacoes = lotacaoService.listarTodosPaginado(pagina, tamanho, ordenacao, direcao);
        return ResponseEntity.ok(lotacoes);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<LotacaoDTO> buscarPorId(@PathVariable Integer id) {
        return lotacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
	@GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<LotacaoDTO>> buscarPorPessoa(@PathVariable Integer pessoaId) {
        List<LotacaoDTO> lotacoes = lotacaoService.buscarPorPessoa(pessoaId);
        return ResponseEntity.ok(lotacoes);
    }

    @GetMapping("/unidade/{unidadeId}")
    public ResponseEntity<List<LotacaoDTO>> buscarPorUnidade(@PathVariable Integer unidadeId) {
        List<LotacaoDTO> lotacoes = lotacaoService.buscarPorUnidade(unidadeId);
        return ResponseEntity.ok(lotacoes);
    }

    @PostMapping
    public ResponseEntity<LotacaoDTO> criar(@RequestBody @Valid LotacaoDTO lotacaoDTO) {
        LotacaoDTO novaLotacao = lotacaoService.salvar(lotacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaLotacao);
    }
	
    @PutMapping("/{id}")
    public ResponseEntity<LotacaoDTO> atualizar(@PathVariable Integer id, @RequestBody LotacaoDTO lotacaoDTO) {
        lotacaoDTO.id = id;
        return lotacaoService.atualizar(lotacaoDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        if (lotacaoService.excluir(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
