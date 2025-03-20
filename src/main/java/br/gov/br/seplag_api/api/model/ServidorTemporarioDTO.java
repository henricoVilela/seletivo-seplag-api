package br.gov.br.seplag_api.api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ServidorTemporarioDTO {
	public Integer id;
	public String nome;
	public LocalDate dataNascimento;
	public String sexo;
	public String mae;
	public String pai;
    public LocalDate dataAdmissao;
    public LocalDate dataDemissao;
    public List<Integer> enderecoIds;
}
