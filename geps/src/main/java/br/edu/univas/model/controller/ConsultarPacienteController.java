package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.PacienteDAO;
import br.edu.univas.model.entity.Paciente;

@Named(value = "consultarPacienteController")
@ViewScoped
public class ConsultarPacienteController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	private List<Paciente> pacientes;

	@Inject
	transient private PacienteDAO dao;

	@PostConstruct
	public void init() {

		// RETORNAR AS PESSOAS CADASTRADAS
		this.pacientes = dao.retrieveAllPacientes();
	}

	public void inativarPaciente(Paciente paciente) {

		dao.inativate(paciente.getCpf());
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

}