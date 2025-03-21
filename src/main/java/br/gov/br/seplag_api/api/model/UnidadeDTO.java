package br.gov.br.seplag_api.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UnidadeDTO {
	public Integer id;
	
	@NotBlank(message = "não pode estar em branco")
    @Size(min = 3, max = 200, message = "O nome deve ter entre {min} e {max} caracteres")
	public String nome;
	
	@NotBlank(message = "não pode estar em branco")
    @Size(min = 3, max = 20, message = "O nome deve ter entre {min} e {max} caracteres")
	public String sigla;
	
	@NotEmpty(message = "não pode estar vazio")
	public List<EnderecoDto> enderecos;
}
