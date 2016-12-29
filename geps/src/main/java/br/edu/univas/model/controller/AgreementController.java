package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
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
//		currentAgreement = agreements.isEmpty() ? null : agreements.get(0);
//		fireAgreementChange();
	}

	@Inject
	private Event<Convenio> agreementChangeEvt;

	private void fireAgreementChange() {
		agreementChangeEvt.fire(currentAgreement);
	}

	public void onRowSelect(SelectEvent event) {
		currentAgreement = (Convenio) event.getObject();
		System.out.println("Convenio selecionado: " + currentAgreement.getCodigo());
		fireAgreementChange();
	}
	
	public void saveNewAgreement() {
		dao.save(newAgreement);
		
		populateData();
		
		Uteis.MensagemInfo("Convênio " + newAgreement.getNome() + " cadastrado com sucesso.");
		newAgreement = null;
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
