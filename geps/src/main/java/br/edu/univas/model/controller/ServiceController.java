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
import br.edu.univas.model.entity.Convenio;
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
	private Convenio currentConvenio;

	@Inject
	private Servico newService;

	private void populateData(Integer codigoConvenio) {
		
//			services = master.getServicos();
			services = dao.findServiceByAgreement(codigoConvenio);
			System.out.println("populateDate: services of convenio: " + codigoConvenio + ": " + services.size());
	}

	public void observeAgreementChanged(@Observes Convenio master) {
		if (master == null) {
			System.out.println("Changed the convenio: null");
			services = Collections.emptyList();
		} else {
			System.out.println("Changed the convenio: " + master.getCodigoConvenio());
			populateData(master.getCodigoConvenio());
		}
	}

	public void saveNewService() {
		dao.save(newService, currentConvenio.getCodigoConvenio());
		populateData(currentConvenio.getCodigoConvenio());
		
		Uteis.MensagemInfo("Servi�o " + newService.getNome() + " cadastrado com sucesso.");
	}
	
	public void prepareNewService(Convenio agreement) {
		currentConvenio = agreement;
		newService = new Servico();
	}
	
	public Convenio getCurrentConvenio() {
		return currentConvenio;
	}
	
	public void setCurrentConvenio(Convenio currentConvenio) {
		this.currentConvenio = currentConvenio;
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
