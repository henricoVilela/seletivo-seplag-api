package br.gov.br.seplag_api.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.br.seplag_api.api.model.EnderecoDto;
import br.gov.br.seplag_api.api.model.PessoaDto;
import br.gov.br.seplag_api.domain.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping
    public ResponseEntity<Page<EnderecoDto>> listarTodos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "id") String ordenacao,
            @RequestParam(defaultValue = "ASC") String direcao) {
        
        Page<EnderecoDto> enderecos = enderecoService.listarTodosPaginado(pagina, tamanho, ordenacao, direcao);
        return ResponseEntity.ok(enderecos);
    }

	@GetMapping("/pessoa")
	public ResponseEntity<List<PessoaDto>> getEnderecoPorNomePessoa(@RequestParam String nome) {
		return ResponseEntity.ok(enderecoService.buscarPorNome(nome));
	}
}
