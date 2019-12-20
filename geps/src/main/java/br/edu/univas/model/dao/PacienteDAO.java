package br.edu.univas.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.Paciente;

public class PacienteDAO {

	@Inject
	EntityManager em;

	public PacienteDAO(EntityManager em) {
		this.em = em;
	}

	public PacienteDAO() {
	}

	public void save(Paciente paciente) {
		em.persist(paciente);
	}

	public Paciente retrievePaciente(String numeroProntuario) {
		return em.find(Paciente.class, numeroProntuario);
	}

	public List<Paciente> retrieveAllPacientes() {
		TypedQuery<Paciente> query = em.createNamedQuery("Paciente.findAllAtivos", Paciente.class);
		List<Paciente> list = query.getResultList();
		return list;
	}
	
	public Map<String, Paciente> retrieveAllPacientesAsMap() {
		HashMap<String, Paciente> map = new HashMap<>();
		for (Paciente p : retrieveAllPacientes()) {
			map.put(p.getNumeroProntuario(), p);
		}
		return map;
	}

	public void inativate(String numeroProntuario) {
		Paciente paciente = retrievePaciente(numeroProntuario);
		paciente.setAtivo(false);
		em.merge(paciente);
	}

	public void update(Paciente paciente) {
		em.merge(paciente);
	}

	public Map<String, Paciente> retrievePacientesFromEstagiario(String matriculaEstagiario) {
		System.out.println("retrievePacientesFromEstagiario:retrievePacientesFromEstagiario: matriculaEstagiario = " + matriculaEstagiario);
		TypedQuery<Paciente> query = em.createNamedQuery("Paciente.findPacientesByEstagiario", Paciente.class);
		query.setParameter("matricula", matriculaEstagiario);
		List<Paciente> list = query.getResultList();
		
		HashMap<String, Paciente> map = new HashMap<>();
		for (Paciente p : list) {
			map.put(p.getNumeroProntuario(), p);
		}
		return map;
	}

	public Map<String, Paciente> retrieveAllPacientesWithoutAcompanhamento() {
		TypedQuery<Paciente> query = em.createNamedQuery("Paciente.findPacientesWithoutAcompanhamento", Paciente.class);
		List<Paciente> list = query.getResultList();
		
		HashMap<String, Paciente> map = new HashMap<>();
		for (Paciente p : list) {
			map.put(p.getNumeroProntuario(), p);
		}
		return map;
	}
}
