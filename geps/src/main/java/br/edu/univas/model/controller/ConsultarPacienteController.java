package br.edu.univas.model.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;

import br.edu.univas.model.dao.PacienteDAO;
import br.edu.univas.model.entity.Paciente;
import br.edu.univas.model.entity.Registro;
import br.edu.univas.uteis.StringUtil;
import br.edu.univas.uteis.Uteis;

@Named(value = "consultarPacienteController")
@ViewScoped
public class ConsultarPacienteController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	private List<Paciente> pacientes;
	
	private List<Paciente> pacientesFiltered;

	@Inject
	transient private PacienteDAO dao;

	@PostConstruct
	public void init() {
		showSuccessMessage();
		this.pacientes = dao.retrieveAllPacientes();
		setDownloadFile();
	}

	private void showSuccessMessage() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Paciente cadastrado com sucesso.");
		}
	}

	public void inativarPaciente(Paciente paciente) {
		dao.inativate(paciente.getNumeroProntuario());
	}
	
	public String newPaciente() {
		return "cadastrarPaciente.xhtml?faces-redirect=true";
	}
	
	private void setDownloadFile() {
		if (pacientes != null) {
			for (Paciente paciente : pacientes) {
				Registro registro = paciente.getRegistro();
				if (registro != null) {
					if (!StringUtil.isNullOrEmpty(registro.getTermoConsentimento())) {
						paciente.setTermoConsentimento(getStream(paciente.getNumeroProntuario(), registro.getTermoConsentimento()));
					}

					if (!StringUtil.isNullOrEmpty(registro.getDeclaracao())) {
						paciente.setDeclaracao(getStream(paciente.getNumeroProntuario(), registro.getDeclaracao()));
					}
				}
			}
		}
	}

	private DefaultStreamedContent getStream(String numeroProntuario, String fileName) {
		try {
			String termo = Uteis.generateFileNameByPaciente(numeroProntuario, fileName);
			InputStream input = new FileInputStream(termo);
			DefaultStreamedContent stream = new DefaultStreamedContent(input);
			stream.setName(fileName);
			return stream;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<Paciente> getPacientesFiltered() {
		return pacientesFiltered;
	}

	public void setPacientesFiltered(List<Paciente> pacientesFiltered) {
		this.pacientesFiltered = pacientesFiltered;
	}
	
}