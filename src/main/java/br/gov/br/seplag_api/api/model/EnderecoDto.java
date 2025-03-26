package br.gov.br.seplag_api.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EnderecoDto {

	public Integer id;
	
    public String tipoLogradouro;
    
    public String logradouro;
    
    public Integer numero;
    
    @NotBlank(message = "não pode estar em branco")
    @Size(min = 3, max = 100, message = "O bairro deve ter entre {min} e {max} caracteres")
    public String bairro;
    
    @NotNull(message = "não pode estar em branco")
    public CidadeDto cidade;
    
}
