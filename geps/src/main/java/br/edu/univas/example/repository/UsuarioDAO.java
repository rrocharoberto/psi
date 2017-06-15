package br.edu.univas.example.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.univas.example.model.UsuarioModel;
import br.edu.univas.model.entity.Usuario;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	public Usuario ValidaUsuario(UsuarioModel usuarioModel) {

		try {
			// QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)
			Query query = em.createNamedQuery("Usuario.findUser");

			// PARÂMETROS DA QUERY
			query.setParameter("user", usuarioModel.getUsuario());
			query.setParameter("pass", usuarioModel.getSenha());

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (Usuario) query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
