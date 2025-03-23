package br.gov.br.seplag_api.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.br.seplag_api.api.model.converter.LocalDateDeserializer;
import br.gov.br.seplag_api.api.model.converter.LocalDateSerializer;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ServidorTemporarioDTO extends PessoaDto {
	public Integer id;
	
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate dataAdmissao;
    
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate dataDemissao;
    
}
