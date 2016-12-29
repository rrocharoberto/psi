package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the convenio database table.
 * 
 */
@Entity
@Table(name="convenio")
@NamedQuery(name="Convenio.findAll", query="SELECT c FROM Convenio c")
public class Convenio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer codigo;

	@Column(nullable=false, length=50)
	private String nome;

	//bi-directional many-to-one association to Servico
	@OneToMany(mappedBy="convenio")
	private List<Servico> servicos;

	public Convenio() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Servico addServico(Servico servico) {
		getServicos().add(servico);
		servico.setConvenio(this);

		return servico;
	}

	public Servico removeServico(Servico servico) {
		getServicos().remove(servico);
		servico.setConvenio(null);

		return servico;
	}

}