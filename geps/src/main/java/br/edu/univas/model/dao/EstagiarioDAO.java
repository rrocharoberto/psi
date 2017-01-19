package br.edu.univas.model.dao;

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

	public Estagiario retrieveEstagiario(Long cpf) {
		return em.find(Estagiario.class, cpf);
	}

	public HashMap<Long, Estagiario> retrieveAllEstagiarios() {
		TypedQuery<Estagiario> query = em.createNamedQuery("Estagiario.findAllAtivos", Estagiario.class);
		List<Estagiario> list = query.getResultList();

		HashMap<Long, Estagiario> map = new HashMap<>();
		for (Estagiario e : list) {
			map.put(e.getCpf(), e);
		}
		return map;
	}


	public void update(Estagiario estagiario) {
		em.merge(estagiario);
	}
}
