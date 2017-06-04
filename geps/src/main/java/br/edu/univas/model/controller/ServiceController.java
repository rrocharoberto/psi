package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.example.uteis.Uteis;
import br.edu.univas.model.dao.ServicoDAO;
import br.edu.univas.model.entity.Area;
import br.edu.univas.model.entity.Servico;

@Named(value = "serviceController")
@ViewScoped
public class ServiceController implements Serializable {

	private static final long serialVersionUID = -4034019163844422973L;

	@Inject
	transient private ServicoDAO dao;

	@Produces
	private List<Servico> services;

	@Inject
	private Area currentArea;

	@Inject
	private Servico newService;

	private void populateData(Integer codigoArea) {
		
//			services = master.getServicos();
			services = dao.findServiceByArea(codigoArea);
			System.out.println("populateDate: services of area: " + codigoArea + ": " + services.size());
	}

	public void observeAreaChanged(@Observes Area master) {
		if (master == null) {
			System.out.println("Changed the area: null");
			services = Collections.emptyList();
		} else {
			System.out.println("Changed the area: " + master.getCodigoArea());
			populateData(master.getCodigoArea());
		}
	}

	public void saveNewService() {
		dao.save(newService, currentArea.getCodigoArea());
		populateData(currentArea.getCodigoArea());
		
		Uteis.MensagemInfo("Serviço " + newService.getNome() + " cadastrado com sucesso.");
	}
	
	public void prepareNewService(Area area) {
		currentArea = area;
		newService = new Servico();
	}
	
	public Area getCurrentArea() {
		return currentArea;
	}
	
	public void setCurrentArea(Area currentArea) {
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
}
