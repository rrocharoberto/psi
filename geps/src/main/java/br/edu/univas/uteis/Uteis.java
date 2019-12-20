package br.edu.univas.uteis;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DefaultStreamedContent;

public class Uteis {

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

	public static DefaultStreamedContent createStream(String numeroProntuario, String fileName, byte [] fileContent) {
		
		fileName = fileName + "_" + numeroProntuario;
		InputStream input = new ByteArrayInputStream(fileContent);
		DefaultStreamedContent stream = null;
		if(fileContent == null) {
			stream = new DefaultStreamedContent();
		} else {
			stream = new DefaultStreamedContent(input);
		}
		stream.setName(fileName);
		return stream;
	}
	
	public static int calcAge(Date dataNascimento) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataNascimento);
		int year = calendar.get(Calendar.YEAR);
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) - year;
	}

}
