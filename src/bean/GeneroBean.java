package bean;

import interfaces.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.GeneroDAO;
import entidade.GeneroEnt;

@ManagedBean(name="generoBean")
@RequestScoped
public class GeneroBean implements Bean {
	GeneroDAO generoDAO = new GeneroDAO();
	GeneroEnt generoEnt = new GeneroEnt();

	public GeneroEnt getGeneroEnt() {
		return generoEnt;
	}

	public void setGeneroEnt(GeneroEnt generoEnt) {
		this.generoEnt = generoEnt;
	}
	
	public String cadastrar() {
		return "admCadGenero";
	}
	
	public String abrirForm() {
		return "admGenero";
	}
	
	public String alterar() {
		return "admDelUsuario";
	}
	
	public String deletar() {
		return "admDelUsuario";
	}
	
	public void salvarGenero() {
		generoDAO.salvar(generoEnt);
	}
}
