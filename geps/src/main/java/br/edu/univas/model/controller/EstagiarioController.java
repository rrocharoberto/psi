package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.EstagiarioDAO;
import br.edu.univas.model.dao.ProfessorDAO;
import br.edu.univas.model.entity.Estagiario;
import br.edu.univas.model.entity.Professor;

@Named(value = "estagiarioController")
@ViewScoped
public class EstagiarioController implements Serializable {

	private static final long serialVersionUID = -6240806348540143768L;

	@Inject
	Estagiario estagiario;

	@Inject
	transient private EstagiarioDAO dao;

	@Inject
	transient private ProfessorDAO professorDAO;
	
	@Produces
	private List<Professor> professores;
	
	@Produces
	private List<Estagiario> estagiarios;
	
	private String currentProfessor;
	
	public void reset() {
		estagiario = new Estagiario();
		professores = professorDAO.retrieveAll();
		estagiarios = dao.retrieveAll();
	}

	public void editar(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public void atualizarEstagiario() {
		dao.update(estagiario);
	}
	
	/*************************************/
	/** controle de data mínima e máxima */

	private Date inicioVigenciaDate = new Date();

	public void firstDateChoosen() {
		inicioVigenciaDate = estagiario.getDataInicioVigencia();
		System.out.println("inicioVigenciaDate updated to: " + inicioVigenciaDate);
	}

	public Date getNow() {
		return new Date();
	}

	public Date getInicioVigenciaDate() {
		return inicioVigenciaDate;
	}

	//fim do controle de datas
	
	public Estagiario getEstagiario() {
		return estagiario;
	}
	
	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}
	
	public List<Professor> getProfessores() {
		return professores;
	}
	
	public String getCurrentProfessor() {
		return currentProfessor;
	}
	
	public void setCurrentProfessor(String currentProfessor) {
		this.currentProfessor = currentProfessor;
	}

	public void setProfessor() {
		Professor professor = professorDAO.retrieveProfessor(currentProfessor);
		estagiario.setOrientador(professor);
	}

	public List<Estagiario> getEstagiarios() {
		return estagiarios;
	}
}