package br.edu.univas.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@Table(name="paciente")

@NamedQueries({
	@NamedQuery(name="Paciente.findAll", query="SELECT p FROM Paciente p order by p.dataSaida desc, p.dadosPessoais.nome asc"),
	@NamedQuery(name="Paciente.findPacientesByEstagiario", 
				query="SELECT a.prontuario.paciente FROM Acompanha a WHERE a.estagiario.cpf = :cpf"),
	@NamedQuery(name="Paciente.findPacientesWithoutAcompanhamento", 
				query="SELECT p FROM Paciente p WHERE p.prontuario not in (SELECT a.prontuario FROM Acompanha a)")
})

public class Paciente implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#identifiers-derived-primarykeyjoincolumn
	@Id
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
	@MapsId(value="cpf")
	@JoinColumn(name = "cpf")
	private DadosPessoais dadosPessoais;

	//bi-directional one-to-one association to Prontuario
	@OneToOne(mappedBy="paciente", fetch=FetchType.EAGER)
	private Prontuario prontuario;

	public static final long MILISEGUNDOS_EM_UM_ANO = 1000L * 60L * 60L * 24L * 365L;

	public Paciente() {
	}
	
	public int getIdade() {
		long delta = System.currentTimeMillis() - dadosPessoais.getDataNascimento().getTime();
		long idade = delta / MILISEGUNDOS_EM_UM_ANO;
//		System.out.println(dadosPessoais.getNome() + " nascimento: " + dadosPessoais.getDataNascimento() + " delta:" + delta + " idade: " + idade);
		return (int) idade;
	}

	public long getCpf() {
		return cpf;
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

	@Override
	public String toString() {
		return "Paciente [cpf=" + cpf + ", prontuario=" + prontuario + "]";
	}

}