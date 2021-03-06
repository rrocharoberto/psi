package br.edu.univas.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the registro database table.
 * 
 */
@Entity
@Table(name="registro")

@NamedQueries({
	@NamedQuery(name="Registro.findAll", query="SELECT r FROM Registro r"),
	@NamedQuery(name="Registro.findRegistroByPaciente", 
				query="SELECT r FROM Registro r WHERE r.paciente.numeroProntuario = :prontuario")
})


public class Registro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=20)
	private String numeroProntuario;
	
	private boolean declaracaoOk;
	
	private boolean termoOk;

	@Column(nullable=true, length=300)
	private String declaracao;

	@Column(nullable=true, length=300)
	private String termoConsentimento;

	//bi-directional many-to-one association to Evolucao
	@OneToMany(mappedBy="registro", fetch=FetchType.LAZY)
	private List<Evolucao> evolucoes;

	//bi-directional one-to-one association to Paciente
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="numeroprontuario", nullable=false, insertable=false, updatable=false)
	private Paciente paciente;

	public Registro() {
	}

	public String getNumeroProntuario() {
		return this.numeroProntuario;
	}

	public void setNumeroProntuario(String numeroProntuario) {
		this.numeroProntuario = numeroProntuario;
	}

	public List<Evolucao> getEvolucoes() {
		return this.evolucoes;
	}

	public void setEvolucoes(List<Evolucao> evolucoes) {
		this.evolucoes = evolucoes;
	}

	public Evolucao addEvolucoe(Evolucao evolucoe) {
		getEvolucoes().add(evolucoe);
		evolucoe.setRegistro(this);

		return evolucoe;
	}

	public Evolucao removeEvolucoe(Evolucao evolucoe) {
		getEvolucoes().remove(evolucoe);
		evolucoe.setRegistro(null);

		return evolucoe;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public boolean isDeclaracaoOk() {
		return declaracaoOk;
	}

	public void setDeclaracaoOk(boolean declaracaoOk) {
		this.declaracaoOk = declaracaoOk;
	}

	public boolean isTermoOk() {
		return termoOk;
	}

	public void setTermoOk(boolean termoOk) {
		this.termoOk = termoOk;
	}

	public String getDeclaracao() {
		return declaracao;
	}

	public void setDeclaracao(String declaracao) {
		this.declaracao = declaracao;
	}

	public String getTermoConsentimento() {
		return termoConsentimento;
	}

	public void setTermoConsentimento(String termoConsentimento) {
		this.termoConsentimento = termoConsentimento;
	}

}