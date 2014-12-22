package bean;

import interfaces.Bean;
import dao.UsuarioDAO;
import entidade.UsuarioEnt;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean implements Bean {
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	UsuarioEnt usuarioEnt = new UsuarioEnt();
	
	public UsuarioEnt getUsuarioEnt() {
		return usuarioEnt;
	}

	public void setUsuarioEnt(UsuarioEnt usuarioEnt) {
		this.usuarioEnt = usuarioEnt;
	}
	
	public String cadastrar() {
		return "admCadUsuario";
	}
	
	public String abrirForm() {
		return "admUsuario";
	}

	public void salvarUsuario() {
		usuarioDAO.salvar(usuarioEnt);		
	}
}
