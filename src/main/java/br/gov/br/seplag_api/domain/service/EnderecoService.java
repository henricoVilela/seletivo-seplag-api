package br.gov.br.seplag_api.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.br.seplag_api.api.model.EnderecoDto;
import br.gov.br.seplag_api.api.model.LotacaoDTO;
import br.gov.br.seplag_api.api.model.PessoaDto;
import br.gov.br.seplag_api.api.model.converter.EnderecoConverter;
import br.gov.br.seplag_api.domain.exception.ResourceNotFoundException;
import br.gov.br.seplag_api.domain.model.Endereco;
import br.gov.br.seplag_api.domain.model.Pessoa;
import br.gov.br.seplag_api.repository.CidadeRepository;
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
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
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
			pessoaEndereco.add(pessoaDto);
		}
        
        return pessoaEndereco;
    }
	
	@Transactional
	public EnderecoDto salvar(EnderecoDto enderecoDTO) {
		var endereco = EnderecoConverter.convert(enderecoDTO);
		adicionaCidade(enderecoDTO, endereco);
		
		endereco = enderecoRepository.save(endereco);
		
		return EnderecoConverter.convert(endereco);
	}
	
	
	@Transactional
    public Optional<EnderecoDto> atualizar(EnderecoDto enderecoDTO) {
        if (!enderecoRepository.existsById(enderecoDTO.id)) {
            return Optional.empty();
        }
        var endereco = EnderecoConverter.convert(enderecoDTO);
        
        adicionaCidade(enderecoDTO, endereco);
		
		endereco = enderecoRepository.save(endereco);
		
        return Optional.of(EnderecoConverter.convert(endereco));
    }
	
	private void adicionaCidade(EnderecoDto enderecoDTO, Endereco endereco) {
		var cidade = cidadeRepository.findById(enderecoDTO.cidade.id).orElseThrow(
				() -> new ResourceNotFoundException("Cidade não encontrada"));
		
		endereco.setCidade(cidade);
	}

}
