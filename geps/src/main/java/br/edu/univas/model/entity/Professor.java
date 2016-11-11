package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the professor database table.
 * 
 */
@Entity
@NamedQuery(name="Professor.findAll", query="SELECT p FROM Professor p")
public class Professor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cpf;

	private String conselhoprofissional;

	private String profissao;

	private String registro;

	private String titulacao;

	//bi-directional one-to-one association to Dadospessoai
	@OneToOne
	@JoinColumn(name="cpf")
	private Dadospessoai dadospessoais;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="username")
	private Usuario usuario;

	public Professor() {
	}

	public long getCpf() {
		return this.cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getConselhoprofissional() {
		return this.conselhoprofissional;
	}

	public void setConselhoprofissional(String conselhoprofissional) {
		this.conselhoprofissional = conselhoprofissional;
	}

	public String getProfissao() {
		return this.profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRegistro() {
		return this.registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getTitulacao() {
		return this.titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public Dadospessoai getDadospessoais() {
		return this.dadospessoais;
	}

	public void setDadospessoais(Dadospessoai dadospessoais) {
		this.dadospessoais = dadospessoais;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}