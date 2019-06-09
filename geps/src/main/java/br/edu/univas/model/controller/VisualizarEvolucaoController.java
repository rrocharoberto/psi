package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.EstagiarioDAO;
import br.edu.univas.model.dao.EvolucaoDAO;
import br.edu.univas.model.dao.PacienteDAO;
import br.edu.univas.model.dao.ProfessorDAO;
import br.edu.univas.model.entity.Estagiario;
import br.edu.univas.model.entity.Evolucao;
import br.edu.univas.model.entity.Paciente;
import br.edu.univas.model.entity.Professor;
import br.edu.univas.model.util.Util;
import br.edu.univas.uteis.Perfil;
import br.edu.univas.uteis.Uteis;

@Named(value = "visualizarEvolucaoController")
@ViewScoped
public class VisualizarEvolucaoController implements Serializable {

	private static final long serialVersionUID = -552880226283192348L;

	private Paciente paciente;
	
	transient private Map<String, Paciente> pacientes = new HashMap<>();
	
	@Inject
	private Evolucao evolucao;

	@Inject
	transient private Util util;

	@Inject
	transient private EvolucaoDAO evolucaoDAO;

	@Inject
	transient private PacienteDAO pacienteDAO;

	@Inject
	transient private ProfessorDAO professorDAO;
	
	@Inject
	transient private EstagiarioDAO estagiarioDAO;
		
	@Inject
	private List<Evolucao> evolucoes;

	@PostConstruct
	public void init() {
		pacientes = new HashMap<>();
		evolucao = null;
		List<Estagiario> estagiarios = getEstagiarios();
		
		for (Estagiario estagiario : estagiarios) {
			Map<String, Paciente> pacientesByEstagiario = pacienteDAO.retrievePacientesFromEstagiario(estagiario.getMatricula());
			pacientes.putAll(pacientesByEstagiario);
		}
		
		showSuccessMessage();
	}
	
	public String salvarEvolucao() {
		try {
			evolucao.setValidado(true);
			evolucaoDAO.update(evolucao);
		} catch (Exception ex) {
			Uteis.MensagemAtencao("Erro ao salvar os dados da evolução: " + ex.getMessage());
			return null;
		}
		
		return "visualizarEvolucao.xhtml?faces-redirect=true&save=success";
	}

	public void prepararEvolucao(String numeroProntuario) {
		paciente = pacienteDAO.retrievePaciente(numeroProntuario);
		evolucoes = evolucaoDAO.retrieveByPaciente(numeroProntuario);
	}
	
	public void comentar(Evolucao evolucao) {
		this.evolucao = evolucao;
	}

	private List<Estagiario> getEstagiarios() {
		List<Estagiario> estagiarios = new ArrayList<>();
		
		Perfil perfil = util.getPerfilInSession();
		if (perfil.equals(Perfil.SUPERVISORA)) {
			estagiarios = estagiarioDAO.retrieveAll();
		} else {
			String matriculaProfessor = util.getMatriculaUserSession();
			Professor professor = professorDAO.retrieveProfessor(matriculaProfessor);
			if (professor != null) {
				estagiarios = professor.getEstagiarios();
			}
		}
		return estagiarios;
	}

	private void showSuccessMessage() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Cadastrado salvo com sucesso.");
		}
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Evolucao getEvolucao() {
		return evolucao;
	}

	public void setEvolucao(Evolucao evolucao) {
		this.evolucao = evolucao;
	}

	public List<Paciente> getPacientes() {
		return new ArrayList<Paciente>(pacientes.values());
	}

	public Map<String, Paciente> getPacientesMap() {
		return pacientes;
	}
	
	public List<Evolucao> getEvolucoes() {
		return evolucoes;
	}
}
