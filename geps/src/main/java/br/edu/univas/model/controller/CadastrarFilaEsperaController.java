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
import br.edu.univas.uteis.Constants;
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
	
	@Inject
	FilaEspera currentPaciente;

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
		filaEspera.setDesistencia(false);
		filaEsperaDAO.save(filaEspera);

		return "filaEspera.xhtml?faces-redirect=true&save=success";
	}
	
	public String desistirFilaEspera() {
		filaEspera.setDesistencia(true);
		filaEsperaDAO.update(filaEspera);
		
		return "filaEspera.xhtml?faces-redirect=true";
	}
	
	public String cadastrarPaciente(FilaEspera filaEspera) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put(Constants.FILA_ESPERA_SESSION, filaEspera);
		return "cadastrarPaciente.xhtml?faces-redirect=true";
	}
	
	public void desistir(FilaEspera filaEspera) {
		this.filaEspera = filaEspera;
	}

	public String newPaciente() {
		return "cadastrarFilaEspera.xhtml?faces-redirect=true";
	}
		
	//editar fila de espera
	
	public void editar(Long idPacienteNaFilaEspera) {
		this.currentPaciente = filaEsperaDAO.retrievePacienteById(idPacienteNaFilaEspera);
		this.minFirstDate = currentPaciente.getDataNascimento();
		this.minLastDate = currentPaciente.getDataCadastro();
	}

	public String atualizarPaciente() {
		filaEsperaDAO.update(currentPaciente);
		Uteis.MensagemInfo("Dados salvos com sucesso.");
		filaEsperaList = filaEsperaDAO.retrieveAllFilaEspera();
		return "filaEspera.xhtml?faces-redirect=true&save=success";
	}

	
	/*************************************/
	/** controle de data mínima e máxima */

	private Date minFirstDate = null;
	private Date minLastDate = new Date();

	public void firstDateChoosen() {
		minLastDate = currentPaciente.getDataCadastro();
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

	
	public FilaEspera getFilaEspera() {
		return filaEspera;
	}

	public void setFilaEspera(FilaEspera filaEspera) {
		this.filaEspera = filaEspera;
	}
	
	public List<FilaEspera> getFilaEsperaList() {
		return filaEsperaList;
	}
	
	public FilaEspera getCurrentPaciente() {
		return currentPaciente;
	}
	
	public void setCurrentPaciente(FilaEspera currentPaciente) {
		this.currentPaciente = currentPaciente;
	}
}
