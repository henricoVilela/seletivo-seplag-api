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

import br.gov.br.seplag_api.api.model.ServidorTemporarioDTO;
import br.gov.br.seplag_api.api.model.converter.ServidorConverter;
import br.gov.br.seplag_api.domain.model.Endereco;
import br.gov.br.seplag_api.domain.model.ServidorTemporario;
import br.gov.br.seplag_api.repository.EnderecoRepository;
import br.gov.br.seplag_api.repository.ServidorTemporarioRepository;

@Service
public class ServidorTemporarioService {
	
	@Autowired
    private ServidorTemporarioRepository servidorRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Page<ServidorTemporarioDTO> listarTodosPaginado(int pagina, int tamanho, String ordenacao, String direcao) {
        Sort.Direction direcaoSort = direcao.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(direcaoSort, ordenacao));
        
        return servidorRepository.findAll(pageable)
                .map(ServidorConverter::convert);
    }
    
    public List<ServidorTemporarioDTO> listarTodos() {
        return servidorRepository.findAll().stream()
                .map(ServidorConverter::convert)
                .collect(Collectors.toList());
    }

    public Optional<ServidorTemporarioDTO> buscarPorId(Integer id) {
        return servidorRepository.findById(id)
        	.map(s -> ServidorConverter.convert(s, TipoConversao.COMPLETA));
    }

    @Transactional
    public ServidorTemporarioDTO salvar(ServidorTemporarioDTO servidorDTO) {
        ServidorTemporario servidor = ServidorConverter.convert(servidorDTO);
        servidor = servidorRepository.save(servidor);
        return ServidorConverter.convert(servidor);
    }

    @Transactional
    public Optional<ServidorTemporarioDTO> atualizar(ServidorTemporarioDTO servidorDTO) {
        if (!servidorRepository.existsById(servidorDTO.id)) {
            return Optional.empty();
        }
        
        var servidor = ServidorConverter.convert(servidorDTO);
        if (servidorDTO.enderecos != null && !servidorDTO.enderecos.isEmpty()) {
        	var enderecosIds = servidorDTO.enderecos.stream()
        		.map(e -> e.id)
        		.toList();
        	
            List<Endereco> enderecos = enderecoRepository.findAllById(enderecosIds);
            servidor.setEnderecos(enderecos);
        }
        
        servidor = servidorRepository.save(servidor);
        return Optional.of(ServidorConverter.convert(servidor, TipoConversao.COMPLETA));
    }

    @Transactional
    public boolean excluir(Integer id) {
        if (!servidorRepository.existsById(id)) {
            return false;
        }
        servidorRepository.deleteById(id);
        return true;
    }
}
