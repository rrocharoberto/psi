package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.ProfessorDAO;
import br.edu.univas.model.dao.ServicoDAO;
import br.edu.univas.model.entity.Professor;
import br.edu.univas.model.entity.Servico;

@Named(value = "professorController")
@ViewScoped
public class ProfessorController implements Serializable {

	private static final long serialVersionUID = -6240806348540143768L;

	@Inject
	Professor professor;

	@Inject
	transient private ProfessorDAO dao;
	
	@Inject
	transient private ServicoDAO serviceDAO;
	
	@Produces
	private List<Professor> professores;
	
	@Produces
	private List<Servico> services;
	
	private Integer currentService;
	
	public void reset() {
		professor = new Professor();
		professores = dao.retrieveAll();
		services = serviceDAO.retrieveAllServicos();
	}

	public void setService() {
		Servico servico = serviceDAO.retrieveServico(currentService);
		professor.setServico(servico);
	}
	
	public void editar(Professor professor) {
		this.professor = professor;
	}

	public void atualizarProfessor() {
		dao.update(professor);
	}

	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Date getNow() {
		return new Date();
	}

	public Integer getCurrentService() {
		return currentService;
	}

	public void setCurrentService(Integer currentService) {
		this.currentService = currentService;
	}

	public List<Servico> getServices() {
		return services;
	}
	
	public List<Professor> getProfessores() {
		return professores;
	}
	
}