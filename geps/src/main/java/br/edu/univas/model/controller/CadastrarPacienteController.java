package br.edu.univas.model.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.TabChangeEvent;

import br.edu.univas.example.uteis.Uteis;
import br.edu.univas.model.dao.DadosPessoaisDAO;
import br.edu.univas.model.dao.PacienteDAO;
import br.edu.univas.model.entity.Paciente;

@Named(value = "cadastrarPacienteController")
@RequestScoped
public class CadastrarPacienteController {

	@Inject
	private DadosPessoaisController dadosPessoaisController;
	
	@Inject
	private ConsultarPacienteController consultarPacienteController;

	@Inject
	transient private Paciente currentPaciente;

	@Inject
	transient private PacienteDAO dao;
	
	private int activeIndex = 0;

	public void salvarPaciente() {
		DadosPessoaisDAO dadosDAO = new DadosPessoaisDAO();
		dadosDAO.save(dadosPessoaisController.getDadosPessoais());
		currentPaciente.setDadosPessoais(dadosPessoaisController.getDadosPessoais());
		dao.save(currentPaciente);
		this.currentPaciente = null;

		Uteis.MensagemInfo("Paciente cadastrado com sucesso.");
	}
	
	public void nextTab() {
		activeIndex++;
		System.out.println("Trocou para a aba: " + activeIndex + " Nome: " + dadosPessoaisController.getDadosPessoais().getNome());
	}

	public void previousTab() {
		activeIndex--;
		System.out.println("Trocou para a aba: " + activeIndex + " Nome: " + dadosPessoaisController.getDadosPessoais().getNome());
	}

	public void onTabChange(TabChangeEvent event) {
		consultarPacienteController.getCurrentPaciente().setDadosPessoais(dadosPessoaisController.getDadosPessoais());
    }
	
	public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }


}
