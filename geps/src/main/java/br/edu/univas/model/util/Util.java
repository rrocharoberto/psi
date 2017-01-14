package br.edu.univas.model.util;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "utilController")
@SessionScoped
public class Util {

	public Long getCPFUserSession() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		return (Long) facesContext.getExternalContext().getSessionMap().get("cpfUserAuthenticated");
	}
}
