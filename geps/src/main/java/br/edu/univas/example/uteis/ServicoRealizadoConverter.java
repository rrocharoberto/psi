package br.edu.univas.example.uteis;

import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.controller.RealizaServicoController;
import br.edu.univas.model.entity.Servico;

@Named(value = "ServicoRealizadoConverter")
@SessionScoped
public class ServicoRealizadoConverter extends AbstractServicoConverter {

	@Inject
	RealizaServicoController realizaServicoController;

	@Override
	public Map<Integer, Servico> getServicosMap() {
		Map<Integer, Servico> map = realizaServicoController.getServicosSource();
		map.putAll(realizaServicoController.getServicosTarget());
		return map;
	}

}
