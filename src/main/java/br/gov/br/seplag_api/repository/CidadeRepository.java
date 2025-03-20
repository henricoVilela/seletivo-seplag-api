package br.gov.br.seplag_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.br.seplag_api.domain.model.Cidade;

@Repository
public interface CidadeRepository extends CrudRepository<Cidade, Integer> {

}
