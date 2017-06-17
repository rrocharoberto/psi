package br.edu.univas.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.Area;

public class AreaDAO {

	@Inject
	EntityManager em;

	public void save(Area area) {
		em.persist(area);
	}

	public Area retrieveArea(String name) {
		return em.find(Area.class, name);
	}

	public List<Area> retrieveAll() {
		TypedQuery<Area> query = em.createNamedQuery("Area.findAll", Area.class);
		List<Area> list = query.getResultList();
		return list;
	}

}
