package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the estagiario database table.
 * 
 */
@Entity
@Table(name="estagiario")

@NamedQueries ({
		@NamedQuery(name="Estagiario.findAll", query="SELECT e FROM Estagiario e"),
		@NamedQuery(name="Estagiario.findAllAtivos", query="SELECT e FROM Estagiario e WHERE e.dadosPessoais.ativo = true")
})

public class Estagiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, precision=131089)
	private long cpf;

	@Column(nullable=false, length=50)
	private String areaDeEstagio;

	@Column(nullable=false, length=500)
	private String comentarios;

	@Column(nullable=false, length=50)
	private String curso;

	@Temporal(TemporalType.DATE)
	private Date dataFimVigencia;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dataInicioVigencia;

	@Column(nullable=false)
	private Integer matricula;

	//bi-directional many-to-one association to Acompanha
	@OneToMany(mappedBy="estagiario")
	private List<Acompanha> acompanhamentos;

	//bi-directional one-to-one association to DadosPessoais
	@OneToOne
	@JoinColumn(name="cpf", nullable=false, insertable=false, updatable=false)
	private DadosPessoais dadosPessoais;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="userName", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to Evolucao
	@OneToMany(mappedBy="estagiario")
	private List<Evolucao> evolucoes;

	//bi-directional many-to-one association to RealizaServico
	@OneToMany(mappedBy="estagiario")
	private List<RealizaServico> realizaServicos;

	public Estagiario() {
	}

	public long getCpf() {
		return this.cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getAreaDeEstagio() {
		return this.areaDeEstagio;
	}

	public void setAreaDeEstagio(String areaDeEstagio) {
		this.areaDeEstagio = areaDeEstagio;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Date getDataFimVigencia() {
		return this.dataFimVigencia;
	}

	public void setDataFimVigencia(Date datafimvigencia) {
		this.dataFimVigencia = datafimvigencia;
	}

	public Date getDataInicioVigencia() {
		return this.dataInicioVigencia;
	}

	public void setDataInicioVigencia(Date datainiciovigencia) {
		this.dataInicioVigencia = datainiciovigencia;
	}

	public Integer getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public List<Acompanha> getAcompanhamentos() {
		return this.acompanhamentos;
	}

	public void setAcompanhamentos(List<Acompanha> acompanhamentos) {
		this.acompanhamentos = acompanhamentos;
	}

	public Acompanha addAcompanhamento(Acompanha acompanhamento) {
		getAcompanhamentos().add(acompanhamento);
		acompanhamento.setEstagiario(this);

		return acompanhamento;
	}

	public Acompanha removeAcompanhamento(Acompanha acompanhamento) {
		getAcompanhamentos().remove(acompanhamento);
		acompanhamento.setEstagiario(null);

		return acompanhamento;
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

	public List<Evolucao> getEvolucoes() {
		return this.evolucoes;
	}

	public void setEvolucoes(List<Evolucao> evolucaos) {
		this.evolucoes = evolucaos;
	}

	public Evolucao addEvolucao(Evolucao evolucao) {
		getEvolucoes().add(evolucao);
		evolucao.setEstagiario(this);

		return evolucao;
	}

	public Evolucao removeEvolucao(Evolucao evolucao) {
		getEvolucoes().remove(evolucao);
		evolucao.setEstagiario(null);

		return evolucao;
	}

	public List<RealizaServico> getRealizaServicos() {
		return this.realizaServicos;
	}

	public void setRealizaServicos(List<RealizaServico> realizaservicos) {
		this.realizaServicos = realizaservicos;
	}

	public RealizaServico addRealizaservico(RealizaServico realizaservico) {
		getRealizaServicos().add(realizaservico);
		realizaservico.setEstagiario(this);

		return realizaservico;
	}

	public RealizaServico removeRealizaservico(RealizaServico realizaservico) {
		getRealizaServicos().remove(realizaservico);
		realizaservico.setEstagiario(null);

		return realizaservico;
	}

}