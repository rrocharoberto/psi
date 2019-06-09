package br.edu.univas.model.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.univas.model.entity.Perfil;

public class PerfilDAO {

	@Inject
	private EntityManager em;

	public void save(Perfil perfil) {
		em.persist(perfil);
	}
	
	public Perfil getPerfilByMatricula(String matricula) {
		return em.find(Perfil.class, matricula);
	}
}
