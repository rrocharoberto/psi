package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.EstagiarioDAO;
import br.edu.univas.model.entity.Estagiario;
import br.edu.univas.model.util.Util;
import br.edu.univas.uteis.Constants;

@Named(value = "realizarAvaliacaoController")
@ViewScoped
public class RealizarAvalicaoController implements Serializable {


	@Inject
	transient private EstagiarioDAO dao;
	
	@Inject
	transient private Util util;
	
	private Estagiario estagiario;
	
	@PostConstruct
	public void init() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		estagiario = (Estagiario) sessionMap.get(Constants.ESTAGIARIO_SESSION);
	}
	
	public void onload() {
	    //doNothing
	}
	
	public void avaliar(Estagiario estagiario) {
		
	}

	public Estagiario getEstagiario() {
		return estagiario;
	}
	
}