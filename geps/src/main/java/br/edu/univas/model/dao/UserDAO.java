package br.edu.univas.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.Usuario;

public class UserDAO {

	@Inject
	EntityManager em;

	public void save(Usuario usuario) {
		em.persist(usuario);
	}

	public Usuario retrieveUser(String userName) {
		return em.find(Usuario.class, userName);
	}

	public void configureActive(String userName, boolean active) {
		Usuario user = em.find(Usuario.class, userName);
		if (user != null) {
			user.setActive(active);
			em.merge(user);
		}
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

	//foi substituído pelo realm
//	public Usuario validaUsuario(UsuarioModel usuarioModel) {
//		try {
//			Query query = em.createNamedQuery("Usuario.findUser");
//			query.setParameter("user", usuarioModel.getUsuario());
//			query.setParameter("pass", usuarioModel.getSenha());
//
//			return (Usuario) query.getSingleResult();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
}
