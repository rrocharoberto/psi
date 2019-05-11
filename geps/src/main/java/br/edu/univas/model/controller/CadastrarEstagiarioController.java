package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import br.edu.univas.model.dao.EstagiarioDAO;
import br.edu.univas.model.dao.PerfilDAO;
import br.edu.univas.model.entity.Estagiario;
import br.edu.univas.model.entity.Perfil;
import br.edu.univas.model.entity.Usuario;
import br.edu.univas.uteis.Constants;
import br.edu.univas.uteis.Uteis;

@Named(value = "cadastrarEstagiarioController")
@ViewScoped
public class CadastrarEstagiarioController implements Serializable {

	private static final long serialVersionUID = -2607189167488366035L;

	@Inject
	private EstagiarioController estagiarioController;

	@Inject
	private UsuarioController usuarioController;

	@Inject
	transient private EstagiarioDAO estagiarioDAO;

	@Inject
	transient private PerfilDAO perfilDAO;
	
	private boolean isEditMode = false;
	
	@PostConstruct
	public void init() {
		Map<String, String> requestParameter = showSuccessMessage();
		resetControllers();
		retrievingDataToEdit(requestParameter);
	}
	
	public void onload() {
		//this method do nothing! But if we remove this code, the combobox will not populate!
	}
	
	public String salvarEstagiario() {
		if (isEditMode) {
			return updateEstagiario();
		}

		Usuario usuario = usuarioController.getUsuario();
		if (usuarioController.existMatricula(usuario.getMatricula())) {
			Uteis.MensagemAtencao("Essa matrícula está sendo utilizado: " + usuarioController.getUsuario().getMatricula());
			return null;
		}
		
		try {
			usuarioController.save();
			usuario = usuarioController.getUsuario();
		} catch (Exception ex) {
			ex.printStackTrace();
			Uteis.MensagemAtencao("Erro ao salvar os dados de usuário: " + ex.getMessage());
			return null;
		}
		
		try {
			estagiarioController.setProfessor();
			estagiarioController.getEstagiario().setUsuario(usuario);
			estagiarioController.getEstagiario().setMatricula(usuario.getMatricula());
			estagiarioDAO.save(estagiarioController.getEstagiario());
			
			Perfil perfil = new Perfil();
			perfil.setMatricula(usuario.getMatricula());
			perfil.setFuncao(br.edu.univas.uteis.Perfil.ESTAGIARIO.getValue());
			perfilDAO.save(perfil);
		} catch (Exception ex) {
			ex.printStackTrace();
			Uteis.MensagemAtencao("Erro ao salvar os dados do estagiário: " + ex.getMessage());
			return null;
		}
		
		return "estagiario.xhtml?faces-redirect=true&save=success";
	}

	public String onFlowProcess(FlowEvent event) {
		return event.getNewStep();
	}
	
	public String newEstagiario() {
		return "cadastrarEstagiario.xhtml?faces-redirect=true";
	}

	public String editEstagiario(Estagiario estagiario) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put(Constants.ESTAGIARIO_SESSION, estagiario);
		return "cadastrarEstagiario.xhtml?faces-redirect=true&edit=true";
	}

	public EstagiarioController getEstagiarioController() {
		return estagiarioController;
	}

	private Map<String, String> showSuccessMessage() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Estágiario cadastrado com sucesso.");
		}
		return requestParameter;
	}

	private void resetControllers() {
		usuarioController.reset();
		estagiarioController.reset();
	}
	
	private void retrievingDataToEdit(Map<String, String> requestParameter) {
		if ("true".equals(requestParameter.get("edit"))) {
			isEditMode = true;
			Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			Estagiario estagiario = (Estagiario) sessionMap.get(Constants.ESTAGIARIO_SESSION);
			
			usuarioController.setUsuario(estagiario.getUsuario());
			estagiarioController.setCurrentProfessor(estagiario.getOrientador().getMatricula());
			estagiarioController.setEstagiario(estagiario);
		}
	}

	private String updateEstagiario() {
		try {
			usuarioController.update();
		} catch (Exception ex) {
			ex.printStackTrace();
			Uteis.MensagemAtencao("Erro ao salvar os dados de usuário: " + ex.getMessage());
			return null;
		}
		
		try {
			estagiarioController.setProfessor();
			estagiarioController.getEstagiario().setUsuario(usuarioController.getUsuario());
			estagiarioDAO.update(estagiarioController.getEstagiario());
		} catch (Exception ex) {
			ex.printStackTrace();
			Uteis.MensagemAtencao("Erro ao salvar os dados do estagiário: " + ex.getMessage());
			return null;
		}
		
		return "estagiario.xhtml?faces-redirect=true&save=success";
	}
}
