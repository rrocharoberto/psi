package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.PacienteDAO;
import br.edu.univas.model.entity.Paciente;

@Named(value = "pacienteController")
@ViewScoped
public class PacienteController implements Serializable {
	
	private static final long serialVersionUID = -5587018526982444175L;

	@Inject
	Paciente currentPaciente;

	@Inject
	transient private PacienteDAO dao;
	
	public void reset() {
		currentPaciente = new Paciente();
	}

	public void editar(Paciente paciente) {
		this.currentPaciente = paciente;
		this.minFirstDate = currentPaciente.getDadosPessoais().getDataNascimento();
		this.minLastDate = currentPaciente.getDataEntrada();
		System.out.println("minFirstDate em editar: " + minFirstDate);
	}

	public void atualizarPaciente() {
		dao.update(currentPaciente);
	}

	public void inativarPaciente(Paciente paciente) {
		dao.inativate(paciente.getCpf());
	}

	public Paciente getCurrentPaciente() {
		return currentPaciente;
	}
	
	public void setCurrentPaciente(Paciente currentPaciente) {
		this.currentPaciente = currentPaciente;
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