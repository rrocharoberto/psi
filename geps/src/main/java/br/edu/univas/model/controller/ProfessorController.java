package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.ProfessorDAO;
import br.edu.univas.model.entity.Professor;

@Named(value = "professorController")
@ViewScoped
public class ProfessorController implements Serializable {

	private static final long serialVersionUID = -6240806348540143768L;

	@Inject
	Professor professor;

	@Inject
	transient private ProfessorDAO dao;
	
	public void reset() {
		professor = new Professor();
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

}