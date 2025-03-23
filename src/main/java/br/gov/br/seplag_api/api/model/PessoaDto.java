package br.gov.br.seplag_api.api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.br.seplag_api.api.model.converter.LocalDateDeserializer;
import br.gov.br.seplag_api.api.model.converter.LocalDateSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PessoaDto {
	public Integer id;

	@NotBlank(message = "não pode estar em branco")
    @Size(min = 3, max = 200, message = "O nome deve ter entre {min} e {max} caracteres")
	public String nome;
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@NotNull(message = "não pode estar em branco")
    public LocalDate dataNascimento;
    
    @NotBlank(message = "não pode estar em branco")
    @Size(min = 1, max = 1, message = "O nome deve ter entre {min} e {max} caracteres")
    public String sexo;
    
    @NotBlank(message = "não pode estar em branco")
    @Size(min = 3, max = 200, message = "O nome deve ter entre {min} e {max} caracteres")
    public String mae;
    
    @NotBlank(message = "não pode estar em branco")
    @Size(min = 3, max = 200, message = "O nome deve ter entre {min} e {max} caracteres")
    public String pai;
	
    @NotEmpty(message = "não pode estar vazio")
    public List<EnderecoDto> enderecos;
    
	public PessoaDto() {
		super();
	}
	
	public PessoaDto(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	
}
