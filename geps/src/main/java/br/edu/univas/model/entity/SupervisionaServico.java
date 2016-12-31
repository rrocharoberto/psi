package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the supervisionaServicos database table.
 * 
 */
@Entity
@Table(name="supervisionaServico")
@NamedQuery(name="SupervisionaServico.findAll", query="SELECT s FROM SupervisionaServico s")
public class SupervisionaServico implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SupervisionaServicoPK id;

	//bi-directional many-to-one association to Professor
	
	//feito manualmente
	@MapsId("cpf")
	@ManyToOne(optional = false)
	@JoinColumns(value = {
	          @JoinColumn(name = "cpf", referencedColumnName = "cpf")})
	private Professor professor;

	//bi-directional many-to-one association to Servico

	//http://stackoverflow.com/questions/32625410/hibernate-foreign-key-with-a-part-of-composite-primary-key
	//feito manualmente
	@MapsId("codigoServico")
	@ManyToOne(optional = false)
	@JoinColumns(value = {
	          @JoinColumn(name = "codigoServico", referencedColumnName = "codigoServico")})
	private Servico servico;

	public SupervisionaServico() {
	}

	public SupervisionaServicoPK getId() {
		return this.id;
	}

	public void setId(SupervisionaServicoPK id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}