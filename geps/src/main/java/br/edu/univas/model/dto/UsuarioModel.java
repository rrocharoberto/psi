package br.edu.univas.model.dto;

import java.io.Serializable;

import br.edu.univas.uteis.StringUtil;

public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String senha;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return StringUtil.simpleTextToSha256(senha);
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}