package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@Table(name="paciente")
@NamedQuery(name="Paciente.findAll", query="SELECT p FROM Paciente p order by p.dataSaida desc, p.dadosPessoais.nome asc")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, precision=131089)
	private long cpf;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dataEntrada;

	@Temporal(TemporalType.DATE)
	private Date dataSaida;

	@Column(nullable=false)
	private Integer decisao;

	@Column(nullable=false)
	private Integer origem;

	//bi-directional one-to-one association to DadosPessoais
	@OneToOne
	@JoinColumn(name="cpf", nullable=false, insertable=false, updatable=false)
	private DadosPessoais dadosPessoais;

	//bi-directional one-to-one association to Prontuario
	@OneToOne(mappedBy="paciente")
	private Prontuario prontuario;

	public Paciente() {
	}

	public long getCpf() {
		return this.cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public Date getDataEntrada() {
		return this.dataEntrada;
	}

	public void setDataEntrada(Date dataentrada) {
		this.dataEntrada = dataentrada;
	}

	public Date getDataSaida() {
		return this.dataSaida;
	}

	public void setDataSaida(Date datasaida) {
		this.dataSaida = datasaida;
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

	public DadosPessoais getDadosPessoais() {
		return this.dadosPessoais;
	}

	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	public Prontuario getProntuario() {
		return this.prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

}