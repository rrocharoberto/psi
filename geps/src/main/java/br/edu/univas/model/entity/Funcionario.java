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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=20)
	private String matricula;

	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;

	@Column(nullable=false, length=50)
	private String nome;

	//bi-directional one-to-one association to Usuario
	@OneToOne
	@JoinColumn(name="matricula", nullable=false, insertable=false, updatable=false)
	private Usuario usuario;

	public Funcionario() {
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getDataAdmissao() {
		return this.dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}