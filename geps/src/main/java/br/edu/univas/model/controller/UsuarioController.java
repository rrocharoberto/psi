package br.edu.univas.model.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import br.edu.univas.model.dao.UserDAO;
import br.edu.univas.model.dto.UsuarioModel;
import br.edu.univas.model.entity.Usuario;
import br.edu.univas.uteis.Uteis;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -3663862174461683959L;

	@Inject
	// que veio da tela
	private UsuarioModel usuarioModel;

	@Inject
	Usuario usuario;

	@Inject
	transient private UserDAO dao;

	//https://stackoverflow.com/questions/9965708/how-to-handle-authentication-authorization-with-users-in-a-database
	//http://tomcat.apache.org/tomcat-8.0-doc/realm-howto.html
	//https://fernandofranzini.wordpress.com/2009/09/09/autenticacao-e-autorizacao/
	// http://olamundo-java.blogspot.com.br/2016/02/autenticacao-e-autorizacao-no-jsf-com.html

	public String logout() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			request.logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}

		return "/index.xhtml?faces-redirect=true";
	}

	public String efetuarLogin() {

		if (StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())) {
			Uteis.Mensagem("Favor informar o login!");
			return null;
		} else if (StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())) {
			Uteis.Mensagem("Favor informar a senha!");
			return null;
		} else {

			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			try {
				request.getSession(); // create session before logging in
				// valida o login usando JDBC Realm
				request.login(usuarioModel.getUsuario(), usuarioModel.getSenha());

				// guarda na sessão para o AutenticacaoFilter verificar
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);

				// redireciona para o home
				return "sistema/home?faces-redirect=true";

			} catch (ServletException e) {
				e.printStackTrace();
				Uteis.MensagemAtencao("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}
	}

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

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
}
