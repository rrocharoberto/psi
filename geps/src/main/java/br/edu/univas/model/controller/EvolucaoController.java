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

import br.edu.univas.example.uteis.Uteis;
import br.edu.univas.model.dao.EstagiarioDAO;
import br.edu.univas.model.dao.EvolucaoDAO;
import br.edu.univas.model.dao.PacienteDAO;
import br.edu.univas.model.dao.ServicoDAO;
import br.edu.univas.model.entity.Estagiario;
import br.edu.univas.model.entity.Evolucao;
import br.edu.univas.model.entity.EvolucaoPK;
import br.edu.univas.model.entity.Paciente;
import br.edu.univas.model.entity.Servico;
import br.edu.univas.model.util.Util;

@Named(value = "evolucaoController")
@ViewScoped
//TODO: excluir e refazer da nova maneira
public class EvolucaoController implements Serializable {

	private static final long serialVersionUID = -4128530945553911141L;

	private Paciente paciente;

	transient private Map<Long, Paciente> pacientes = new HashMap<>();
	
	private Servico servico;

	transient private Map<Integer, Servico> servicos = new HashMap<>();

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
	transient private ServicoDAO servicoDAO;

	@PostConstruct
	public void init() {
		Long cpfEstagiario = util.getCPFUserSession();//TODO: voltar esta linha
		cpfEstagiario = 99999999999l;
		pacientes = pacienteDAO.retrievePacientesFromEstagiario(cpfEstagiario);
		servicos = servicoDAO.retrieveServicosFromEstagiario(cpfEstagiario);
		System.out.println("Quantidade de pacientes: " + pacientes.size());
		
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Cadastrado salvo com sucesso.");
		}
	}
	
	public String salvarEvolucao() {
		Long cpfEstagiario = util.getCPFUserSession();//TODO: voltar esta linha
		cpfEstagiario = 99999999999l;
		Estagiario estag = estagiarioDAO.retrieveEstagiario(cpfEstagiario);
		evolucao.setEstagiario(estag);
		evolucao.setServico(servico);
		
		evolucao.setProntuario(paciente.getProntuario());
		EvolucaoPK evolucaoPK = new EvolucaoPK();
		evolucaoPK.setData(new Date());
		evolucaoPK.setCodigoservico(servico.getCodigoServico());
		evolucaoPK.setCpfestagiario(estag.getCpf());
		evolucaoPK.setNumeroprontuario(paciente.getProntuario().getNumeroProntuario());

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
		} else if (event.getOldStep().equals("tab-seleciona-servico")) {
			if (servico == null) {
				Uteis.MensagemAtencao("Selecione um servico.");
				return event.getOldStep();
			} else {
				System.out.println(" Servico: " + servico.getNome());
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

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
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

	public List<Servico> getServicos() {
		return new ArrayList<Servico>(servicos.values());
	}

	public Map<Integer, Servico> getServicosMap() {
		return servicos;
	}

	public Map<Long, Paciente> getPacientesMap() {
		return pacientes;
	}
}
