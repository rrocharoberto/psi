package br.edu.univas.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.Evolucao;

public class EvolucaoDAO {

	@Inject
	EntityManager em;

	public EvolucaoDAO(EntityManager em) {
		this.em = em;
	}

	public EvolucaoDAO() {
	}

	public void save(Evolucao evolucao) {
		em.persist(evolucao);
	}

	public List<Evolucao> retrieveByPaciente(Long numeroProntuario) {
		TypedQuery<Evolucao> query = em.createNamedQuery("Evolucao.findByProntuario", Evolucao.class);
		query.setParameter("prontuario", numeroProntuario);
		List<Evolucao> list = query.getResultList();
		return list;
	}

	public void update(Evolucao evolucao) {
		em.merge(evolucao);
	}

}
