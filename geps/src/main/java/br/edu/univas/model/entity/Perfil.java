package br.edu.univas.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@Table(name="perfil")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=20)
	private String matricula;

	@Column(nullable=false, length=50)
	private String funcao;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="matricula", nullable=false, insertable=false, updatable=false)
	private Usuario usuario;

	public Perfil() {
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getFuncao() {
		return funcao;
	}
	
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}