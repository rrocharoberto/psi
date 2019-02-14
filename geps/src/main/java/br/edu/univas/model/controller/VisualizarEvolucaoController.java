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

import br.edu.univas.model.dao.EvolucaoDAO;
import br.edu.univas.model.dao.PacienteDAO;
import br.edu.univas.model.dao.ProfessorDAO;
import br.edu.univas.model.entity.Estagiario;
import br.edu.univas.model.entity.Evolucao;
import br.edu.univas.model.entity.Paciente;
import br.edu.univas.model.entity.Professor;
import br.edu.univas.model.util.Util;
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
	private List<Evolucao> evolucoes;

	@PostConstruct
	public void init() {
		String matriculaProfessor = util.getMatriculaUserSession();
		Professor professor = professorDAO.retrieveProfessor(matriculaProfessor);
		if (professor != null) {
			List<Estagiario> estagiarios = professor.getEstagiarios();
			
			pacientes = new HashMap<>();
			evolucao = null;
			
			for (Estagiario estagiario : estagiarios) {
				Map<String, Paciente> pacientesByEstagiario = pacienteDAO.retrievePacientesFromEstagiario(estagiario.getMatricula());
				pacientes.putAll(pacientesByEstagiario);
			}
		}
		
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Cadastrado salvo com sucesso.");
		}
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
		System.out.println("prepararEvolucao para paciente: " + paciente.getDadosPessoais().getNome());
	}
	
	public void comentar(Evolucao evolucao) {
		this.evolucao = evolucao;
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
