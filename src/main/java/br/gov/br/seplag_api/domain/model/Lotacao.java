package br.gov.br.seplag_api.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lotacao")
public class Lotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "unid_id", nullable = false)
    private Unidade unidade;

    @Column(name = "lot_data_lotacao")
    private LocalDate dataLotacao;

    @Column(name = "lot_data_remocao")
    private LocalDate dataRemocao;

    @Column(name = "lot_portaria", length = 100)
    private String portaria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public LocalDate getDataLotacao() {
		return dataLotacao;
	}

	public void setDataLotacao(LocalDate dataLotacao) {
		this.dataLotacao = dataLotacao;
	}

	public LocalDate getDataRemocao() {
		return dataRemocao;
	}

	public void setDataRemocao(LocalDate dataRemocao) {
		this.dataRemocao = dataRemocao;
	}

	public String getPortaria() {
		return portaria;
	}

	public void setPortaria(String portaria) {
		this.portaria = portaria;
	}
    
}
