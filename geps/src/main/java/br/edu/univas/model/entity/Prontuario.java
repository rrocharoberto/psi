package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the prontuario database table.
 * 
 */
@Entity
@Table(name="prontuario")

@NamedQueries({
	@NamedQuery(name="Prontuario.findAll", 
				query="SELECT p FROM Prontuario p")
})

public class Prontuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer numeroProntuario;

	@Column(length=500)
	private String comentarios;

	@Temporal(TemporalType.DATE)
	private Date dataFechamento;

	@Column(length=300)
	private String declaracao;

	@Column(length=50)
	private String motivoFechamento;

	@Column(length=300)
	private String termoConsentimento;

	//bi-directional many-to-one association to Acompanha
	@OneToMany(mappedBy="prontuario")
	private List<Acompanha> acompanhamentos;

	//bi-directional many-to-one association to Evolucao
	@OneToMany(mappedBy="prontuario")
	private List<Evolucao> evolucoes;

	//bi-directional one-to-one association to Paciente
	@OneToOne
	@JoinColumn(name="cpf", nullable=false)
	private Paciente paciente;

	public Prontuario() {
	}

	public Integer getNumeroProntuario() {
		return this.numeroProntuario;
	}

	public void setNumeroProntuario(Integer numeroprontuario) {
		this.numeroProntuario = numeroprontuario;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getDataFechamento() {
		return this.dataFechamento;
	}

	public void setDataFechamento(Date datafechamento) {
		this.dataFechamento = datafechamento;
	}

	public String getDeclaracao() {
		return this.declaracao;
	}

	public void setDeclaracao(String declaracao) {
		this.declaracao = declaracao;
	}

	public String getMotivoFechamento() {
		return this.motivoFechamento;
	}

	public void setMotivoFechamento(String motivofechamento) {
		this.motivoFechamento = motivofechamento;
	}

	public String getTermoConsentimento() {
		return this.termoConsentimento;
	}

	public void setTermoConsentimento(String termoconsentimento) {
		this.termoConsentimento = termoconsentimento;
	}

	public List<Acompanha> getAcompanhamentos() {
		return this.acompanhamentos;
	}

	public void setAcompanhamentos(List<Acompanha> acompanhamentos) {
		this.acompanhamentos = acompanhamentos;
	}

	public Acompanha addAcompanhamento(Acompanha acompanhamento) {
		getAcompanhamentos().add(acompanhamento);
		acompanhamento.setProntuario(this);

		return acompanhamento;
	}

	public Acompanha removeAcompanhamento(Acompanha acompanhamento) {
		getAcompanhamentos().remove(acompanhamento);
		acompanhamento.setProntuario(null);

		return acompanhamento;
	}

	public List<Evolucao> getEvolucoes() {
		return this.evolucoes;
	}

	public void setEvolucoes(List<Evolucao> evolucoes) {
		this.evolucoes = evolucoes;
	}

	public Evolucao addEvolucoe(Evolucao evolucoe) {
		getEvolucoes().add(evolucoe);
		evolucoe.setProntuario(this);

		return evolucoe;
	}

	public Evolucao removeEvolucoe(Evolucao evolucoe) {
		getEvolucoes().remove(evolucoe);
		evolucoe.setProntuario(null);

		return evolucoe;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}