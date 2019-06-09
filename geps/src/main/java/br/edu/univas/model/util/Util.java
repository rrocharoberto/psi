package br.edu.univas.model.util;

import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.edu.univas.model.dto.UsuarioModel;
import br.edu.univas.uteis.Constants;
import br.edu.univas.uteis.Perfil;

@Named(value = "utilController")
@SessionScoped
public class Util {

	public String getMatriculaUserSession() {
		Map<String, Object> sessionMap = getSessionMap();
		UsuarioModel usuario = (UsuarioModel) sessionMap.get(Constants.USER_IN_SESSION);
		return usuario.getUsuario();
	}
	
	public Perfil getPerfilInSession() {
		Map<String, Object> sessionMap = getSessionMap();
		Perfil perfil = (Perfil) sessionMap.get(Constants.RULE_IN_SESSION);
		return perfil;
	}
	
	private Map<String, Object> getSessionMap() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		return sessionMap;
	}
	
}
