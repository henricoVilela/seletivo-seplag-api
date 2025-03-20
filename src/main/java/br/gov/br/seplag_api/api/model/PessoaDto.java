package br.gov.br.seplag_api.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PessoaDto {
	public Integer id;
	public String nome;
	
	public PessoaDto() {
		super();
	}
	
	public PessoaDto(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	
}
