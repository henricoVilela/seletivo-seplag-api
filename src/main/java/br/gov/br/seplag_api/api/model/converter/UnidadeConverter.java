package br.gov.br.seplag_api.api.model.converter;

import java.util.stream.Collectors;

import br.gov.br.seplag_api.api.model.UnidadeDTO;
import br.gov.br.seplag_api.domain.model.Unidade;

public class UnidadeConverter {

	public static UnidadeDTO convert(Unidade unidade) {
		if (unidade == null) {
			return null;
		}
		
		var dto = new UnidadeDTO();
		dto.id = unidade.getId();
		dto.nome = unidade.getNome();
		dto.sigla = unidade.getSigla();
		
		if (unidade.getEnderecos() != null) {
        	dto.enderecos = unidade.getEnderecos().stream()
        			.map(EnderecoConverter::convert)
        			.collect(Collectors.toList());
        }
		
		return dto;
	}
	
	public static Unidade convert(UnidadeDTO unidadeDto) {
		if (unidadeDto == null) {
			return null;
		}
		
		var unidade = new Unidade();
		unidade.setId(unidadeDto.id);
		unidade.setNome(unidadeDto.nome);
		unidade.setSigla(unidadeDto.sigla);
		
		return unidade;
	}
}
