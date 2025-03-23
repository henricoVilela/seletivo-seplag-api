package br.gov.br.seplag_api.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.gov.br.seplag_api.api.model.EnderecoDto;
import br.gov.br.seplag_api.api.model.LotacaoDTO;
import br.gov.br.seplag_api.api.model.PessoaDto;
import br.gov.br.seplag_api.api.model.converter.EnderecoConverter;
import br.gov.br.seplag_api.domain.model.Pessoa;
import br.gov.br.seplag_api.repository.EnderecoRepository;
import br.gov.br.seplag_api.repository.PessoaRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LotacaoService lotacaoService;
	
	public Page<EnderecoDto> listarTodosPaginado(int pagina, int tamanho, String ordenacao, String direcao) {
        Sort.Direction direcaoSort = direcao.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(direcaoSort, ordenacao));
        
        return enderecoRepository.findAll(pageable).map(EnderecoConverter::convert);
    }
	
	public List<PessoaDto> buscarPorNome(String nome) {
        List<Pessoa> pessoas = pessoaRepository.findByNomeContainingIgnoreCase(nome);

        List<PessoaDto> pessoaEndereco = new ArrayList<>();
        
        for (Pessoa pessoa : pessoas) {
        	var pessoaDto = new PessoaDto(pessoa.getId(), pessoa.getNome());
        	
			var lotacoes = lotacaoService.buscarPorPessoa(pessoa.getId());
			lotacoes.forEach(LotacaoDTO::removeDadosPessoa);
			
			
			pessoaDto.lotacoes = lotacoes;
			/*List<EnderecoDto> enderecos = new ArrayList<>();
			for (LotacaoDTO lotacao : lotacoes) {
				enderecos.addAll(lotacao.unidade.enderecos);
			}
			
			pessoaDto.enderecos = enderecos;*/
			
			pessoaEndereco.add(pessoaDto);
		}
        
        return pessoaEndereco;
    }

}
