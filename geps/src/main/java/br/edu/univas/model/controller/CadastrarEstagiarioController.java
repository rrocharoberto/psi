package br.edu.univas.model.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import br.edu.univas.example.uteis.Uteis;
import br.edu.univas.model.dao.EstagiarioDAO;
import br.edu.univas.model.entity.DadosPessoais;
import br.edu.univas.model.entity.Usuario;

@Named(value = "cadastrarEstagiarioController")
@ViewScoped
public class CadastrarEstagiarioController implements Serializable {

	private static final long serialVersionUID = -2607189167488366035L;

	@Inject
	private DadosPessoaisController dadosPessoaisController;

	@Inject
	private EstagiarioController estagiarioController;

	@Inject
	private UsuarioController usuarioController;

	@Inject
	transient private EstagiarioDAO estagiarioDAO;
	
	public String salvarEstagiario() {
		dadosPessoaisController.save();
		usuarioController.save();
		
		DadosPessoais dadosPessoais = dadosPessoaisController.getDadosPessoais();
		Usuario usuario = usuarioController.getUsuario();
		
		estagiarioController.getEstagiario().setCpf(dadosPessoais.getCpf());
		estagiarioController.getEstagiario().setDadosPessoais(dadosPessoais);
		estagiarioController.getEstagiario().setUsuario(usuario);
		
		estagiarioDAO.save(estagiarioController.getEstagiario());

		Uteis.MensagemInfo("Estagiário cadastrado com sucesso.");
		
		usuarioController.reset();
		dadosPessoaisController.reset();
		estagiarioController.reset();
		
		return null; //recarrega a página de cadastro de paciente
	}

	public String onFlowProcess(FlowEvent event) {
		System.out.println("Trocou para da aba: " + event.getOldStep() 
				+ " para a aba: " + event.getNewStep()
				+ " Nome: " + dadosPessoaisController.getDadosPessoais().getNome());

		estagiarioController.getEstagiario().setDadosPessoais(dadosPessoaisController.getDadosPessoais());
		return event.getNewStep();
	}

}