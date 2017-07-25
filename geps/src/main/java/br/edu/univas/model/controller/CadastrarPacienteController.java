package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import br.edu.univas.model.dao.FilaEsperaDAO;
import br.edu.univas.model.dao.PacienteDAO;
import br.edu.univas.model.entity.FilaEspera;
import br.edu.univas.model.entity.Paciente;
import br.edu.univas.uteis.Constants;
import br.edu.univas.uteis.Uteis;

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
	
	@Inject
	transient private FilaEsperaDAO filaEsperaDAO;
	
	@PostConstruct
	public void init() {
		dadosPessoaisController.reset();
		pacienteController.reset();
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Object filaEspera = sessionMap.get(Constants.FILA_ESPERA_SESSION);
		if (filaEspera != null) {
			setDataFromFilaEspera((FilaEspera) filaEspera);
		}
	}
	
	public void onload() {
	    //doNothing
	}
	
	public String salvarPaciente() {
		
		Paciente paciente = pacienteDAO.retrievePaciente(pacienteController.getCurrentPaciente().getNumeroProntuario());
		if (paciente != null) {
			Uteis.MensagemAtencao("Esse nº de prontuário já está sendo utilizado: " + pacienteController.getCurrentPaciente().getNumeroProntuario());
			return null;
		}
		
		try {
			pacienteController.setEstagiario();
			pacienteController.getCurrentPaciente().setDadosPessoais(null);
			pacienteController.getCurrentPaciente().setAtivo(true);
			pacienteDAO.save(pacienteController.getCurrentPaciente());
			
			dadosPessoaisController.getDadosPessoais().setNumeroprontuario(pacienteController.getCurrentPaciente().getNumeroProntuario());
			dadosPessoaisController.save();
			
			removeFilaEspera();
		} catch (Exception ex) {
			Uteis.MensagemAtencao("Erro ao salvar os dados do paciente: " + ex.getMessage());
			return null;	
		}

		return "pacientes.xhtml?faces-redirect=true&save=success";
	}

	private void removeFilaEspera() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Object filaEspera = sessionMap.get(Constants.FILA_ESPERA_SESSION);
		if (filaEspera != null) {
			filaEsperaDAO.delete((FilaEspera) filaEspera);
			sessionMap.remove(Constants.FILA_ESPERA_SESSION);
		}
	}

	public String onFlowProcess(FlowEvent event) {
		System.out.println("Trocou para da aba: " + event.getOldStep() 
				+ " para a aba: " + event.getNewStep()
				+ " Nome: " + dadosPessoaisController.getDadosPessoais().getNome());

		pacienteController.getCurrentPaciente().setDadosPessoais(dadosPessoaisController.getDadosPessoais());
		return event.getNewStep();
	}
	
	public void setDataFromFilaEspera(FilaEspera filaEspera) {
		dadosPessoaisController.getDadosPessoais().setNome(filaEspera.getNome());
		dadosPessoaisController.getDadosPessoais().setDataNascimento(filaEspera.getDataNascimento());
		dadosPessoaisController.getDadosPessoais().setTelefone(filaEspera.getTelefone());
	}

}
