package br.edu.univas.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.Usuario;

public class UserDAO {

	@Inject
	private EntityManager em;

	public void save(Usuario usuario) {
		em.persist(usuario);
	}

	public Usuario retrieveUser(String matricula) {
		return em.find(Usuario.class, matricula);
	}
	
	public void update(Usuario usuario) {
		em.merge(usuario);
	}

	public List<Usuario> retrieveAllUsers() {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findAll", Usuario.class);
		List<Usuario> list = query.getResultList();
		return list;
	}

	public Usuario findUser() {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findUser", Usuario.class);
		Usuario user = query.getSingleResult();
		return user;
	}
	
}
