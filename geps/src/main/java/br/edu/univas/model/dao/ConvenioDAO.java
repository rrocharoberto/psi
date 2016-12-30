package br.edu.univas.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.Convenio;

public class ConvenioDAO {

	@Inject
	Convenio convenio;

	@Inject
	EntityManager em;

	public void save(Convenio convenio) {
		em.persist(convenio);
	}

	public Convenio retrieveConvenio(String name) {
		return em.find(Convenio.class, name);
	}

//	public void configureActive(String name, boolean active) {
//		Convenio convenio = em.find(Convenio.class, name);
//		if (convenio != null) {
//			convenio.setActive(active);
//			em.merge(convenio);
//		}
//	}

	public List<Convenio> retrieveAllConvenios() {
		TypedQuery<Convenio> query = em.createNamedQuery("Convenio.findAll", Convenio.class);
		List<Convenio> list = query.getResultList();
		return list;
	}

}
