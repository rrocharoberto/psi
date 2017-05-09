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
import br.edu.univas.model.dao.ProfessorDAO;
import br.edu.univas.model.entity.DadosPessoais;
import br.edu.univas.model.entity.Usuario;

@Named(value = "cadastrarProfessorController")
@ViewScoped
public class CadastrarProfessorController implements Serializable {

	private static final long serialVersionUID = 2569574536599811497L;

	@Inject
	private DadosPessoaisController dadosPessoaisController;
	
	@Inject
	private ProfessorController professorController;
	
	@Inject
	private UsuarioController usuarioController;

	@Inject
	transient private ProfessorDAO professorDAO;

	@PostConstruct
	public void init() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Professor cadastrado com sucesso.");
		}
	}
	
	public void onload() {
	    //doNothing
	}
	
	public String salvarProfessor() {
		dadosPessoaisController.save();
		usuarioController.save();
		
		DadosPessoais dadosPessoais = dadosPessoaisController.getDadosPessoais();
		Usuario usuario = usuarioController.getUsuario();
		
		professorController.getProfessor().setCpf(dadosPessoais.getCpf());
		professorController.getProfessor().setDadosPessoais(dadosPessoais);
		professorController.getProfessor().setUsuario(usuario);
		
		professorDAO.save(professorController.getProfessor());

		usuarioController.reset();
		dadosPessoaisController.reset();
		professorController.reset();
		
		return "cadastrarProfessor.xhtml?faces-redirect=true&save=success";
	}

	public String onFlowProcess(FlowEvent event) {
		System.out.println("Trocou para da aba: " + event.getOldStep() 
				+ " para a aba: " + event.getNewStep()
				+ " Nome: " + dadosPessoaisController.getDadosPessoais().getNome());

		professorController.getProfessor().setDadosPessoais(dadosPessoaisController.getDadosPessoais());
		return event.getNewStep();
	}
}
