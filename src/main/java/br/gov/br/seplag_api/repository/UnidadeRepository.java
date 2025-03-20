package br.gov.br.seplag_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.br.seplag_api.domain.model.Unidade;

@Repository
public interface UnidadeRepository extends CrudRepository<Unidade, Integer> {

}
