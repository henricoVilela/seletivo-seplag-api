package br.gov.br.seplag_api.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "servidor_efetivo")
@PrimaryKeyJoinColumn(name = "pes_id")
public class ServidorEfetivo extends Pessoa {
	
    @Column(name = "se_matricula", length = 20)
    private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
}
