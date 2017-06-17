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

import org.primefaces.event.FlowEvent;

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
//TODO: precisa corrigir a tela de evolução
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

	@PostConstruct
	public void init() {
		String matriculaEstagiario = util.getMatriculaUserSession();
		matriculaEstagiario = "99999999999";
		estagiario = estagiarioDAO.retrieveEstagiario(matriculaEstagiario);
		pacientes = pacienteDAO.retrievePacientesFromEstagiario(matriculaEstagiario);
		System.out.println("Quantidade de pacientes: " + pacientes.size());
		
		//TODO: precisa fazer assim mesmo, ou tem outra solução?
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Cadastrado salvo com sucesso.");
		}
	}
	
	public String salvarEvolucao() {
		evolucao.setEstagiario(estagiario);
		
		evolucao.setRegistro(paciente.getRegistro());
		EvolucaoPK evolucaoPK = new EvolucaoPK();
		evolucaoPK.setData(new Date());
		evolucaoPK.setNumeroprontuario(paciente.getNumeroProntuario());

		evolucao.setId(evolucaoPK);
		evolucaoDAO.save(evolucao);

		return "cadastrarEvolucao.xhtml?faces-redirect=true&save=success";
	}

	public void selecionarPaciente(Paciente paciente) {
		this.paciente = paciente;
		System.out.println("Paciente escolhido: " + paciente.getDadosPessoais().getNome());
	}

	public String onFlowProcess(FlowEvent event) {
		System.out.print("Trocou para da aba: " + event.getOldStep() + " para a aba: " + event.getNewStep());

		if (event.getOldStep().equals("tab-seleciona-paciente")) {
			if (paciente == null) {
				Uteis.MensagemAtencao("Selecione um paciente.");
				return event.getOldStep();
			} else {
				System.out.println(" Paciente: " + paciente.getDadosPessoais().getNome());
			}
		}
		return event.getNewStep();
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

	public Map<Long, Paciente> getPacientesMap() {
		return pacientes;
	}
}
