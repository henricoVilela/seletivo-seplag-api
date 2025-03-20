package br.gov.br.seplag_api.api.model.converter;

import java.util.stream.Collectors;

import br.gov.br.seplag_api.api.model.ServidorEfetivoDTO;
import br.gov.br.seplag_api.domain.model.ServidorEfetivo;
import br.gov.br.seplag_api.domain.service.TipoConversao;

public class ServidorConverter {

	public static ServidorEfetivoDTO convert(ServidorEfetivo servidor) {
        return convert(servidor, TipoConversao.SIMPLES);
    }
	
	public static ServidorEfetivoDTO convert(ServidorEfetivo servidor, TipoConversao tipoConversao) {
		if (servidor == null) {
			return null;
		}
		
		ServidorEfetivoDTO dto = new ServidorEfetivoDTO();
        dto.id = servidor.getId();
        dto.nome = servidor.getNome();
        dto.dataNascimento = servidor.getDataNascimento();
        dto.sexo = servidor.getSexo();
        dto.mae = servidor.getMae();
        dto.pai = servidor.getPai();
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
        servidor.setId(dto.id);
        servidor.setNome(dto.nome);
        servidor.setDataNascimento(dto.dataNascimento);
        servidor.setSexo(dto.sexo);
        servidor.setMae(dto.mae);
        servidor.setPai(dto.pai);
        servidor.setMatricula(dto.matricula);
        
        return servidor;
    }
}
