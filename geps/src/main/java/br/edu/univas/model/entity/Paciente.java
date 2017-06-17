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

@NamedQueries({
	@NamedQuery(name="Paciente.findAll", query="SELECT p FROM Paciente p order by p.dataSaida desc, p.dadosPessoais.nome asc"),
	@NamedQuery(name="Paciente.findPacientesByEstagiario", 
				query="SELECT p FROM Paciente p WHERE p.estagiario.matricula = :matricula"),
	@NamedQuery(name="Paciente.findPacientesWithoutAcompanhamento", 
				query="SELECT p FROM Paciente p WHERE p.estagiario is null")
})

public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="paciente_seq")
	@SequenceGenerator(name="paciente_seq", sequenceName="paciente_seq")
	@Column(unique=true, nullable=false, precision=131089)
	private long numeroProntuario;

	@Column(nullable=false)
	private Boolean ativo;

	@Column(length=500)
	private String comentarios;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dataEntrada;

	@Temporal(TemporalType.DATE)
	private Date dataSaida;

	@Column(length=500)
	private String decisao;

	@Column(length=200)
	private String motivoSaida;

	@Column(length=500)
	private String origem;

	//bi-directional one-to-one association to DadosPessoais
	@OneToOne(mappedBy="paciente")
	private DadosPessoais dadosPessoais;

	//bi-directional one-to-one association to Endereco
	@OneToOne(mappedBy="paciente")
	private Endereco endereco;

	//bi-directional many-to-one association to Estagiario
	@ManyToOne
	@JoinColumn(name="matricula", nullable=true)
	private Estagiario estagiario;

	//bi-directional one-to-one association to Registro
	@OneToOne(mappedBy="paciente")
	private Registro registro;

	public Paciente() {
	}

	public long getNumeroProntuario() {
		return this.numeroProntuario;
	}

	public void setNumeroProntuario(long numeroProntuario) {
		this.numeroProntuario = numeroProntuario;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getDataEntrada() {
		return this.dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return this.dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getDecisao() {
		return this.decisao;
	}

	public void setDecisao(String decisao) {
		this.decisao = decisao;
	}

	public String getMotivoSaida() {
		return this.motivoSaida;
	}

	public void setMotivoSaida(String motivoSaida) {
		this.motivoSaida = motivoSaida;
	}

	public String getOrigem() {
		return this.origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public DadosPessoais getDadosPessoais() {
		return this.dadosPessoais;
	}

	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Estagiario getEstagiario() {
		return this.estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public Registro getRegistro() {
		return this.registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

}