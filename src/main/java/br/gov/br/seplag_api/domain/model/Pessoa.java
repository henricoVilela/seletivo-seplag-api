package br.gov.br.seplag_api.domain.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private Long id;

    @Column(name = "pes_nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "pes_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "pes_sexo", length = 1)
    private String sexo;

    @Column(name = "pes_mae", length = 200)
    private String mae;

    @Column(name = "pes_pai", length = 200)
    private String pai;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FotoPessoa> fotos;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "pessoa_endereco",
        joinColumns = @JoinColumn(name = "pes_id"),
        inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    private List<Endereco> enderecos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public List<FotoPessoa> getFotos() {
		return fotos;
	}

	public void setFotos(List<FotoPessoa> fotos) {
		this.fotos = fotos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
    
}
