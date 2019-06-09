package br.edu.univas.model.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.Funcionario;
import br.edu.univas.model.entity.Professor;

public class FuncionarioDAO {

	@Inject
	private EntityManager em;

	public FuncionarioDAO() {
	}

	public void save(Funcionario funcionario) {
		em.persist(funcionario);
	}

	public Funcionario retrieveFuncionario(String matricula) {
		return em.find(Funcionario.class, matricula);
	}
	
	public List<Funcionario> retrieveAll() {
		TypedQuery<Funcionario> query = em.createNamedQuery("Funcionario.findAll", Funcionario.class);
		List<Funcionario> list = query.getResultList();
		return list;
	}

	public HashMap<String, Funcionario> retrieveAllProfessores() {
		TypedQuery<Funcionario> query = em.createNamedQuery("Funcionario.findAll", Funcionario.class);
		List<Funcionario> list = query.getResultList();

		HashMap<String, Funcionario> map = new HashMap<>();
		for (Funcionario funcionario : list) {
			map.put(funcionario.getMatricula(), funcionario);
		}
		return map;
	}

	public void update(Professor professor) {
		em.merge(professor);
	}
}
