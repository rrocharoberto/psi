package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.EstagiarioDAO;
import br.edu.univas.model.entity.Estagiario;

@Named(value = "estagiarioController")
@ViewScoped
public class EstagiarioController implements Serializable {

	private static final long serialVersionUID = -6240806348540143768L;

	@Inject
	Estagiario estagiario;

	@Inject
	transient private EstagiarioDAO dao;
	
	public void reset() {
		estagiario = new Estagiario();
	}

	public void editar(Estagiario estagiario) {
		this.estagiario = estagiario;
		this.minFirstDate = estagiario.getDadosPessoais().getDataNascimento();
		this.minLastDate = estagiario.getDataInicioVigencia();
		System.out.println("minFirstDate em editar: " + minFirstDate);
	}

	public void atualizarEstagiario() {
		dao.update(estagiario);
	}

	public Estagiario getEstagiario() {
		return estagiario;
	}
	
	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	/*************************************/
	/** controle de data mínima e máxima */

	private Date minFirstDate = null;
	private Date minLastDate = new Date();

	public void firstDateChoosen() {
		minLastDate = estagiario.getDataInicioVigencia();
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