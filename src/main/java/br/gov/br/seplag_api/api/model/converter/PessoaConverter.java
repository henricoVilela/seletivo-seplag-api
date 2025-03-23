package br.gov.br.seplag_api.api.model.converter;

import java.util.stream.Collectors;

import br.gov.br.seplag_api.api.model.PessoaDto;
import br.gov.br.seplag_api.domain.model.Pessoa;
import br.gov.br.seplag_api.domain.service.TipoConversao;

public class PessoaConverter {
	
	public static void convert(Pessoa pessoa, PessoaDto dto, TipoConversao tipoConversao) {
		if (pessoa == null) {
			return;
		}
		
        dto.id = pessoa.getId();
        dto.nome = pessoa.getNome();
        dto.dataNascimento = pessoa.getDataNascimento();
        dto.sexo = pessoa.getSexo();
        dto.mae = pessoa.getMae();
        dto.pai = pessoa.getPai();

        if (pessoa.getEnderecos() != null && tipoConversao.isCompleta()) {
        	dto.enderecos = pessoa.getEnderecos().stream()
        			.map(EnderecoConverter::convert)
        			.collect(Collectors.toList());
        }
        
	}
	
	public static void convert(PessoaDto dto, Pessoa pessoa) {
		if (dto == null) {
			return;
		}
		
		pessoa.setId(dto.id);
		pessoa.setNome(dto.nome);
		pessoa.setDataNascimento(dto.dataNascimento);
		pessoa.setSexo(dto.sexo);
		pessoa.setMae(dto.mae);
        pessoa.setPai(dto.pai);
    }

}
