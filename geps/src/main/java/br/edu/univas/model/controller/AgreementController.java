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
import br.edu.univas.model.dao.ConvenioDAO;
import br.edu.univas.model.entity.Convenio;

@Named(value="agreementController")
@ViewScoped
public class AgreementController implements Serializable {

	private static final long serialVersionUID = -639441148553504495L;

	@Inject
	transient private ConvenioDAO dao;

	@Produces
	private List<Convenio> agreements;

	@Inject
	private Convenio currentAgreement;

	@Inject
	private Convenio newAgreement;

	@PostConstruct
	public void populateData() {
		agreements = dao.retrieveAllConvenios();
		
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Cadastrado salvo com sucesso.");
		}
	}

	@Inject
	private Event<Convenio> agreementChangeEvt;

	private void fireAgreementChange() {
		agreementChangeEvt.fire(currentAgreement);
	}

	public void onRowSelect(SelectEvent event) {
		currentAgreement = (Convenio) event.getObject();
		System.out.println("Convenio selecionado: " + currentAgreement.getCodigoConvenio());
		fireAgreementChange();
	}
	
	public String saveNewAgreement() {
		dao.save(newAgreement);
		
		return "servicesByAgreement.xhtml?faces-redirect=true&save=success";
	}

	public Convenio getCurrentAgreement() {
		return currentAgreement;
	}

	public void setCurrentAgreement(Convenio currentAgreement) {
		this.currentAgreement = currentAgreement;
	}

	public List<Convenio> getAgreements() {
		return agreements;
	}

	public Convenio getNewAgreement() {
		return newAgreement;
	}
	
	public void setNewAgreement(Convenio newAgreement) {
		this.newAgreement = newAgreement;
	}
}
