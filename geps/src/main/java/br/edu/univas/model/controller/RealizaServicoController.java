package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.edu.univas.example.uteis.Uteis;
import br.edu.univas.model.dao.EstagiarioDAO;
import br.edu.univas.model.dao.ServicoDAO;
import br.edu.univas.model.entity.Estagiario;
import br.edu.univas.model.entity.RealizaServico;
import br.edu.univas.model.entity.RealizaServicoPK;
import br.edu.univas.model.entity.Servico;

@Named(value = "realizaServicoController")
@ViewScoped
//TODO: excluir e refazer da nova maneira
public class RealizaServicoController implements Serializable {

	private static final long serialVersionUID = 3229045736981667521L;

	transient private DualListModel<Servico> servicosModel;

	private Servico servico;

	transient private Map<Integer, Servico> servicosSource = new HashMap<>();
	transient private Map<Integer, Servico> servicosTarget = new HashMap<>();

	private Estagiario estagiario;

	transient private Map<Long, Estagiario> estagiarios = new HashMap<>();

	@Inject
	transient private ServicoDAO servicoDAO;

	@Inject
	transient private EstagiarioDAO estagiarioDAO;

	@PostConstruct
	public void init() {
		estagiarios = estagiarioDAO.retrieveAllEstagiarios();
		servicosSource = servicoDAO.retrieveAllServicosAsMap();
		System.out.println("RealizaServicoController: Quantidade de pacientes: " + servicosSource.size());
		System.out.println("RealizaServicoController: Quantidade de estagiarios: " + estagiarios.size());

		List<Servico> source = new ArrayList<Servico>(servicosSource.values());
		List<Servico> target = new ArrayList<Servico>(servicosTarget.values());

		servicosModel = new DualListModel<Servico>(source, target);
	}

	// TODO: criar um ajax para selecionar o estagiário
	public void selecionarEstagiario(Estagiario estagiario) {
		System.out.println(
				"Estagiário selecionado: " + estagiario.getCpf() + ":" + estagiario.getDadosPessoais().getNome());
		this.estagiario = estagiario;
		servicosTarget = servicoDAO.retrieveServicosFromEstagiario(estagiario.getCpf());
		List<Servico> target = new ArrayList<Servico>(servicosTarget.values());
		servicosModel.setTarget(target);
	}

	public String salvarRealizaServico() {

		System.out.println("Iniciando salvarRealizaServico: pacientesModel.size: " + servicosModel.getTarget().size());

		for (Servico servico : servicosModel.getTarget()) {
			System.out.println("Ajustando realização para servico: " + servico.getNome());

			RealizaServicoPK id = new RealizaServicoPK();
			id.setCpf(estagiario.getCpf());
			id.setCodigoServico(servico.getCodigoServico());

			RealizaServico realizaServico = new RealizaServico();
			realizaServico.setId(id);
			realizaServico.setEstagiario(estagiario);
			realizaServico.setServico(servico);
			servicoDAO.deleteRealizaServico(id);
			servicoDAO.saveRealizaServico(realizaServico);
		}

		Uteis.MensagemInfo("Realiza serviço cadastrado com sucesso.");

		return null; // recarrega a página de registro de evolução
		// TODO: verificar se é melhor ir para outra página
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<Estagiario> getEstagiarios() {
		return new ArrayList<Estagiario>(estagiarios.values());
	}

	public Map<Long, Estagiario> getEstagiariosMap() {
		return estagiarios;
	}

	public DualListModel<Servico> getServicosModel() {
		return servicosModel;
	}

	public void setServicosModel(DualListModel<Servico> servicosModel) {
		this.servicosModel = servicosModel;
	}

	public Map<Integer, Servico> getServicosSource() {
		return servicosSource;
	}

	public Map<Integer, Servico> getServicosTarget() {
		return servicosTarget;
	}

	public Estagiario getEstagiario() {
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}
}
