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

	public Paciente retrievePaciente(Long cpf) {
		return em.find(Paciente.class, cpf);
	}

	public List<Paciente> retrieveAllPacientes() {
		TypedQuery<Paciente> query = em.createNamedQuery("Paciente.findAll", Paciente.class);
		List<Paciente> list = query.getResultList();
		return list;
	}
	
	public Map<Long, Paciente> retrieveAllPacientesAsMap() {
		HashMap<Long, Paciente> map = new HashMap<>();
		for (Paciente p : retrieveAllPacientes()) {
			map.put(p.getNumeroProntuario(), p);
		}
		return map;
	}

	public void inativate(Long cpf) {
		Paciente paciente = retrievePaciente(cpf);
		paciente.setAtivo(false);
		em.merge(paciente);
	}

	public void update(Paciente paciente) {
		em.merge(paciente);
	}

	public Map<Long, Paciente> retrievePacientesFromEstagiario(Long cpf) {
		TypedQuery<Paciente> query = em.createNamedQuery("Paciente.findPacientesByEstagiario", Paciente.class);
		query.setParameter("cpf", cpf);
		List<Paciente> list = query.getResultList();
		
		HashMap<Long, Paciente> map = new HashMap<>();
		for (Paciente p : list) {
			map.put(p.getNumeroProntuario(), p);
		}
		return map;
	}

	public Map<Long, Paciente> retrieveAllPacientesWithoutAcompanhamento() {
		TypedQuery<Paciente> query = em.createNamedQuery("Paciente.findPacientesWithoutAcompanhamento", Paciente.class);
		List<Paciente> list = query.getResultList();
		
		HashMap<Long, Paciente> map = new HashMap<>();
		for (Paciente p : list) {
			map.put(p.getNumeroProntuario(), p);
		}
		return map;
	}
}
