package br.edu.univas.model.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.entity.DadosPessoais;

@Named(value = "dadosPessoaisController")
@RequestScoped
public class DadosPessoaisController {

	@Inject
	DadosPessoais dadosPessoais;

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

}
