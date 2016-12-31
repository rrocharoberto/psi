package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evolucao database table.
 * 
 */
@Entity
@Table(name="evolucao")
@NamedQuery(name="Evolucao.findAll", query="SELECT e FROM Evolucao e")
public class Evolucao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EvolucaoPK id;

	@Column(nullable=false, length=500)
	private String descricao;

	@Column(length=500)
	private String descricaoavaliacao;

	@Column(nullable=false)
	private Boolean validado;

	//bi-directional many-to-one association to Estagiario
	@ManyToOne
	@JoinColumn(name="cpfEstagiario", nullable=false, insertable=false, updatable=false)
	private Estagiario estagiario;

	//bi-directional many-to-one association to Professor
	@ManyToOne
	@JoinColumn(name="cpfProfessor", nullable=false)
	private Professor professor;

	//bi-directional many-to-one association to Prontuario
	@ManyToOne
	@JoinColumn(name="numeroProntuario", nullable=false, insertable=false, updatable=false)
	private Prontuario prontuario;

	//bi-directional many-to-one association to Servico
	@ManyToOne
	@JoinColumn(name="codigoServico", nullable=false, insertable=false, updatable=false)
	private Servico servico;

	public Evolucao() {
	}

	public EvolucaoPK getId() {
		return this.id;
	}

	public void setId(EvolucaoPK id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoavaliacao() {
		return this.descricaoavaliacao;
	}

	public void setDescricaoavaliacao(String descricaoavaliacao) {
		this.descricaoavaliacao = descricaoavaliacao;
	}

	public Boolean getValidado() {
		return this.validado;
	}

	public void setValidado(Boolean validado) {
		this.validado = validado;
	}

	public Estagiario getEstagiario() {
		return this.estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Prontuario getProntuario() {
		return this.prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}