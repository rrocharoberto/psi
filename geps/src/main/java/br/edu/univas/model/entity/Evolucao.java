package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evolucao database table.
 * 
 */
@Entity
@Table(name="evolucao")

@NamedQueries({
	@NamedQuery(name="Evolucao.findAll", query="SELECT e FROM Evolucao e"),
	@NamedQuery(name="Evolucao.findByProntuario", 
		query="SELECT e FROM Evolucao e "
				+ "WHERE e.registro.paciente.numeroProntuario = :prontuario "
				+ "order by e.id.data")
})

public class Evolucao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EvolucaoPK id;

	@Column(nullable=false, length=500)
	private String descricao;

	@Column(length=500)
	private String descricaoAvaliacao;

	@Column(nullable=false)
	private Boolean validado;

	//bi-directional many-to-one association to Estagiario
	@ManyToOne
	@JoinColumn(name="estagiario1", nullable=false)
	private Estagiario estagiario;

	//bi-directional many-to-one association to Professor
	@ManyToOne
	@JoinColumn(name="professor")
	private Professor professor;

	//bi-directional many-to-one association to Registro
	@ManyToOne
	@JoinColumn(name="numeroprontuario", nullable=false, insertable=false, updatable=false)
	private Registro registro;

	//bi-directional many-to-one association to Servico
	@ManyToOne
	@JoinColumn(name="codigoservico", nullable=false, insertable=false, updatable=false)
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

	public String getDescricaoAvaliacao() {
		return this.descricaoAvaliacao;
	}

	public void setDescricaoAvaliacao(String descricaoAvaliacao) {
		this.descricaoAvaliacao = descricaoAvaliacao;
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

	public Registro getRegistro() {
		return this.registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}