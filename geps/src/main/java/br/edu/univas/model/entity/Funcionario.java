package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the funcionario database table.
 * 
 */
@Entity
@Table(name="funcionario")
@NamedQuery(name="Funcionario.findAll", query="SELECT f FROM Funcionario f")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, precision=131089)
	private long cpf;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dataadmissao;

	//bi-directional one-to-one association to DadosPessoais
	@OneToOne
	@JoinColumn(name="cpf", nullable=false, insertable=false, updatable=false)
	private DadosPessoais dadosPessoais;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="userName", nullable=false)
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

	public DadosPessoais getDadosPessoais() {
		return this.dadosPessoais;
	}

	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}