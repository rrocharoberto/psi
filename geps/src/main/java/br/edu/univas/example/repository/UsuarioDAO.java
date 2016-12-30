package br.edu.univas.example.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.univas.example.entity.UsuarioEntity;
import br.edu.univas.example.model.UsuarioModel;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel) {

		try {
			// QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)
			Query query = em.createNamedQuery("UsuarioEntity.findUser");

			// PARÂMETROS DA QUERY
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (UsuarioEntity) query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
