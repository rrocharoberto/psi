package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the servico database table.
 * 
 */
@Entity
@Table(name="servico")
@NamedQueries({
	@NamedQuery(name="Servico.findAll", 
				query="SELECT s FROM Servico s"),
	@NamedQuery(name="Servico.findByAgreement", 
				query="SELECT s FROM Servico s WHERE s.convenio.codigoConvenio = :code"),
	@NamedQuery(name="Servico.findServicosByEstagiario", 
				query="SELECT r.servico FROM RealizaServico r WHERE r.estagiario.cpf = :cpf")
})

public class Servico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer codigoServico;

	@Column(nullable=false, length=200)
	private String nome;

	//bi-directional many-to-one association to Evolucao
	@OneToMany(mappedBy="servico")
	private List<Evolucao> evolucoes;

	//bi-directional many-to-one association to RealizaServico
	@OneToMany(mappedBy="servico")
	private List<RealizaServico> realizaServicos;

	//bi-directional many-to-one association to Convenio
	@ManyToOne
	@JoinColumn(name="codigoConvenio", nullable=false)
	private Convenio convenio;

	//bi-directional many-to-one association to SupervisionaServico
	@OneToMany(mappedBy="servico")
	private List<SupervisionaServico> supervisionaServicos;

	public Servico() {
	}

	public Integer getCodigoServico() {
		return this.codigoServico;
	}

	public void setCodigoServico(Integer codigoservico) {
		this.codigoServico = codigoservico;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Evolucao> getEvolucoes() {
		return this.evolucoes;
	}

	public void setEvolucoes(List<Evolucao> evolucoes) {
		this.evolucoes = evolucoes;
	}

	public Evolucao addEvolucoe(Evolucao evolucoe) {
		getEvolucoes().add(evolucoe);
		evolucoe.setServico(this);

		return evolucoe;
	}

	public Evolucao removeEvolucoe(Evolucao evolucoe) {
		getEvolucoes().remove(evolucoe);
		evolucoe.setServico(null);

		return evolucoe;
	}

	public List<RealizaServico> getRealizaServicos() {
		return this.realizaServicos;
	}

	public void setRealizaServicos(List<RealizaServico> realizaservicos) {
		this.realizaServicos = realizaservicos;
	}

	public RealizaServico addRealizaservico(RealizaServico realizaservico) {
		getRealizaServicos().add(realizaservico);
		realizaservico.setServico(this);

		return realizaservico;
	}

	public RealizaServico removeRealizaservico(RealizaServico realizaservico) {
		getRealizaServicos().remove(realizaservico);
		realizaservico.setServico(null);

		return realizaservico;
	}

	public Convenio getConvenio() {
		return this.convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public List<SupervisionaServico> getSupervisionaServicos() {
		return this.supervisionaServicos;
	}

	public void setSupervisionaServicos(List<SupervisionaServico> supervisionaServicos) {
		this.supervisionaServicos = supervisionaServicos;
	}

	public SupervisionaServico addSupervisionaservico(SupervisionaServico supervisionaServicos) {
		getSupervisionaServicos().add(supervisionaServicos);
		supervisionaServicos.setServico(this);

		return supervisionaServicos;
	}

	public SupervisionaServico removeSupervisionaservico(SupervisionaServico supervisionaServicos) {
		getSupervisionaServicos().remove(supervisionaServicos);
		supervisionaServicos.setServico(null);

		return supervisionaServicos;
	}

}