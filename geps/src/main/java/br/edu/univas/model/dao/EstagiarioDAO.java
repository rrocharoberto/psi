package br.edu.univas.model.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.Estagiario;

public class EstagiarioDAO {

	@Inject
	EntityManager em;

	public EstagiarioDAO() {
	}

	public void save(Estagiario estagiario) {
		em.persist(estagiario);
	}

	public Estagiario retrieveEstagiario(String matricula) {
		return em.find(Estagiario.class, matricula);
	}

	public List<Estagiario> retrieveEstagiariosAtivos() {
		TypedQuery<Estagiario> query = em.createNamedQuery("Estagiario.findAllAtivos", Estagiario.class);
		query.setParameter("today", new Date());
		List<Estagiario> list = query.getResultList();

//		HashMap<String, Estagiario> map = new HashMap<>();
//		for (Estagiario e : list) {
//			map.put(e.getMatricula(), e);
//		}
//		return map;
		return list;
	}

	public void update(Estagiario estagiario) {
		em.merge(estagiario);
	}

	public List<Estagiario> retrieveAll() {
		TypedQuery<Estagiario> query = em.createNamedQuery("Estagiario.findAll", Estagiario.class);
		List<Estagiario> list = query.getResultList();
		return list;
	}
}
