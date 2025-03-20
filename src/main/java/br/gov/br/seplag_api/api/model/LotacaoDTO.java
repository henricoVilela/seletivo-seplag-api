package br.gov.br.seplag_api.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LotacaoDTO {
	public Integer id;
    public PessoaDto pessoa;
    public UnidadeDTO unidade;
    public LocalDate dataLotacao;
    public LocalDate dataRemocao;
    public String portaria;
}
