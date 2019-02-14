package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	@Column(unique=true, nullable=false, length=10)
	private Long numeroProntuario;

	@Column(nullable=false, length=300)
	private String declaracao;

	@Column(nullable=false, length=300)
	private String termoConsentimento;

	//bi-directional many-to-one association to Evolucao
	@OneToMany(mappedBy="registro")
	private List<Evolucao> evolucoes;

	//bi-directional one-to-one association to Paciente
	@OneToOne
	@JoinColumn(name="numeroprontuario", nullable=false, insertable=false, updatable=false)
	private Paciente paciente;

	public Registro() {
	}

	public Long getNumeroProntuario() {
		return this.numeroProntuario;
	}

	public void setNumeroProntuario(Long numeroProntuario) {
		this.numeroProntuario = numeroProntuario;
	}

	public String getDeclaracao() {
		return this.declaracao;
	}

	public void setDeclaracao(String declaracao) {
		this.declaracao = declaracao;
	}

	public String getTermoConsentimento() {
		return this.termoConsentimento;
	}

	public void setTermoConsentimento(String termoConsentimento) {
		this.termoConsentimento = termoConsentimento;
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

}