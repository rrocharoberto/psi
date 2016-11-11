package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u"),
	@NamedQuery(name = "Usuario.findUser", 
		query= "SELECT u FROM Usuario u WHERE u.username = :user AND u.password = :pass")
})

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private Boolean active;

	private String email;

	private String password;

	//bi-directional many-to-one association to Estagiario
	@OneToMany(mappedBy="usuario")
	private List<Estagiario> estagiarios;

	//bi-directional many-to-one association to Funcionario
	@OneToMany(mappedBy="usuario")
	private List<Funcionario> funcionarios;

	//bi-directional many-to-one association to Professor
	@OneToMany(mappedBy="usuario")
	private List<Professor> professores;

	public Usuario() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public List<Estagiario> getEstagiarios() {
		return this.estagiarios;
	}

	public void setEstagiarios(List<Estagiario> estagiarios) {
		this.estagiarios = estagiarios;
	}

	public Estagiario addEstagiario(Estagiario estagiario) {
		getEstagiarios().add(estagiario);
		estagiario.setUsuario(this);

		return estagiario;
	}

	public Estagiario removeEstagiario(Estagiario estagiario) {
		getEstagiarios().remove(estagiario);
		estagiario.setUsuario(null);

		return estagiario;
	}

	public List<Funcionario> getFuncionarios() {
		return this.funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario addFuncionario(Funcionario funcionario) {
		getFuncionarios().add(funcionario);
		funcionario.setUsuario(this);

		return funcionario;
	}

	public Funcionario removeFuncionario(Funcionario funcionario) {
		getFuncionarios().remove(funcionario);
		funcionario.setUsuario(null);

		return funcionario;
	}

	public List<Professor> getProfessores() {
		return this.professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Professor addProfessore(Professor professore) {
		getProfessores().add(professore);
		professore.setUsuario(this);

		return professore;
	}

	public Professor removeProfessore(Professor professore) {
		getProfessores().remove(professore);
		professore.setUsuario(null);

		return professore;
	}

}