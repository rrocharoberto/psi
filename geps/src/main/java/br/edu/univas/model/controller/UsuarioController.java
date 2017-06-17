package br.edu.univas.model.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.edu.univas.example.uteis.Uteis;
import br.edu.univas.model.dao.UserDAO;
import br.edu.univas.model.dto.UsuarioModel;
import br.edu.univas.model.entity.Usuario;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -3663862174461683959L;

	@Inject
	private UsuarioModel usuarioModel;
	
	@Inject
	Usuario usuario;

	@Inject
	transient private UserDAO dao;

	public String logout(){
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "/index.xhtml?faces-redirect=true";
	}
	
	public String efetuarLogin(){
			
		if(StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())){
			
			Uteis.Mensagem("Favor informar o login!");
			return null;
		}
		else if(StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())){
			
			Uteis.Mensagem("Favor informar a senha!");
			return null;
		}
		else{	

			usuario = dao.validaUsuario(usuarioModel);
			
			if(usuario!= null){
							
				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuario.getMatricula());
				
				
				FacesContext facesContext = FacesContext.getCurrentInstance();
				
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);
				
				
				return "sistema/home?faces-redirect=true";
			}
			else{
				
				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
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
