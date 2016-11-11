package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the funcionario database table.
 * 
 */
@Entity
@NamedQuery(name="Funcionario.findAll", query="SELECT f FROM Funcionario f")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cpf;

	@Temporal(TemporalType.DATE)
	private Date dataadmissao;

	//bi-directional one-to-one association to Dadospessoai
	@OneToOne
	@JoinColumn(name="cpf")
	private Dadospessoai dadospessoais;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="username")
	private Usuario usuario;

	public Funcionario() {
	}

	public long getCpf() {
		return this.cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public Date getDataadmissao() {
		return this.dataadmissao;
	}

	public void setDataadmissao(Date dataadmissao) {
		this.dataadmissao = dataadmissao;
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