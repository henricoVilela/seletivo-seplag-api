package br.gov.br.seplag_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.br.seplag_api.domain.model.Lotacao;
import br.gov.br.seplag_api.domain.model.Pessoa;
import br.gov.br.seplag_api.domain.model.Unidade;

@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Integer> {
	List<Lotacao> findByPessoa(Pessoa pessoa);
    List<Lotacao> findByUnidade(Unidade unidade);
}
