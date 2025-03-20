package br.gov.br.seplag_api.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "unidade")
public class Unidade {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unid_id")
    private Integer id;

    @Column(name = "unid_nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "unid_sigla", length = 20)
    private String sigla;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "unidade_endereco",
        joinColumns = @JoinColumn(name = "unid_id"),
        inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "unidade")
    private List<Lotacao> lotacoes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Lotacao> getLotacoes() {
		return lotacoes;
	}

	public void setLotacoes(List<Lotacao> lotacoes) {
		this.lotacoes = lotacoes;
	}
    
}
