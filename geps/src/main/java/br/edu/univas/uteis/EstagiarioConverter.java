package br.edu.univas.uteis;

import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.controller.AcompanhaController;
import br.edu.univas.model.entity.Estagiario;

@Named(value = "EstagiarioConverter")
@SessionScoped
public class EstagiarioConverter extends AbstractEstagiarioConverter {

	@Inject
	AcompanhaController acompanhaController;

	@Override
	public Map<Long, Estagiario> getEstagiariosMap() {
		return acompanhaController.getEstagiariosMap();
	}

}
