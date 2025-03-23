package br.gov.br.seplag_api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.br.seplag_api.domain.model.FotoPessoa;

@Repository
public interface FotoPessoaRepository extends CrudRepository<FotoPessoa, Integer> {
	List<FotoPessoa> findByPessoaId(Integer pessoaId);
}
