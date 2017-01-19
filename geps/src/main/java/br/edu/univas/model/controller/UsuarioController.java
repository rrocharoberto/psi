package br.edu.univas.model.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.UserDAO;
import br.edu.univas.model.entity.Usuario;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -3663862174461683959L;

	@Inject
	Usuario usuario;

	@Inject
	transient private UserDAO dao;

	public void reset() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void save() {
		usuario.setActive(true);
		dao.save(usuario);
	}
}
