package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import br.edu.univas.model.dao.FuncionarioDAO;
import br.edu.univas.model.dao.PerfilDAO;
import br.edu.univas.model.entity.Perfil;
import br.edu.univas.model.entity.Usuario;
import br.edu.univas.uteis.Uteis;

@Named(value = "cadastrarFuncionarioController")
@ViewScoped
public class CadastrarFuncionarioController implements Serializable {

	private static final long serialVersionUID = 2569574536599811497L;

	@Inject
	private FuncionarioController funcionarioController;
	
	@Inject
	private UsuarioController usuarioController;

	@Inject
	transient private FuncionarioDAO funcionarioDAO;

	@Inject
	transient private PerfilDAO perfilDAO;
	
	@PostConstruct
	public void init() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Funcionário cadastrado com sucesso.");
		}
		
		usuarioController.reset();
		funcionarioController.reset();
	}
	
	public void onload() {
	    //this method do nothing! But if we remove this code, the combobox will not populate!
	}
	
	public String salvarFuncionario() {
		Usuario usuario = usuarioController.getUsuario();
		if (usuarioController.existMatricula(usuario.getMatricula())) {
			Uteis.MensagemAtencao("Essa matrícula está sendo utilizada: " + usuarioController.getUsuario().getMatricula());
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
			funcionarioController.getFuncionario().setUsuario(usuario);
			funcionarioController.getFuncionario().setMatricula(usuario.getMatricula());
			funcionarioDAO.save(funcionarioController.getFuncionario());
			
			Perfil perfil = new Perfil();
			perfil.setMatricula(usuario.getMatricula());
			perfil.setFuncao(br.edu.univas.uteis.Perfil.FUNCIONARIO.getValue());
			perfilDAO.save(perfil);
		} catch (Exception ex) {
			ex.printStackTrace();
			Uteis.MensagemAtencao("Erro ao salvar os dados do funcionário: " + ex.getMessage());
			return null;			
		}
		
		return "listarFuncionario.xhtml?faces-redirect=true&save=success";
	}

	public String onFlowProcess(FlowEvent event) {
		System.out.println("Trocou para da aba: " + event.getOldStep() 
				+ " para a aba: " + event.getNewStep()
				+ " Nome: " + funcionarioController.getFuncionario().getNome());

		return event.getNewStep();
	}
	
	public String newFuncionario() {
		return "cadastrarFuncionario.xhtml?faces-redirect=true";
	}
	
	public FuncionarioController getFuncionarioController() {
		return funcionarioController;
	}
}
