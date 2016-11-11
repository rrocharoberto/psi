package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the dadospessoais database table.
 * 
 */
@Entity
@Table(name="dadospessoais")
@NamedQuery(name="Dadospessoai.findAll", query="SELECT d FROM Dadospessoai d")
public class Dadospessoai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cpf;

	private Boolean ativo;

	private Long celular;

	@Temporal(TemporalType.DATE)
	private Date datanascimento;

	private String estadocivil;

	private String nacionalidade;

	private String naturalidade;

	private String nome;

	private Long rg;

	private String sexo;

	private Long telefone;

	private Long telefonerecado;

	private String uf;

	//bi-directional one-to-one association to Endereco
	@OneToOne(mappedBy="dadospessoais")
	private Endereco endereco;

	//bi-directional one-to-one association to Estagiario
	@OneToOne(mappedBy="dadospessoais")
	private Estagiario estagiario;

	//bi-directional one-to-one association to Funcionario
	@OneToOne(mappedBy="dadospessoais")
	private Funcionario funcionario;

	//bi-directional one-to-one association to Paciente
	@OneToOne(mappedBy="dadospessoais")
	private Paciente paciente;

	//bi-directional one-to-one association to Professor
	@OneToOne(mappedBy="dadospessoais")
	private Professor professor;

	public Dadospessoai() {
	}

	public long getCpf() {
		return this.cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Long getCelular() {
		return this.celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	public Date getDatanascimento() {
		return this.datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getEstadocivil() {
		return this.estadocivil;
	}

	public void setEstadocivil(String estadocivil) {
		this.estadocivil = estadocivil;
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

	public Long getTelefonerecado() {
		return this.telefonerecado;
	}

	public void setTelefonerecado(Long telefonerecado) {
		this.telefonerecado = telefonerecado;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}