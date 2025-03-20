package br.gov.br.seplag_api.api.model.converter;

import br.gov.br.seplag_api.api.model.CidadeDto;
import br.gov.br.seplag_api.api.model.EnderecoDto;
import br.gov.br.seplag_api.domain.model.Endereco;

public class EnderecoConverter {

	public static EnderecoDto convert(Endereco origem) {
		if (origem == null) {
			return null;
		}
		
		EnderecoDto destino = new EnderecoDto();
		destino.id = origem.getId();
		destino.bairro = origem.getBairro();
		destino.logradouro = origem.getLogradouro();
		destino.numero = origem.getNumero();
		
		var cidade = origem.getCidade();
		destino.cidade = new CidadeDto(cidade.getId(), cidade.getNome(), cidade.getUf());
		
		return destino;
	}
}
