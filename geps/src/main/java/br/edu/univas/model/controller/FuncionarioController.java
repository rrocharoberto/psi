package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.FuncionarioDAO;
import br.edu.univas.model.entity.Funcionario;

@Named(value = "funcionarioController")
@ViewScoped
public class FuncionarioController implements Serializable {
	
	private static final long serialVersionUID = -6911351012325834678L;

	@Inject
	Funcionario funcionario;

	@Inject
	transient private FuncionarioDAO dao;
	
	@Produces
	private List<Funcionario> funcionarios;
	
	@PostConstruct
	public void reset() {
		funcionario = new Funcionario();
		funcionarios = dao.retrieveAll();
	}
//
//	public void editar(Funcionario funcionario) {
//		this.funcionario = funcionario;
//	}
//
//	public void atualizarFuncionario() {
//		dao.update(funcionario);
//	}

	public Date getNow() {
		return new Date();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
}