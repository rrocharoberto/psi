package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.edu.univas.example.uteis.Uteis;
import br.edu.univas.model.dao.AcompanhaDAO;
import br.edu.univas.model.dao.EstagiarioDAO;
import br.edu.univas.model.dao.PacienteDAO;
import br.edu.univas.model.dao.ProntuarioDAO;
import br.edu.univas.model.entity.Acompanha;
import br.edu.univas.model.entity.AcompanhaPK;
import br.edu.univas.model.entity.Estagiario;
import br.edu.univas.model.entity.Paciente;
import br.edu.univas.model.entity.Prontuario;

@Named(value = "acompanhaController")
@ViewScoped
//TODO: excluir e refazer da nova maneira
public class AcompanhaController implements Serializable {

	private static final long serialVersionUID = 3229045736981667521L;
	
	transient private DualListModel<Paciente> pacientesModel;

	transient private Map<Long, Paciente> pacientesSource = new HashMap<>();
	transient private Map<Long, Paciente> pacientesTarget = new HashMap<>();
	

	private Estagiario estagiario;

	transient private Map<Long, Estagiario> estagiarios = new HashMap<>();

	@Inject
	transient private AcompanhaDAO acompanhaDAO;

	@Inject
	transient private PacienteDAO pacienteDAO;

	@Inject
	transient private EstagiarioDAO estagiarioDAO;
	
	@Inject
	transient private ProntuarioDAO prontuarioDAO;

	@PostConstruct
	public void init() {
		estagiarios = estagiarioDAO.retrieveAllEstagiarios();
		pacientesSource = pacienteDAO.retrieveAllPacientesAsMap();
		System.out.println("acompanhaController: Quantidade de pacientes: " + pacientesSource.size());
		System.out.println("acompanhaController: Quantidade de estagiarios: " + estagiarios.size());
		
		List<Paciente> source = new ArrayList<Paciente>(pacientesSource.values());
		List<Paciente> target = new ArrayList<Paciente>(pacientesTarget.values());

		
		pacientesModel = new DualListModel<Paciente>(source, target);
	}
	
	public void selecionarEstagiario(Estagiario estagiario) {
		System.out.println("Estagiário selecionado: " + estagiario.getCpf() + ":" + estagiario.getDadosPessoais().getNome());
		this.estagiario = estagiario;
		pacientesTarget = pacienteDAO.retrievePacientesFromEstagiario(estagiario.getCpf());
		List<Paciente> target = new ArrayList<Paciente>(pacientesTarget.values());
		pacientesModel.setTarget(target);
	}
	
	public String salvarAcompanha() {
		
		System.out.println("Iniciando salvarAcompanha: pacientesModel.size: " + pacientesModel.getTarget().size());
		
		for (Paciente paciente : pacientesModel.getTarget()) {
			System.out.println("Ajustando acompanhamento para paciente: " + paciente.getDadosPessoais().getNome());
			
			AcompanhaPK id = new AcompanhaPK();
			id.setCpf(estagiario.getCpf());
			Prontuario prontuario = paciente.getProntuario();
			if(prontuario == null) {
				prontuario = prontuarioDAO.createNewProntuario(paciente.getCpf());
			}
			id.setNumeroprontuario(prontuario.getNumeroProntuario());
			id.setDatainicio(new Date());
			
			Acompanha acompanha = acompanhaDAO.retrieveAcompanha(id);
			if(acompanha == null) { //se não tem, cria um novo
				acompanhaDAO.createNewAcompanha(id, estagiario, paciente.getProntuario());
			} else {//se tem, então desativa o atual e cria um novo
				acompanha.setAtivo(false);
				
				acompanhaDAO.update(acompanha);
				acompanhaDAO.createNewAcompanha(id, estagiario, paciente.getProntuario());
			}
		}

		Uteis.MensagemInfo("Acompanhamento cadastrado com sucesso.");

		return null; // recarrega a página de registro de evolução
		// TODO: verificar se é melhor ir para outra página
	}

	public List<Estagiario> getEstagiarios() {
		return new ArrayList<Estagiario>(estagiarios.values());
	}

	public Map<Long, Estagiario> getEstagiariosMap() {
		return estagiarios;
	}

	public DualListModel<Paciente> getPacientesModel() {
		return pacientesModel;
	}
	
	public void setPacientesModel(DualListModel<Paciente> pacientesModel) {
		this.pacientesModel = pacientesModel;
	}
	
	public Map<Long, Paciente> getPacientesSource() {
		return pacientesSource;
	}
	
	public Map<Long, Paciente> getPacientesTarget() {
		return pacientesTarget;
	}
	
	public Estagiario getEstagiario() {
		return estagiario;
	}
	
	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}
}
