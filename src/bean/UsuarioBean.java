package bean;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Bean;
import dao.UsuarioDAO;
import entidade.UsuarioEnt;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean implements Bean, Serializable {
	private static final long serialVersionUID = 1L;
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
	
	public String alterar() {
		return "admDelUsuario";
	}
	
	public String deletar() {
		return "admDelUsuario";
	}

	public void salvarUsuario() {
		usuarioDAO.salvar(usuarioEnt);
	}
	
	public void deletarUsuario() {
		usuarioDAO.deletar(usuarioEnt);
	}
	
	public ArrayList<UsuarioEnt> listar() {
		return usuarioDAO.pesquisaAluno();
	}
}