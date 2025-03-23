package br.gov.br.seplag_api.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.br.seplag_api.api.model.LotacaoDTO;
import br.gov.br.seplag_api.api.model.converter.LotacaoConverter;
import br.gov.br.seplag_api.domain.model.Lotacao;
import br.gov.br.seplag_api.domain.model.Pessoa;
import br.gov.br.seplag_api.repository.LotacaoRepository;
import br.gov.br.seplag_api.repository.ServidorEfetivoRepository;
import br.gov.br.seplag_api.repository.ServidorTemporarioRepository;
import br.gov.br.seplag_api.repository.UnidadeRepository;

@Service
public class LotacaoService {
	
	@Autowired
    private LotacaoRepository lotacaoRepository;

    @Autowired
    private ServidorEfetivoRepository servidorEfetivoRepository;

    @Autowired
    private ServidorTemporarioRepository servidorTemporarioRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    public Page<LotacaoDTO> listarTodosPaginado(int pagina, int tamanho, String ordenacao, String direcao) {
        Sort.Direction direcaoSort = direcao.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(direcaoSort, ordenacao));
        
        return lotacaoRepository.findAll(pageable)
                .map(LotacaoConverter::convert);
    }

    public Optional<LotacaoDTO> buscarPorId(Integer id) {
        return lotacaoRepository.findById(id)
        		.map(LotacaoConverter::convert);
    }

    public List<LotacaoDTO> buscarPorPessoa(Integer pessoaId) {
    	
        Optional<Pessoa> pessoaOpt = servidorEfetivoRepository.findById(pessoaId).map(servidor -> (Pessoa) servidor);
        
        if (!pessoaOpt.isPresent()) {
            pessoaOpt = servidorTemporarioRepository.findById(pessoaId).map(servidor -> (Pessoa) servidor);
        }
        
        return pessoaOpt.map(pessoa -> 
            lotacaoRepository.findByPessoa(pessoa).stream()
                    .map(LotacaoConverter::convert)
                    .collect(Collectors.toList())
        ).orElse(List.of());
    }

    public List<LotacaoDTO> buscarPorUnidade(Integer unidadeId) {
        return unidadeRepository.findById(unidadeId)
                .map(unidade -> 
                    lotacaoRepository.findByUnidade(unidade).stream()
                        .map(LotacaoConverter::convert)
                        .collect(Collectors.toList())
                ).orElse(List.of());
    }

    @Transactional
    public LotacaoDTO salvar(LotacaoDTO lotacaoDTO) {
        Lotacao lotacao = LotacaoConverter.convert(lotacaoDTO);
        
        adicionaPessoaEUnidade(lotacaoDTO, lotacao);
        
        lotacao = lotacaoRepository.save(lotacao);
        return LotacaoConverter.convert(lotacao);
    }
    

    @Transactional
    public Optional<LotacaoDTO> atualizar(LotacaoDTO lotacaoDTO) {
        if (!lotacaoRepository.existsById(lotacaoDTO.id)) {
            return Optional.empty();
        }
        Lotacao lotacao = LotacaoConverter.convert(lotacaoDTO);
        
        adicionaPessoaEUnidade(lotacaoDTO, lotacao);
        
        lotacao = lotacaoRepository.save(lotacao);
        return Optional.of(LotacaoConverter.convert(lotacao));
    }

    @Transactional
    public boolean excluir(Integer id) {
        if (!lotacaoRepository.existsById(id)) {
            return false;
        }
        lotacaoRepository.deleteById(id);
        return true;
    }
    
    private void adicionaPessoaEUnidade(LotacaoDTO lotacaoDTO, Lotacao lotacao) {
    	Optional<Pessoa> pessoaOpt = servidorEfetivoRepository.findById(lotacaoDTO.pessoa.id)
            	.map(servidor -> (Pessoa) servidor);
            
        if (!pessoaOpt.isPresent()) {
            pessoaOpt = servidorTemporarioRepository.findById(lotacaoDTO.pessoa.id).map(servidor -> (Pessoa) servidor);
        }
        pessoaOpt.ifPresent(lotacao::setPessoa);

        unidadeRepository.findById(lotacaoDTO.unidade.id).ifPresent(lotacao::setUnidade);
    }
}
