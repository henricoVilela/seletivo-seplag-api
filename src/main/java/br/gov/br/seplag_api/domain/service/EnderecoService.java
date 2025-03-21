package br.gov.br.seplag_api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.gov.br.seplag_api.api.model.EnderecoDto;
import br.gov.br.seplag_api.api.model.converter.EnderecoConverter;
import br.gov.br.seplag_api.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Page<EnderecoDto> listarTodosPaginado(int pagina, int tamanho, String ordenacao, String direcao) {
        Sort.Direction direcaoSort = direcao.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(direcaoSort, ordenacao));
        
        return enderecoRepository.findAll(pageable).map(EnderecoConverter::convert);
    }

}
