package br.edu.univas.model.controller;

import java.io.Serializable;
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
	
	transient private Map<Long, Paciente> pacientes = new HashMap<>();
	
	private Estagiario estagiario;
	
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
		evolucao.setEstagiario(estagiario);
		evolucao.setProfessor(estagiario.getOrientador());		
		evolucao.setRegistro(paciente.getRegistro());
		
		EvolucaoPK evolucaoPK = new EvolucaoPK();
		evolucaoPK.setData(new Date());
		evolucaoPK.setNumeroprontuario(paciente.getNumeroProntuario());
		evolucaoPK.setCodigoservico(paciente.getEstagiario().getOrientador().getServico().getCodigoServico());

		evolucao.setId(evolucaoPK);
		evolucao.setValidado(false);
		evolucaoDAO.save(evolucao);

		return "cadastrarEvolucao.xhtml?faces-redirect=true&save=success";
	}

	public void prepararEvolucao(Long numeroProntuario) {
		paciente = pacienteDAO.retrievePaciente(numeroProntuario);
		
		evolucoes = evolucaoDAO.retrieveByPaciente(numeroProntuario);
		System.out.println("prepararEvolucao para paciente: " + paciente.getDadosPessoais().getNome());
	}
	
//	public String onFlowProcess(FlowEvent event) {
//		System.out.print("Trocou para da aba: " + event.getOldStep() + " para a aba: " + event.getNewStep());
//		
//		if (event.getOldStep().equals("tab-seleciona-paciente")) {
//			paciente = pacienteDAO.retrievePaciente(pacienteMatricula);
//			
//			if (paciente == null) {
//				Uteis.MensagemAtencao("Selecione um paciente.");
//				return event.getOldStep();
//			} else {
//				//TODO: corrigir: obter via paciente.registro.evolucoes
//				//TODO: verificar onde evolucoes é utilizado
//				
//				evolucoes = evolucaoDAO.retrieveByPaciente(pacienteMatricula);
//				System.out.println(" Paciente: " + paciente.getDadosPessoais().getNome());
//			}
//		}
//		return event.getNewStep();
//	}

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

	public Map<Long, Paciente> getPacientesMap() {
		return pacientes;
	}
	
	public List<Evolucao> getEvolucoes() {
		return evolucoes;
	}
}
