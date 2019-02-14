package br.edu.univas.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.univas.uteis.EstadoCivilConverter;
import br.edu.univas.uteis.SexoConverter;
import br.edu.univas.uteis.StringUtil;


/**
 * The persistent class for the dadospessoais database table.
 * 
 */
@Entity
@Table(name="dadospessoais")

@NamedQueries({
	@NamedQuery(name="DadosPessoais.findAll", query="SELECT d FROM DadosPessoais d"),
	@NamedQuery(name="DadosPessoais.findByCPF", query="SELECT d FROM DadosPessoais d WHERE d.cpf = :cpf"), //TODO: não vai mais usar
	@NamedQuery(name="DadosPessoais.findByProntuario", query="SELECT d FROM DadosPessoais d WHERE d.numeroprontuario = :prontuario")
})

public class DadosPessoais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private Long numeroprontuario;

	@Column(precision=131089)
	private Long celular;

	@Column(precision=131089)
	private Long cpf;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dataNascimento;

	@Column(nullable=false, length=20)
	private String estadoCivil;

	@Column(length=50)
	private String nacionalidade;

	@Column(length=50)
	private String naturalidade;

	@Column(nullable=false, length=50)
	private String nome;

	@Column(precision=131089)
	private Long rg;

	@Column(nullable=false, length=1)
	private String sexo;

	@Column(precision=131089)
	private Long telefone;

	@Column(precision=131089)
	private Long telefoneRecado;

	@Column(length=30)
	private String uf;

	//bi-directional one-to-one association to Paciente
	@OneToOne
	@JoinColumn(name="numeroprontuario", nullable=false, insertable=false, updatable=false)
	private Paciente paciente;

	public DadosPessoais() {
	}

	public Long getNumeroprontuario() {
		return this.numeroprontuario;
	}

	public void setNumeroprontuario(Long numeroprontuario) {
		this.numeroprontuario = numeroprontuario;
	}

	public Long getCelular() {
		return this.celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	public Long getCpf() {
		return this.cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNacionalidade() {
		return this.nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getNaturalidade() {
		return this.naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getRg() {
		return this.rg;
	}

	public void setRg(Long rg) {
		this.rg = rg;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Long getTelefone() {
		return this.telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public Long getTelefoneRecado() {
		return this.telefoneRecado;
	}

	public void setTelefoneRecado(Long telefoneRecado) {
		this.telefoneRecado = telefoneRecado;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getCpfString() {
		return StringUtil.longToString(this.cpf, 11);
	}
	
	public String getRgString() {
		return StringUtil.longToString(this.rg, 8);
	}
	
	public String getCelularString() {
		return StringUtil.longToString(this.celular, 11);
	}
	
	public String getTelefoneString() {
		return StringUtil.longToString(this.telefone, 10);
	}
	
	public String getTelefoneRecadoString() {
		return StringUtil.longToString(this.telefoneRecado, 10);
	}
	
	public String getSexoString() {
		return SexoConverter.convert(this.sexo);
	}
	
	public String getEstadoCivilString() {
		return EstadoCivilConverter.convert(this.estadoCivil);
	}

}