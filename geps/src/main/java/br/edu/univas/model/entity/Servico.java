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
	@NamedQuery(name="Servico.findByArea", 
				query="SELECT s FROM Servico s WHERE s.area.codigoArea = :code")
})

public class Servico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer codigoServico;

	@Column(nullable=false, length=200)
	private String nome;

	//bi-directional many-to-one association to Evolucao
	@OneToMany(mappedBy="servico")
	private List<Evolucao> evolucoes;

	//bi-directional many-to-one association to Professor
	@OneToMany(mappedBy="servico")
	private List<Professor> professores;

	//bi-directional many-to-one association to Area
	@ManyToOne
	@JoinColumn(name="codigoarea", nullable=false)
	private Area area;

	public Servico() {
	}

	public Integer getCodigoServico() {
		return this.codigoServico;
	}

	public void setCodigoServico(Integer codigoServico) {
		this.codigoServico = codigoServico;
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

	public List<Professor> getProfessores() {
		return this.professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Professor addProfessore(Professor professore) {
		getProfessores().add(professore);
		professore.setServico(this);

		return professore;
	}

	public Professor removeProfessore(Professor professore) {
		getProfessores().remove(professore);
		professore.setServico(null);

		return professore;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}