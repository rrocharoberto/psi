package br.edu.univas.model.util;

import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.edu.univas.model.dto.UsuarioModel;

@Named(value = "utilController")
@SessionScoped
public class Util {

	public String getMatriculaUserSession() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		UsuarioModel usuario = (UsuarioModel) sessionMap.get("usuarioAutenticado");
		
		return usuario.getUsuario();
	}
}
