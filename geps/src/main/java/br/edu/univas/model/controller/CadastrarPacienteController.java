package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import br.edu.univas.example.uteis.Uteis;
import br.edu.univas.model.dao.PacienteDAO;

@Named(value = "cadastrarPacienteController")
@ViewScoped
public class CadastrarPacienteController implements Serializable {

	private static final long serialVersionUID = 2569574536599811497L;

	@Inject
	private DadosPessoaisController dadosPessoaisController;

	@Inject
	private PacienteController pacienteController;

	@Inject
	transient private PacienteDAO pacienteDAO;

	@PostConstruct
	public void init() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Paciente cadastrado com sucesso.");
		}

		dadosPessoaisController.reset();
		pacienteController.reset();
	}
	
	public void onload() {
	    //doNothing
	}
	
	public String salvarPaciente() {
		pacienteController.setEstagiario();
		pacienteController.getCurrentPaciente().setDadosPessoais(null);
		pacienteController.getCurrentPaciente().setAtivo(true);
		pacienteDAO.save(pacienteController.getCurrentPaciente());
		
		dadosPessoaisController.getDadosPessoais().setNumeroprontuario(pacienteController.getCurrentPaciente().getNumeroProntuario());
		dadosPessoaisController.save();

		return "cadastrarPaciente.xhtml?faces-redirect=true&save=success";
	}

	public String onFlowProcess(FlowEvent event) {
		System.out.println("Trocou para da aba: " + event.getOldStep() 
				+ " para a aba: " + event.getNewStep()
				+ " Nome: " + dadosPessoaisController.getDadosPessoais().getNome());

		pacienteController.getCurrentPaciente().setDadosPessoais(dadosPessoaisController.getDadosPessoais());
		return event.getNewStep();
	}

}
