package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.AreaDAO;
import br.edu.univas.model.dao.ServicoDAO;
import br.edu.univas.model.entity.Area;
import br.edu.univas.model.entity.Servico;
import br.edu.univas.uteis.Uteis;

@Named(value = "serviceController")
@ViewScoped
public class ServiceController implements Serializable {

	private static final long serialVersionUID = -4034019163844422973L;

	@Inject
	transient private ServicoDAO dao;
	
	@Inject
	transient private AreaDAO areaDAO;

	@Produces
	private List<Servico> services;

	@Inject
	private List<Area> areas;
	
	@Inject
	private Servico newService;
	
	private Integer currentArea;
	
	@PostConstruct
	public void init() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Cadastrado salvo com sucesso.");
		}
		
		currentArea = new Integer(0);
		newService = new Servico();
		areas = areaDAO.retrieveAll();
		services = dao.retrieveAllServicos();
	}
	
	public String saveNewService() {
		dao.save(newService, currentArea);
		return "novoServico.xhtml?faces-redirect=true&save=success";
	}
	
	public Integer getCurrentArea() {
		return currentArea;
	}
	
	public void setCurrentArea(Integer currentArea) {
		this.currentArea = currentArea;
	}

	public List<Servico> getServices() {
		return services;
	}

	public Servico getNewService() {
		return newService;
	}
	
	public void setNewService(Servico newService) {
		this.newService = newService;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
}
