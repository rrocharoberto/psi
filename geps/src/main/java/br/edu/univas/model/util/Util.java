package br.edu.univas.model.util;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "utilController")
@SessionScoped
public class Util {

	public String getMatriculaUserSession() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		return (String) facesContext.getExternalContext().getSessionMap().get("matriculaUserAuthenticated");
	}
}
