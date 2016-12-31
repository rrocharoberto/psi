package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the acompanha database table.
 * 
 */
@Entity
@Table(name="acompanha")
@NamedQuery(name="Acompanha.findAll", query="SELECT a FROM Acompanha a")
public class Acompanha implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AcompanhaPK id;

	@Column(nullable=false)
	private Boolean ativo;

	//bi-directional many-to-one association to Estagiario
	@ManyToOne
	@JoinColumn(name="cpf", nullable=false, insertable=false, updatable=false)
	private Estagiario estagiario;

	//bi-directional many-to-one association to Prontuario
	@ManyToOne
	@JoinColumn(name="numeroProntuario", nullable=false, insertable=false, updatable=false)
	private Prontuario prontuario;

	public Acompanha() {
	}

	public AcompanhaPK getId() {
		return this.id;
	}

	public void setId(AcompanhaPK id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Estagiario getEstagiario() {
		return this.estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public Prontuario getProntuario() {
		return this.prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

}