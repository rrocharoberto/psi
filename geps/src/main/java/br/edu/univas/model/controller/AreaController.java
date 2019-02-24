package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.edu.univas.model.dao.AreaDAO;
import br.edu.univas.model.dao.ServicoDAO;
import br.edu.univas.model.entity.Area;
import br.edu.univas.model.entity.Servico;
import br.edu.univas.uteis.Uteis;

@Named(value = "areaController")
@ViewScoped
public class AreaController implements Serializable {

	private static final long serialVersionUID = -639441148553504495L;

	@Inject
	transient private AreaDAO areaDAO;

	@Produces
	private List<Area> areas;
	
	private List<Area> areasFiltered;

	@Inject
	private Area currentArea;

	@Inject
	private Area newArea;

	//serviços
	@Inject
	transient private ServicoDAO servicoDAO;

	@Produces
	private List<Servico> servicos;
	
	private List<Servico> servicosFiltered;

	@Inject
	private Servico newService;
	
	@PostConstruct
	public void populateData() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Área salva com sucesso.");
		}
		
		areas = areaDAO.retrieveAll();
		currentArea = null;
		servicos = servicoDAO.retrieveAllServicos();
		newService = null;
	}

	public void onRowSelect(SelectEvent event) {
		currentArea = (Area) event.getObject();
		populateListServicos(currentArea.getCodigoArea());
		System.out.println("Area selecionada: " + currentArea.getCodigoArea());
	}
	
	public String saveNewArea() {
		Area existArea = areaDAO.retrieveArea(newArea.getCodigoArea());
		if (existArea != null) {
			Uteis.MensagemAtencao("Esse código de área ja está sendo utilizado: " + newArea.getCodigoArea());
			return null;
			
		} else {
			
			try {
				areaDAO.save(newArea);
			} catch (Exception ex) {
				ex.printStackTrace();
				Uteis.MensagemAtencao("Falha ao salvar a nova área: " + ex.getMessage());
				return null;
			}
			
			return "servicesByArea.xhtml?faces-redirect=true&save=success";
		}
	}

	//serviços
	
	public void observeAreaChanged(@Observes Area master) {
		if (master == null) {
			System.out.println("Changed the area: null");
			servicos = Collections.emptyList();
		} else {
			System.out.println("Changed the area: " + master.getCodigoArea());
			populateListServicos(master.getCodigoArea());
		}
	}

	private void populateListServicos(Integer codigoArea) {
		servicos = servicoDAO.findServiceByArea(codigoArea);
		System.out.println("populateDate: services of area: " + codigoArea + ": " + servicos.size());
	}
	
	public void prepareNewService(Area area) {
		currentArea = area;
		newService = new Servico();
	}

	public void saveNewService() {
		Servico existServico = servicoDAO.retrieveServico(newService.getCodigoServico());
		if (existServico != null) {
			Uteis.MensagemAtencao("Esse código de serviço ja está sendo utilizado: " + newService.getCodigoServico());
			
		} else {
			try {
				servicoDAO.save(newService, currentArea.getCodigoArea());
				populateListServicos(currentArea.getCodigoArea());
				Uteis.MensagemInfo("Serviço " + newService.getNome() + " cadastrado com sucesso.");
				
			} catch (Exception ex) {
				ex.printStackTrace();
				Uteis.MensagemAtencao("Falha ao salvar o novo serviço: " + ex.getMessage());
			}
		}
	}
	
	public List<Area> getAreas() {
		return areas;
	}

	public Area getCurrentArea() {
		return currentArea;
	}

	public void setCurrentArea(Area currentArea) {
		this.currentArea = currentArea;
	}

	public Area getNewArea() {
		return newArea;
	}

	public void setNewArea(Area newArea) {
		this.newArea = newArea;
	}

	public Servico getNewService() {
		return newService;
	}
	
	public void setNewService(Servico newService) {
		this.newService = newService;
	}
	
	public List<Servico> getServicos() {
		return servicos;
	}

	public List<Area> getAreasFiltered() {
		return areasFiltered;
	}

	public void setAreasFiltered(List<Area> areasFiltered) {
		this.areasFiltered = areasFiltered;
	}

	public List<Servico> getServicosFiltered() {
		return servicosFiltered;
	}

	public void setServicosFiltered(List<Servico> servicosFiltered) {
		this.servicosFiltered = servicosFiltered;
	}
	
}
