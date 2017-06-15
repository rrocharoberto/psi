package br.edu.univas.example.uteis;

import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.controller.EvolucaoController;
import br.edu.univas.model.entity.Servico;

//http://stackoverflow.com/questions/15392887/use-managedbean-in-facesconverter
@Named(value = "ServicoConverter")
@SessionScoped
public class ServicoConverter extends AbstractServicoConverter {

	@Inject
	EvolucaoController evolucaoController;

	@Override
	public Map<Integer, Servico> getServicosMap() {
		//return evolucaoController.getServicosMap();
		return null;
	}

}
