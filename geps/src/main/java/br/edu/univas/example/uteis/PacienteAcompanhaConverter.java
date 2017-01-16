package br.edu.univas.example.uteis;

import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.controller.AcompanhaController;
import br.edu.univas.model.entity.Paciente;

@Named(value = "PacienteAcompanhaConverter")
@SessionScoped
public class PacienteAcompanhaConverter extends AbstractPacienteConverter {

	@Inject
	AcompanhaController acompanhaController;
	
	@Override
	public Map<Long, Paciente> getPacientesMap() {
		Map<Long, Paciente> map = acompanhaController.getPacientesSource();
		map.putAll(acompanhaController.getPacientesTarget());
		return map;
	}

}
