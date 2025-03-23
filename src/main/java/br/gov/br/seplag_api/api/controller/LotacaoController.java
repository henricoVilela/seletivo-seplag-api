package br.gov.br.seplag_api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.br.seplag_api.api.model.LotacaoDTO;
import br.gov.br.seplag_api.domain.service.LotacaoService;

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
	
}
