package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the servico database table.
 * 
 */
@Entity
@Table(name="servico")
@NamedQueries({
	@NamedQuery(name="Servico.findAll", query="SELECT s FROM Servico s"),
	@NamedQuery(name="Servico.findByAgreement", query="SELECT s FROM Servico s WHERE s.convenio.codigo = :code")
})

public class Servico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer codigoServico;

	@Column(nullable=false, length=200)
	private String nome;

	//bi-directional many-to-one association to Convenio
	@ManyToOne
	@JoinColumn(name="codigoconvenio", nullable=false)
	private Convenio convenio;

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

	public Convenio getConvenio() {
		return this.convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

}