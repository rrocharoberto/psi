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
	@Column(unique=true, nullable=false, precision=131089)
	private long cpf;

	@Column(nullable=false, length=50)
	private String conselhoprofissional;

	@Column(nullable=false, length=50)
	private String profissao;

	@Column(nullable=false, length=50)
	private String registro;

	@Column(nullable=false, length=50)
	private String titulacao;

	//bi-directional many-to-one association to Evolucao
	@OneToMany(mappedBy="professor")
	private List<Evolucao> evolucoes;

	//bi-directional one-to-one association to DadosPessoais
	@OneToOne
	@JoinColumn(name="cpf", nullable=false, insertable=false, updatable=false)
	private DadosPessoais dadosPessoais;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="userName", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to SupervisionaServico
	@OneToMany(mappedBy="professor")
	private List<SupervisionaServico> supervisionaServicos;

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

	public List<SupervisionaServico> getSupervisionaServicos() {
		return this.supervisionaServicos;
	}

	public void setSupervisionaServicos(List<SupervisionaServico> supervisionaServicos) {
		this.supervisionaServicos = supervisionaServicos;
	}

	public SupervisionaServico addSupervisionaservico(SupervisionaServico supervisionaServicos) {
		getSupervisionaServicos().add(supervisionaServicos);
		supervisionaServicos.setProfessor(this);

		return supervisionaServicos;
	}

	public SupervisionaServico removeSupervisionaservico(SupervisionaServico supervisionaServicos) {
		getSupervisionaServicos().remove(supervisionaServicos);
		supervisionaServicos.setProfessor(null);

		return supervisionaServicos;
	}

}