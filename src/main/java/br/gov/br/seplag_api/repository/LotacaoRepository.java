package br.gov.br.seplag_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.br.seplag_api.domain.model.Lotacao;

@Repository
public interface LotacaoRepository extends CrudRepository<Lotacao, Integer> {

}
