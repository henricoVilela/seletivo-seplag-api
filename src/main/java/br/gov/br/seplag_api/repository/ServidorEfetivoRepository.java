package br.gov.br.seplag_api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.br.seplag_api.domain.model.Endereco;
import br.gov.br.seplag_api.domain.model.ServidorEfetivo;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Integer> {

	boolean existsByMatricula(String matricula);
	
	//@Query("select distinct s from ServidorEfetivo s join s.enderecos where enderecos in :enderecos")
	@Query("select s from ServidorEfetivo s where EXISTS (select 1 from s.enderecos e where e in :enderecos)")
	Page<ServidorEfetivo> listaServidoresPorEnderecos(List<Endereco> enderecos, Pageable pageable);
}
