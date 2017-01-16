package br.edu.univas.example.uteis;

import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.controller.RealizaServicoController;
import br.edu.univas.model.entity.Estagiario;

@Named(value = "EstagiarioServicoConverter")
@SessionScoped
public class EstagiarioServicoConverter extends AbstractEstagiarioConverter {

	@Inject
	RealizaServicoController realizaServicoController;

	@Override
	public Map<Long, Estagiario> getEstagiariosMap() {
		return realizaServicoController.getEstagiariosMap();
	}

}
