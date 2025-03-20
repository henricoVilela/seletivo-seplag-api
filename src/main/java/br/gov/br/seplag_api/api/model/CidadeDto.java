package br.gov.br.seplag_api.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CidadeDto {

	public Integer id;
	public String nome;
	public String uf;
	
	public CidadeDto() {
		super();
	}
	
	public CidadeDto(Integer id, String nome, String uf) {
		super();
		this.id = id;
		this.nome = nome;
		this.uf = uf;
	}
	
	
}
