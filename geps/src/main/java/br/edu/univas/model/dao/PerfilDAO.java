package br.edu.univas.model.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.univas.model.entity.Perfil;

public class PerfilDAO {

	@Inject
	EntityManager em;

	public void save(Perfil perfil) {
		em.persist(perfil);
	}
	
}
