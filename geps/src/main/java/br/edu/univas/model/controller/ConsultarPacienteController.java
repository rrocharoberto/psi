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

import br.edu.univas.model.dao.PacienteDAO;
import br.edu.univas.model.entity.Paciente;
import br.edu.univas.uteis.Uteis;

@Named(value = "consultarPacienteController")
@ViewScoped
public class ConsultarPacienteController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	private List<Paciente> pacientes;

	@Inject
	transient private PacienteDAO dao;

	@PostConstruct
	public void init() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Paciente cadastrado com sucesso.");
		}
		
		this.pacientes = dao.retrieveAllPacientes();
	}

	public void inativarPaciente(Paciente paciente) {
		dao.inativate(paciente.getNumeroProntuario());
	}
	
	public String newPaciente() {
		return "cadastrarPaciente.xhtml?faces-redirect=true";
	}
	
	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

}