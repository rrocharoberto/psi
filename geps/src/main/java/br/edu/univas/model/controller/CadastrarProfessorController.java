package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import br.edu.univas.model.dao.PerfilDAO;
import br.edu.univas.model.dao.ProfessorDAO;
import br.edu.univas.model.entity.Perfil;
import br.edu.univas.model.entity.Usuario;
import br.edu.univas.uteis.Uteis;

@Named(value = "cadastrarProfessorController")
@ViewScoped
public class CadastrarProfessorController implements Serializable {

	private static final long serialVersionUID = 2569574536599811497L;

	@Inject
	private ProfessorController professorController;
	
	@Inject
	private UsuarioController usuarioController;

	@Inject
	transient private ProfessorDAO professorDAO;

	@Inject
	transient private PerfilDAO perfilDAO;
	
	@PostConstruct
	public void init() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Professor cadastrado com sucesso.");
		}
		
		usuarioController.reset();
		professorController.reset();
	}
	
	public void onload() {
	    //doNothing
	    //TODO: verificar se precisa deixar este método aqui
	}
	
	public String salvarProfessor() {
		usuarioController.save();
		Usuario usuario = usuarioController.getUsuario();
		
		professorController.setService();
		professorController.getProfessor().setUsuario(usuario);
		professorController.getProfessor().setMatricula(usuario.getMatricula());
		professorDAO.save(professorController.getProfessor());
		
		Perfil perfil = new Perfil();
		perfil.setMatricula(usuario.getMatricula());
		perfil.setFuncao(br.edu.univas.uteis.Perfil.PROFESSOR.getValue());
		perfilDAO.save(perfil);
		
		return "cadastrarProfessor.xhtml?faces-redirect=true&save=success";
	}

	public String onFlowProcess(FlowEvent event) {
		System.out.println("Trocou para da aba: " + event.getOldStep() 
				+ " para a aba: " + event.getNewStep()
				+ " Nome: " + professorController.getProfessor().getNome());

		return event.getNewStep();
	}
	
	public ProfessorController getProfessorController() {
		return professorController;
	}
}
