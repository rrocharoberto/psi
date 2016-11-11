package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@NamedQuery(name="Paciente.findAll", query="SELECT p FROM Paciente p")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cpf;

	@Temporal(TemporalType.DATE)
	private Date dataentrada;

	@Temporal(TemporalType.DATE)
	private Date datasaida;

	private Integer decisao;

	private Integer origem;

	//bi-directional one-to-one association to Dadospessoai
	@OneToOne
	@JoinColumn(name="cpf")
	private Dadospessoai dadospessoais;

	//bi-directional many-to-one association to Prontuario
	@OneToMany(mappedBy="paciente")
	private List<Prontuario> prontuarios;

	public Paciente() {
	}

	public long getCpf() {
		return this.cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public Date getDataentrada() {
		return this.dataentrada;
	}

	public void setDataentrada(Date dataentrada) {
		this.dataentrada = dataentrada;
	}

	public Date getDatasaida() {
		return this.datasaida;
	}

	public void setDatasaida(Date datasaida) {
		this.datasaida = datasaida;
	}

	public Integer getDecisao() {
		return this.decisao;
	}

	public void setDecisao(Integer decisao) {
		this.decisao = decisao;
	}

	public Integer getOrigem() {
		return this.origem;
	}

	public void setOrigem(Integer origem) {
		this.origem = origem;
	}

	public Dadospessoai getDadospessoais() {
		return this.dadospessoais;
	}

	public void setDadospessoais(Dadospessoai dadospessoais) {
		this.dadospessoais = dadospessoais;
	}

	public List<Prontuario> getProntuarios() {
		return this.prontuarios;
	}

	public void setProntuarios(List<Prontuario> prontuarios) {
		this.prontuarios = prontuarios;
	}

	public Prontuario addProntuario(Prontuario prontuario) {
		getProntuarios().add(prontuario);
		prontuario.setPaciente(this);

		return prontuario;
	}

	public Prontuario removeProntuario(Prontuario prontuario) {
		getProntuarios().remove(prontuario);
		prontuario.setPaciente(null);

		return prontuario;
	}

}