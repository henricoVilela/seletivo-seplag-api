package br.gov.br.seplag_api.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UnidadeDTO {
	public Integer id;
	public String nome;
	public String sigla;
	public List<Integer> enderecoIds;
}
