package br.gov.br.seplag_api.api.model.converter;

import java.util.stream.Collectors;

import br.gov.br.seplag_api.api.model.ServidorEfetivoDTO;
import br.gov.br.seplag_api.api.model.ServidorTemporarioDTO;
import br.gov.br.seplag_api.domain.model.ServidorEfetivo;
import br.gov.br.seplag_api.domain.model.ServidorTemporario;
import br.gov.br.seplag_api.domain.service.TipoConversao;

public class ServidorConverter {

	public static ServidorEfetivoDTO convert(ServidorEfetivo servidor) {
        return convert(servidor, TipoConversao.SIMPLES);
    }
	
	public static ServidorTemporarioDTO convert(ServidorTemporario servidor) {
        return convert(servidor, TipoConversao.SIMPLES);
    }
	
	public static ServidorEfetivoDTO convert(ServidorEfetivo servidor, TipoConversao tipoConversao) {
		if (servidor == null) {
			return null;
		}
		
		ServidorEfetivoDTO dto = new ServidorEfetivoDTO();
		PessoaConverter.convert(servidor, dto, tipoConversao);
		
        dto.matricula = servidor.getMatricula();
        
        if (servidor.getEnderecos() != null && tipoConversao.isCompleta()) {
        	dto.enderecos = servidor.getEnderecos().stream()
        			.map(EnderecoConverter::convert)
        			.collect(Collectors.toList());
        }
        
        return dto;
	}
	
	public static ServidorEfetivo convert(ServidorEfetivoDTO dto) {
        ServidorEfetivo servidor = new ServidorEfetivo();
        PessoaConverter.convert(dto, servidor);
        
        servidor.setMatricula(dto.matricula);
        return servidor;
    }
	
	public static ServidorTemporarioDTO convert(ServidorTemporario servidor, TipoConversao tipoConversao) {
		if (servidor == null) {
			return null;
		}
		
		ServidorTemporarioDTO dto = new ServidorTemporarioDTO();
		PessoaConverter.convert(servidor, dto, tipoConversao);
		
        dto.dataAdmissao = servidor.getDataAdmissao();
        dto.dataDemissao = servidor.getDataDemissao();
        
        if (servidor.getEnderecos() != null && tipoConversao.isCompleta()) {
        	dto.enderecos = servidor.getEnderecos().stream()
        		.map(EnderecoConverter::convert)
        		.collect(Collectors.toList());
        }
        
        return dto;
	}
	
	public static ServidorTemporario convert(ServidorTemporarioDTO dto) {
		ServidorTemporario servidor = new ServidorTemporario();
		PessoaConverter.convert(dto, servidor);
		
        servidor.setDataAdmissao(dto.dataAdmissao);
        servidor.setDataDemissao(dto.dataDemissao);
        
        return servidor;
    }
}
