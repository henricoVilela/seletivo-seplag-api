package br.gov.br.seplag_api.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.br.seplag_api.api.model.UnidadeDTO;
import br.gov.br.seplag_api.api.model.converter.UnidadeConverter;
import br.gov.br.seplag_api.domain.model.Endereco;
import br.gov.br.seplag_api.domain.model.Unidade;
import br.gov.br.seplag_api.repository.EnderecoRepository;
import br.gov.br.seplag_api.repository.UnidadeRepository;

@Service
public class UnidadeService {

	@Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    public Page<UnidadeDTO> listarTodosPaginado(int pagina, int tamanho, String ordenacao, String direcao) {
        Sort.Direction direcaoSort = direcao.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(direcaoSort, ordenacao));
        
        return unidadeRepository.findAll(pageable)
                .map(UnidadeConverter::convert);
    }

    public Optional<UnidadeDTO> buscarPorId(Integer id) {
        return unidadeRepository.findById(id)
                .map(UnidadeConverter::convert);
    }

    @Transactional
    public UnidadeDTO salvar(UnidadeDTO unidadeDTO) {
        Unidade unidade = UnidadeConverter.convert(unidadeDTO);
        adicionaEnderecos(unidadeDTO, unidade);
        unidade = unidadeRepository.save(unidade);
        
        return UnidadeConverter.convert(unidade);
    }

    @Transactional
    public Optional<UnidadeDTO> atualizar(UnidadeDTO unidadeDTO) {
        if (!unidadeRepository.existsById(unidadeDTO.id)) {
            return Optional.empty();
        }
        
        Unidade unidade = UnidadeConverter.convert(unidadeDTO);
        adicionaEnderecos(unidadeDTO, unidade);
        unidade = unidadeRepository.save(unidade);
        
        return Optional.of(UnidadeConverter.convert(unidade));
    }

    @Transactional
    public boolean excluir(Integer id) {
        if (!unidadeRepository.existsById(id)) {
            return false;
        }
        unidadeRepository.deleteById(id);
        return true;
    }
    
    private void adicionaEnderecos(UnidadeDTO unidadeDTO, Unidade unidade) {
    	if (unidadeDTO.enderecos != null && !unidadeDTO.enderecos.isEmpty()) {
        	var enderecosIds = unidadeDTO.enderecos.stream()
        		.map(e -> e.id)
        		.toList();
        	
            List<Endereco> enderecos = enderecoRepository.findAllById(enderecosIds);
            unidade.setEnderecos(enderecos);
        }
    }
}
