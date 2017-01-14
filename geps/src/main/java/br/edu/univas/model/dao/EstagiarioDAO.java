package br.edu.univas.model.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.univas.model.entity.Estagiario;

public class EstagiarioDAO {

	@Inject
	EntityManager em;

	public EstagiarioDAO(EntityManager em) {
		this.em = em;
	}

	public EstagiarioDAO() {
	}

	public void save(Estagiario estagiario) {
		em.persist(estagiario);
	}

	public Estagiario retrieveEstagiario(Long cpf) {
		return em.find(Estagiario.class, cpf);
	}

}
