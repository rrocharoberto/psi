package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.FilaEsperaDAO;
import br.edu.univas.model.entity.FilaEspera;
import br.edu.univas.uteis.Uteis;

@Named(value = "cadastrarFilaEsperaController")
@ViewScoped
public class CadastrarFilaEsperaController implements Serializable {

	private static final long serialVersionUID = 2569574536599811497L;

	@Inject
	transient private FilaEsperaDAO filaEsperaDAO;
	
	@Inject
	FilaEspera filaEspera;
	
	@Produces
	private List<FilaEspera> filaEsperaList;

	@PostConstruct
	public void init() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Paciente salvo com sucesso na fila.");
		}
		
		filaEsperaList = filaEsperaDAO.retrieveAllFilaEspera();
	}
	
	public void onload() {
	    //doNothing
	}
	
	public String salvarFilaEspera() {
		filaEspera.setDataCadastro(new Date());
		filaEsperaDAO.save(filaEspera);

		return "filaEspera.xhtml?faces-redirect=true&save=success";
	}
	
	public String newPaciente() {
		return "cadastrarFilaEspera.xhtml?faces-redirect=true";
	}
	
	public Date getNow() {
		return new Date();
	}

	public FilaEspera getFilaEspera() {
		return filaEspera;
	}

	public void setFilaEspera(FilaEspera filaEspera) {
		this.filaEspera = filaEspera;
	}
	
	public List<FilaEspera> getFilaEsperaList() {
		return filaEsperaList;
	}
}
