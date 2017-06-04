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
import br.edu.univas.model.dao.EstagiarioDAO;
import br.edu.univas.model.entity.Usuario;

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
	
	@PostConstruct
	public void init() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Estágiario cadastrado com sucesso.");
		}
	}
	
	public void onload() {
	    //doNothing
	}
	
	public String salvarEstagiario() {
		usuarioController.save();
		
		Usuario usuario = usuarioController.getUsuario();
		
		estagiarioController.getEstagiario().setUsuario(usuario);
		
		estagiarioDAO.save(estagiarioController.getEstagiario());

		Uteis.MensagemInfo("Estagiário cadastrado com sucesso.");
		
		usuarioController.reset();
		estagiarioController.reset();
		
		return "cadastrarEstagiario.xhtml?faces-redirect=true&save=success";
	}

	public String onFlowProcess(FlowEvent event) {
		System.out.println("Trocou para da aba: " + event.getOldStep() 
				+ " para a aba: " + event.getNewStep()
				+ " Nome: " + estagiarioController.getEstagiario().getNome());

		return event.getNewStep();
	}

}
