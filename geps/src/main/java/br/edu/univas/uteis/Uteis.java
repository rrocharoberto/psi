package br.edu.univas.uteis;

import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

public class Uteis {

	public static final String UPLOAD_DIR = "/uploads/";

	@Produces
	@RequestScoped
	public EntityManager createEntityManager(){
				
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpServletRequest request  = (HttpServletRequest)externalContext.getRequest();
		
		return (EntityManager)request.getAttribute("entityManager");
	}
	
	//MOSTRAR MENSAGEM
	public static void Mensagem(String mensagem){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		facesContext.addMessage(null, new FacesMessage("Alerta",mensagem));
	}

	//MOSTRAR MENSAGEM
	public static void MensagemAtencao(String mensagem){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
	}
	
	//MOSTRAR MENSAGEM
	public static void MensagemInfo(String mensagem){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}

	public static String generateFileNameByPaciente(String numeroProntuario, String fileName) {
		return UPLOAD_DIR
				+ "prontuario_"
				+ numeroProntuario
				+ "_"
				+ fileName;
	}

	public static int calcAge(Date dataNascimento) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataNascimento);
		int year = calendar.get(Calendar.YEAR);
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) - year;
	}

}
