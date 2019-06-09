package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import br.edu.univas.model.dao.PerfilDAO;
import br.edu.univas.model.dao.UserDAO;
import br.edu.univas.model.dto.UsuarioModel;
import br.edu.univas.model.entity.Perfil;
import br.edu.univas.model.entity.Usuario;
import br.edu.univas.uteis.Constants;
import br.edu.univas.uteis.StringUtil;
import br.edu.univas.uteis.Uteis;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -8132826801952789797L;

	@Inject
	private UsuarioModel usuarioModel;

	@Inject
	private Usuario usuario;

	@Inject
	transient private UserDAO dao;
	
	@Inject
	transient private PerfilDAO perfilDAO;

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
			ExternalContext externalContext = context.getExternalContext();
			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
			try {
				request.getSession(); // create session before logging in
				// valida o login usando JDBC Realm
				request.login(usuarioModel.getUsuario(), usuarioModel.getSenha());

				// guarda na sessão para o AutenticacaoFilter verificar
				Map<String, Object> sessionMap = externalContext.getSessionMap();
				sessionMap.put(Constants.USER_IN_SESSION, usuarioModel);
				savePerfilInSession(usuarioModel.getUsuario(), sessionMap);
				
				// redireciona para o home
				return "sistema/home?faces-redirect=true";

			} catch (ServletException e) {
				e.printStackTrace();
				Uteis.MensagemAtencao("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}
	}
	
	private void savePerfilInSession(String matricula, Map<String, Object> sessionMap) {
		Perfil perfil = perfilDAO.getPerfilByMatricula(matricula);
		sessionMap.put(Constants.RULE_IN_SESSION, br.edu.univas.uteis.Perfil.valueOf(perfil.getFuncao()));
	}
	
	public boolean existMatricula(String matricula) {
		return dao.retrieveUser(matricula) != null;
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
		usuario.setPassword(StringUtil.simpleTextToSha256(usuario.getPassword()));
		dao.save(usuario);
	}
	
	public void update() {
		dao.update(usuario);
	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
}
