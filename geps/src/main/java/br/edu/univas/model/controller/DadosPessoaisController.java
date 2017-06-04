package br.edu.univas.model.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.DadosPessoaisDAO;
import br.edu.univas.model.entity.DadosPessoais;

@Named(value = "dadosPessoaisController")
@ViewScoped
public class DadosPessoaisController implements Serializable {

	private static final long serialVersionUID = 4426619565195883344L;

	@Inject
	DadosPessoais dadosPessoais;

	@Inject
	transient private DadosPessoaisDAO dadosDAO;
	
	public void reset() {
		dadosPessoais = new DadosPessoais();
	}

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	public void save() {
		dadosDAO.save(dadosPessoais);
	}
}
