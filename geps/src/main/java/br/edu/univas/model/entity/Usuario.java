package br.edu.univas.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")

@NamedQueries({
	@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u"),
	@NamedQuery(name = "Usuario.findUser", 
		query= "SELECT u FROM Usuario u WHERE u.matricula = :user AND u.password = :pass")
})

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=20)
	private String matricula;

	@Column(nullable=false)
	private Boolean active;

	@Column(nullable=false, length=30)
	private String email;

	@Column(nullable=false, length=15)
	private String password;

	//bi-directional one-to-many association to Perfil
	@OneToMany(mappedBy="usuario")
	private List<Perfil> perfis;

	//bi-directional one-to-one association to Estagiario
	@OneToOne(mappedBy="usuario")
	private Estagiario estagiario;

	//bi-directional one-to-one association to Funcionario
	@OneToOne(mappedBy="usuario")
	private Funcionario funcionario;

	//bi-directional one-to-one association to Professor
	@OneToOne(mappedBy="usuario")
	private Professor professor;

	public Usuario() {
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Perfil> getPerfis() {
		return perfis;
	}
	
	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Estagiario getEstagiario() {
		return this.estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}