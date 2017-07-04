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
	    //this method do nothing! But if we remove this code, the combobox will not populate!
	}
	
	public String salvarProfessor() {
		Usuario usuario = usuarioController.getUsuario();
		if (usuarioController.existMatricula(usuario.getMatricula())) {
			Uteis.MensagemAtencao("Essa matrícula está sendo utilizado: " + usuarioController.getUsuario().getMatricula());
			return null;
		}
		
		try {
			usuarioController.save();
			usuario = usuarioController.getUsuario();
		} catch (Exception ex) {
			Uteis.MensagemAtencao("Erro ao salvar os dados de usuário: " + ex.getMessage());
			return null;
		}
		
		try {
			professorController.setService();
			professorController.getProfessor().setUsuario(usuario);
			professorController.getProfessor().setMatricula(usuario.getMatricula());
			professorDAO.save(professorController.getProfessor());
			
			Perfil perfil = new Perfil();
			perfil.setMatricula(usuario.getMatricula());
			perfil.setFuncao(br.edu.univas.uteis.Perfil.PROFESSOR.getValue());
			perfilDAO.save(perfil);
		} catch (Exception ex) {
			Uteis.MensagemAtencao("Erro ao salvar os dados do professor: " + ex.getMessage());
			return null;			
		}
		
		return "professor.xhtml?faces-redirect=true&save=success";
	}

	public String onFlowProcess(FlowEvent event) {
		System.out.println("Trocou para da aba: " + event.getOldStep() 
				+ " para a aba: " + event.getNewStep()
				+ " Nome: " + professorController.getProfessor().getNome());

		return event.getNewStep();
	}
	
	public String newProfessor() {
		return "cadastrarProfessor.xhtml?faces-redirect=true";
	}
	
	public ProfessorController getProfessorController() {
		return professorController;
	}
}
