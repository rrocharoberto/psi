package br.edu.univas.model.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.univas.model.entity.Paciente;
import br.edu.univas.model.entity.Prontuario;

public class ProntuarioDAO {

	@Inject
	EntityManager em;

	public Prontuario retrieveProntuario(Integer numeroProntuario) {
		return em.find(Prontuario.class, numeroProntuario);
	}

	public void update(Prontuario prontuario) {
		em.merge(prontuario);
	}

	public Prontuario createNewProntuario(Long cpf) {
		Paciente paciente =  em.find(Paciente.class, cpf);
		
		Prontuario novoProntuario = new Prontuario();
		novoProntuario.setPaciente(paciente);
		
		em.persist(novoProntuario);
		return novoProntuario;
	}

	public Prontuario retrieveProntuarioFromPaciente(Long cpf) {
		Paciente paciente =  em.find(Paciente.class, cpf);
		return paciente.getProntuario();
	}
}
