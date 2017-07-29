package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.EstagiarioDAO;
import br.edu.univas.model.dao.PacienteDAO;
import br.edu.univas.model.entity.Estagiario;
import br.edu.univas.model.entity.Paciente;
import br.edu.univas.uteis.Uteis;

@Named(value = "pacienteController")
@ViewScoped
public class PacienteController implements Serializable {
	
	private static final long serialVersionUID = -5587018526982444175L;

	@Inject
	Paciente currentPaciente;

	@Inject
	transient private PacienteDAO dao;

	@Inject
	transient private EstagiarioDAO estagiarioDAO;
	
	@Inject
	private List<Estagiario> estagiarios;
	
	private String currentEstagiario;
	
	@PostConstruct
	public void reset() {
		currentPaciente = new Paciente();
		estagiarios = estagiarioDAO.retrieveAll();
	}

	public void editar(Long numeroProntuario) {
		this.currentPaciente = dao.retrievePaciente(numeroProntuario);
		if(currentPaciente.getEstagiario() != null) {
			this.currentEstagiario = currentPaciente.getEstagiario().getMatricula();
		}
		this.minFirstDate = currentPaciente.getDadosPessoais().getDataNascimento();
		this.minLastDate = currentPaciente.getDataEntrada();
	}

	public void atualizarPaciente() {
		setEstagiario();
		dao.update(currentPaciente);
		currentEstagiario = null; //para não aparecer o mesmo estagiário para outro paciente.
		Uteis.MensagemInfo("Dados salvos com sucesso.");
	}

	public void setEstagiario() {
		System.out.println("Chamou o setEstagiario no pacienteController: currentEstagiario" + currentEstagiario);
		Estagiario estagiario = estagiarioDAO.retrieveEstagiario(currentEstagiario);
		currentPaciente.setEstagiario(estagiario);
	}
	
	public void inativarPaciente(Paciente paciente) {
		dao.inativate(paciente.getNumeroProntuario());
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
	
	public String getCurrentEstagiario() {
		return currentEstagiario;
	}
	
	public void setCurrentEstagiario(String currentEstagiario) {
		this.currentEstagiario = currentEstagiario; //vem a matrícula do estagiário (do combo)
	}
	
	public List<Estagiario> getEstagiarios() {
		return estagiarios;
	}

}