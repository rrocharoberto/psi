package br.edu.univas.model.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.FichaAvaliacao;

public class FichaAvaliacaoDAO {

	@Inject
	private EntityManager em;

	public void save(FichaAvaliacao fichaAvaliacao) {
		em.persist(fichaAvaliacao);
	}
	
	public FichaAvaliacao getFichaAvaliacaoByEstagiario(String matriculaEstagiario) {
		TypedQuery<FichaAvaliacao> query = em.createNamedQuery("FichaAvaliacao.findByMatricula", FichaAvaliacao.class);
		query.setParameter("matricula_estagiario", matriculaEstagiario);
		FichaAvaliacao avaliacao = null;
		try {
			avaliacao = query.getSingleResult();
		} catch (NoResultException e) { /* do nothing */ }
		
		return avaliacao;
	}

}
