package br.edu.univas.example.uteis;

import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.controller.EvolucaoController;
import br.edu.univas.model.entity.Paciente;


@Named(value = "PacienteConverter")
@SessionScoped
public class PacienteConverter extends AbstractPacienteConverter {

	@Inject
	EvolucaoController evolucaoController;
	
	@Override
	public Map<Long, Paciente> getPacientesMap() {
		return evolucaoController.getPacientesMap();
	}

}
