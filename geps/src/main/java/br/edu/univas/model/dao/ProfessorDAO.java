package br.edu.univas.model.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.Professor;

public class ProfessorDAO {

	@Inject
	EntityManager em;

	public ProfessorDAO() {
	}

	public void save(Professor professor) {
		em.persist(professor);
	}

	public Professor retrieveProfessor(Long cpf) {
		return em.find(Professor.class, cpf);
	}

	public HashMap<String, Professor> retrieveAllProfessores() {
		TypedQuery<Professor> query = em.createNamedQuery("Professor.findAll", Professor.class);
		List<Professor> list = query.getResultList();

		HashMap<String, Professor> map = new HashMap<>();
		for (Professor professor : list) {
			map.put(professor.getMatricula(), professor);
		}
		return map;
	}

	public void update(Professor professor) {
		em.merge(professor);
	}
}
