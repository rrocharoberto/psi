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
import br.edu.univas.uteis.Uteis;

@Named(value = "avaliacaoController")
@ViewScoped
public class AvalicaoController implements Serializable {

	private static final long serialVersionUID = -4072907444550334877L;

	@Inject
	transient private EstagiarioDAO dao;
	
	@Inject
	transient private Util util;
	
	private List<Estagiario> estagiarios;
	
	@PostConstruct
	public void init() {
		String matriculaProfessor = util.getMatriculaUserSession();
		estagiarios = dao.findByTeacher(matriculaProfessor);
		showSuccessMessage();
	}
	
	public void onload() {
	    //doNothing
	}
	
	public String avaliar(Estagiario estagiario) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put(Constants.ESTAGIARIO_SESSION, estagiario);
		return "realizarAvaliacao.xhtml?faces-redirect=true";
	}
	
	public List<Estagiario> getEstagiarios() {
		return estagiarios;
	}

	private Map<String, String> showSuccessMessage() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Avaliação cadastrada com sucesso.");
		}
		return requestParameter;
	}

}