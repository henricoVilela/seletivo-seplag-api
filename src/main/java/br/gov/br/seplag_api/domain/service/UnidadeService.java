package br.gov.br.seplag_api.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<UnidadeDTO> listarTodas() {
        return unidadeRepository.findAll().stream()
                .map(UnidadeConverter::convert)
                .collect(Collectors.toList());
    }

    public Optional<UnidadeDTO> buscarPorId(Integer id) {
        return unidadeRepository.findById(id)
                .map(UnidadeConverter::convert);
    }

    @Transactional
    public UnidadeDTO salvar(UnidadeDTO unidadeDTO) {
        Unidade unidade = UnidadeConverter.convert(unidadeDTO);
        unidade = unidadeRepository.save(unidade);
        
        return UnidadeConverter.convert(unidade);
    }

    @Transactional
    public Optional<UnidadeDTO> atualizar(UnidadeDTO unidadeDTO) {
        if (!unidadeRepository.existsById(unidadeDTO.id)) {
            return Optional.empty();
        }
        Unidade unidade = UnidadeConverter.convert(unidadeDTO);
        
        if (unidadeDTO.enderecos != null && !unidadeDTO.enderecos.isEmpty()) {
        	var enderecosIds = unidadeDTO.enderecos.stream()
        		.map(e -> e.id)
        		.toList();
        	
            List<Endereco> enderecos = enderecoRepository.findAllById(enderecosIds);
            unidade.setEnderecos(enderecos);
        }
        
        unidade = unidadeRepository.save(unidade);
        return Optional.of(UnidadeConverter.convert(unidade));
    }

    @Transactional
    public boolean excluir(Long id) {
        if (!unidadeRepository.existsById(id)) {
            return false;
        }
        unidadeRepository.deleteById(id);
        return true;
    }
}
