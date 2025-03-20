package br.gov.br.seplag_api.api.model.converter;

import br.gov.br.seplag_api.api.model.LotacaoDTO;
import br.gov.br.seplag_api.api.model.PessoaDto;
import br.gov.br.seplag_api.domain.model.Lotacao;

public class LotacaoConverter {
	
	public static LotacaoDTO convert(Lotacao lotacao) {
        LotacaoDTO dto = new LotacaoDTO();
        dto.id = lotacao.getId();
        
        var pessoa = lotacao.getPessoa();
        dto.pessoa = new PessoaDto(pessoa.getId(), pessoa.getNome());
        dto.unidade = UnidadeConverter.convert(lotacao.getUnidade());
        dto.dataLotacao = lotacao.getDataLotacao();
        dto.dataRemocao = lotacao.getDataRemocao();
        dto.portaria = lotacao.getPortaria();
        
        return dto;
    }

	public static Lotacao convert(LotacaoDTO dto) {
        Lotacao lotacao = new Lotacao();
        lotacao.setId(dto.id);
        lotacao.setDataLotacao(dto.dataLotacao);
        lotacao.setDataRemocao(dto.dataRemocao);
        lotacao.setPortaria(dto.portaria);
        
        return lotacao;
    }
	
}
