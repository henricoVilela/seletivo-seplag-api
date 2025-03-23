package br.gov.br.seplag_api.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.br.seplag_api.api.model.converter.LocalDateDeserializer;
import br.gov.br.seplag_api.api.model.converter.LocalDateSerializer;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LotacaoDTO {
	public Integer id;
	
	@NotNull(message = "não deve ser nulo")
    public PessoaDto pessoa;
	
	@NotNull(message = "não deve ser nulo")
    public UnidadeDTO unidade;
	
	@NotNull(message = "não pode estar em branco")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate dataLotacao;
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate dataRemocao;
    
    @NotNull(message = "não pode estar em branco")
    public String portaria;
}
