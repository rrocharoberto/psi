package br.edu.univas.model.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import br.edu.univas.model.entity.EvolucaoPK;
import br.edu.univas.model.entity.Paciente;
import br.edu.univas.model.util.Util;
import br.edu.univas.uteis.Uteis;

@Named(value = "evolucaoController")
@ViewScoped
public class EvolucaoController implements Serializable {

	private static final long serialVersionUID = -4128530945553911141L;

	private Paciente paciente;
	
	transient private Map<String, Paciente> pacientes = new HashMap<>();
	
	private Estagiario estagiario;
	
	private boolean isEvolucaoSaveToday;
	
	@Inject
	private Evolucao evolucao;

	@Inject
	transient private Util util;

	@Inject
	transient private EvolucaoDAO evolucaoDAO;

	@Inject
	transient private PacienteDAO pacienteDAO;

	@Inject
	transient private EstagiarioDAO estagiarioDAO;
		
	@Inject
	private List<Evolucao> evolucoes;

	@PostConstruct
	public void init() {
		String matriculaEstagiario = util.getMatriculaUserSession();
		System.out.println("Iniciando evolução para estagiário: " + matriculaEstagiario);
		estagiario = estagiarioDAO.retrieveEstagiario(matriculaEstagiario);
		pacientes = pacienteDAO.retrievePacientesFromEstagiario(matriculaEstagiario);
		
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Cadastrado salvo com sucesso.");
		}
	}
	
	public String salvarEvolucao() {
		Date today = new Date();
		try {
			evolucao.setEstagiario(estagiario);
			evolucao.setProfessor(estagiario.getOrientador());		
			evolucao.setRegistro(paciente.getRegistro());
			
			EvolucaoPK evolucaoPK = new EvolucaoPK();
			evolucaoPK.setData(today);
			evolucaoPK.setNumeroprontuario(paciente.getNumeroProntuario());
			evolucaoPK.setCodigoservico(paciente.getEstagiario().getOrientador().getServico().getCodigoServico());
	
			evolucao.setId(evolucaoPK);
			evolucao.setValidado(false);
			evolucaoDAO.save(evolucao);
		} catch (Exception ex) {
			Uteis.MensagemAtencao("Erro ao salvar os dados da evolução: " + ex.getMessage());
			return null;
		}
		
		return "cadastrarEvolucao.xhtml?faces-redirect=true&save=success";
	}

	public void prepararEvolucao(String numeroProntuario) {
		evolucao = new Evolucao();
		paciente = pacienteDAO.retrievePaciente(numeroProntuario);
		
		evolucoes = evolucaoDAO.retrieveByPaciente(numeroProntuario);
		System.out.println("prepararEvolucao para paciente: " + paciente.getDadosPessoais().getNome());
	}
	
	public boolean isEvolucaoSaveToday() {
		isEvolucaoSaveToday = false;
		
		if (evolucoes != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date today = new Date();
			
			for (Evolucao evolucao : evolucoes) {
				if (sdf.format(evolucao.getId().getData()).equals(sdf.format(today))) {
					isEvolucaoSaveToday = true;
					break;
				}
			}
		}
		
		return isEvolucaoSaveToday;
	}
	
	public void showComment(Evolucao evolucao) {
		System.out.println(new Date());
		System.out.println(evolucao.getDescricaoAvaliacao());
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
	
	public Estagiario getEstagiario() {
		return estagiario;
	}

	public Date getNow() {
		return new Date();
	}
}
