package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.edu.univas.example.uteis.Uteis;
import br.edu.univas.model.dao.AreaDAO;
import br.edu.univas.model.entity.Area;

@Named(value="areaController")
@ViewScoped
public class AreaController implements Serializable {

	private static final long serialVersionUID = -639441148553504495L;

	@Inject
	transient private AreaDAO dao;

	@Produces
	private List<Area> areas;

	@Inject
	private Area currentArea;

	@Inject
	private Area newArea;

	@PostConstruct
	public void populateData() {
		areas = dao.retrieveAll();
		
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Cadastrado salvo com sucesso.");
		}
	}

	@Inject
	private Event<Area> areaChangeEvt;

	private void fireAreaChange() {
		areaChangeEvt.fire(currentArea);
	}

	public void onRowSelect(SelectEvent event) {
		currentArea = (Area) event.getObject();
		System.out.println("Area selecionada: " + currentArea.getCodigoArea());
		fireAreaChange();
	}
	
	public String saveNewArea() {
		dao.save(newArea);
		
		return "servicesByArea.xhtml?faces-redirect=true&save=success";
	}

	
}
