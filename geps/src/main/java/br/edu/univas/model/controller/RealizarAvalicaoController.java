package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.FichaAvaliacaoDAO;
import br.edu.univas.model.entity.Estagiario;
import br.edu.univas.model.entity.FichaAvaliacao;
import br.edu.univas.uteis.Constants;

@Named(value = "realizarAvaliacaoController")
@ViewScoped
public class RealizarAvalicaoController implements Serializable {

	private static final long serialVersionUID = 6043488424842667354L;

	@Inject
	transient private FichaAvaliacaoDAO dao;
	
	private Estagiario estagiario;
	
	private FichaAvaliacao fichaAvaliacao;
	
	@PostConstruct
	public void init() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		estagiario = (Estagiario) sessionMap.get(Constants.ESTAGIARIO_SESSION);
		fichaAvaliacao = dao.getFichaAvaliacaoByEstagiario(estagiario.getMatricula());
		if (fichaAvaliacao == null) {
			fichaAvaliacao = new FichaAvaliacao();
			fichaAvaliacao.setMatricula_estagiario(estagiario.getMatricula());
		}
	}
	
	public void onload() {
	    //doNothing
	}
	
	public void updateAverage(AjaxBehaviorEvent e) {
		Integer[] values = {fichaAvaliacao.getAtitude(), fichaAvaliacao.getCognitiva(),
				fichaAvaliacao.getHabilidade(), fichaAvaliacao.getRelatorio_cientifico()};
		
		int sum = 0;
		for (Integer value : values) {
			if (value != null) {
				sum += value;
			}
		}
		
		fichaAvaliacao.setMedia_geral(sum / 4);
	}
	
	public String avaliar() {
		dao.save(fichaAvaliacao);
		return "avaliacao.xhtml?faces-redirect=true&save=success";
	}

	public Estagiario getEstagiario() {
		return estagiario;
	}

	public FichaAvaliacao getFichaAvaliacao() {
		return fichaAvaliacao;
	}

	public void setFichaAvaliacao(FichaAvaliacao fichaAvaliacao) {
		this.fichaAvaliacao = fichaAvaliacao;
	}
	
}