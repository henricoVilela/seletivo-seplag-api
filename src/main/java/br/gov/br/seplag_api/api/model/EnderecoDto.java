package br.gov.br.seplag_api.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EnderecoDto {

	public Integer id;
    public String tipoLogradouro;
    public String logradouro;
    public Integer numero;
    public String bairro;
    public CidadeDto cidade;
    
}
