package bean;

import interfaces.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.GameDAO;
import entidade.GameEnt;

@ManagedBean(name="gameBean")
@RequestScoped
public class GameBean implements Bean {
	GameDAO gameDAO = new GameDAO();
	GameEnt gameEnt = new GameEnt();

	public GameEnt getGameEnt() {
		return gameEnt;
	}

	public void setGameEnt(GameEnt gameEnt) {
		this.gameEnt = gameEnt;
	}
	
	public String cadastrar() {
		return "admCadGame";
	}
	
	public String abrirForm() {
		return "admGame";
	}
	
	public void salvarGame() {
		gameDAO.salvar(gameEnt);
	}
}
