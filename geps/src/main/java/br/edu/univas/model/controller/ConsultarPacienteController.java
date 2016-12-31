package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Date;
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

	@Inject
	transient private Paciente currentPaciente;

	@Produces
	private List<Paciente> pacientes;

	@Inject
	transient private PacienteDAO dao;

	@PostConstruct
	public void init() {

		// RETORNAR AS PESSOAS CADASTRADAS
		this.pacientes = dao.retrieveAllPacientes();
	}

	public void editar(Paciente paciente) {
		this.currentPaciente = paciente;
		this.minFirstDate = currentPaciente.getDadosPessoais().getDataNascimento();
		this.minLastDate = currentPaciente.getDataEntrada();
		System.out.println("minFirstDate em editar: " + minFirstDate);
	}

	public void atualizarPaciente() {

		dao.update(currentPaciente);

		this.init();
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

	public Paciente getCurrentPaciente() {
		return currentPaciente;
	}

	public void setCurrentPaciente(Paciente pacienteEditando) {
		this.currentPaciente = pacienteEditando;
	}

	/*************************************/
	/** controle de data mínima e máxima */

	private Date minFirstDate = null;
	private Date minLastDate = new Date();

	public void firstDateChoosen() {
		minLastDate = currentPaciente.getDataEntrada();
		System.out.println("minLastDate updated to: " + minLastDate);
	}

	public Date getMinFirstDate() {
		return minFirstDate;
	}

	public Date getMinLastDate() {
		return minLastDate;
	}

	public Date getNow() {
		return new Date();
	}

}