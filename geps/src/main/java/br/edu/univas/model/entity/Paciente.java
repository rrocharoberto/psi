package br.edu.univas.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.primefaces.model.StreamedContent;

import br.edu.univas.uteis.Uteis;


/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@Table(name="paciente")

@NamedQueries({
	@NamedQuery(name="Paciente.findAll", query="SELECT p FROM Paciente p order by p.dataSaida desc, p.dadosPessoais.nome asc"),
	@NamedQuery(name="Paciente.findAllAtivos", query="SELECT p FROM Paciente p WHERE p.dataSaida is null order by p.dadosPessoais.nome asc"),
	@NamedQuery(name="Paciente.findPacientesByEstagiario", 
				query="SELECT p FROM Paciente p WHERE p.estagiario.matricula = :matricula and p.dataSaida is null"),
	@NamedQuery(name="Paciente.findPacientesWithoutAcompanhamento", 
				query="SELECT p FROM Paciente p WHERE p.estagiario is null")
})

public class Paciente implements Serializable {

	private static final long serialVersionUID = 6136849144810706184L;

	@Id
	@Column(unique=true, nullable=false, length=20)
	private String numeroProntuario;

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

	@Transient
	private StreamedContent declaracao;

	@Transient
	private StreamedContent termoConsentimento;

	public Paciente() {
	}

	public String getNumeroProntuario() {
		return this.numeroProntuario;
	}

	public void setNumeroProntuario(String numeroProntuario) {
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

	public StreamedContent getDeclaracao() {
		if (registro != null) {
			if (registro.getDeclaracaoContent() != null) {
				this.declaracao = Uteis.createStream(this.getNumeroProntuario(), "Declaracao", registro.getDeclaracaoContent());
			}
		}
		return declaracao;
	}

	public void setDeclaracao(StreamedContent declaracao) {
		this.declaracao = declaracao;
	}

	public StreamedContent getTermoConsentimento() {
		if (registro != null) {
			if (registro.getTermoContent() != null) {
				this.termoConsentimento = Uteis.createStream(this.getNumeroProntuario(), "Termo", registro.getTermoContent());
			}
		}
		return termoConsentimento;
	}

	public void setTermoConsentimento(StreamedContent termoConsentimento) {
		this.termoConsentimento = termoConsentimento;
	}

}