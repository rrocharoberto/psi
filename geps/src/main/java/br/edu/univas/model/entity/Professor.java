package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the professor database table.
 * 
 */
@Entity
@Table(name="professor")
@NamedQuery(name="Professor.findAll", query="SELECT p FROM Professor p")
public class Professor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=20)
	private String matricula;

	@Column(nullable=false, length=50)
	private String conselhoProfissional;

	@Column(nullable=false, length=50)
	private String nome;

	@Column(nullable=false, length=50)
	private String profissao;

	@Column(nullable=false, length=50)
	private String titulacao;

	//bi-directional many-to-one association to Estagiario
	@OneToMany(mappedBy="orientador")
	private List<Estagiario> estagiarios;

	//bi-directional many-to-one association to Evolucao
	@OneToMany(mappedBy="professor")
	private List<Evolucao> evolucoes;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codigoservico", nullable=false)
	private Servico servico;

	//bi-directional one-to-one association to Usuario
	@OneToOne
	@JoinColumn(name="matricula", nullable=false, insertable=false, updatable=false)
	private Usuario usuario;

	public Professor() {
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getConselhoProfissional() {
		return this.conselhoProfissional;
	}

	public void setConselhoProfissional(String conselhoProfissional) {
		this.conselhoProfissional = conselhoProfissional;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfissao() {
		return this.profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getTitulacao() {
		return this.titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public List<Estagiario> getEstagiarios() {
		return this.estagiarios;
	}

	public void setEstagiarios(List<Estagiario> estagiarios) {
		this.estagiarios = estagiarios;
	}

	public Estagiario addEstagiario(Estagiario estagiario) {
		getEstagiarios().add(estagiario);
		estagiario.setOrientador(this);

		return estagiario;
	}

	public Estagiario removeEstagiario(Estagiario estagiario) {
		getEstagiarios().remove(estagiario);
		estagiario.setOrientador(null);

		return estagiario;
	}

	public List<Evolucao> getEvolucoes() {
		return this.evolucoes;
	}

	public void setEvolucoes(List<Evolucao> evolucoes) {
		this.evolucoes = evolucoes;
	}

	public Evolucao addEvolucoe(Evolucao evolucoe) {
		getEvolucoes().add(evolucoe);
		evolucoe.setProfessor(this);

		return evolucoe;
	}

	public Evolucao removeEvolucoe(Evolucao evolucoe) {
		getEvolucoes().remove(evolucoe);
		evolucoe.setProfessor(null);

		return evolucoe;
	}

	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}