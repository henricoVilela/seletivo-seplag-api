package br.gov.br.seplag_api.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "servidor_temporario")
@PrimaryKeyJoinColumn(name = "pes_id")
public class ServidorTemporario extends Pessoa {
	
    @Column(name = "st_data_admissao")
    private LocalDate dataAdmissao;

    @Column(name = "st_data_demissao")
    private LocalDate dataDemissao;
}