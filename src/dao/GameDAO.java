package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import entidade.GameEnt;
import banco.Conexao;

public class GameDAO {
	Conexao conexao = new Conexao();
	
	public void salvar (GameEnt game) {
		Connection conn = new Conexao().getCon();
		PreparedStatement insereSt = null;
		
		String sql = "INSERT INTO game (idgame, titulo, marca, plataforma, faixaetaria, fk_idgenero) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			insereSt = conn.prepareStatement(sql);
			insereSt.setLong(1, game.getIdgame());
			insereSt.setString(2, game.getTitulo());
			insereSt.setString(3, game.getMarca());
			insereSt.setString(4, game.getPlataforma());
			insereSt.setString(5, game.getFaixaetaria());
			insereSt.setString(6, game.getIdgenero());
			insereSt.executeUpdate();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Sucesso",  "Game salvo com sucesso."));
		} catch (SQLException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro",  "O código escolhido para o game já existe. Tente outro."));
		} finally {
			try {
				insereSt.close();
				conn.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar conexão.");
				e.printStackTrace();
			}
		}
	}

	public ArrayList<GameEnt> pesquisaGame() {

		Connection conn = new Conexao().getCon();

		ArrayList<GameEnt> dadosGame = new ArrayList<GameEnt>();

		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT idgame, titulo, marca, plataforma, faixaetaria, descricao FROM game, genero WHERE fk_idgenero = idgenero ORDER BY titulo";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int idgame = rs.getInt("idgame");
				String titulo = rs.getString("titulo");
				String marca = rs.getString("marca");
				String plataforma = rs.getString("plataforma");
				String faixaetaria = rs.getString("faixaetaria");
				String genero = rs.getString("descricao");

				GameEnt gameEnt = new GameEnt();
				gameEnt.setIdgame(idgame);
				gameEnt.setTitulo(titulo);
				gameEnt.setMarca(marca);
				gameEnt.setPlataforma(plataforma);
				gameEnt.setFaixaetaria(faixaetaria);
				gameEnt.setIdgenero(genero);

				dadosGame.add(gameEnt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dadosGame;		
	}

	public void deletar(GameEnt game) {
		Connection conn = new Conexao().getCon();
		
		PreparedStatement deleteSt = null;

		String sql = "DELETE FROM game WHERE idgame = ?";

		try {
			deleteSt = conn.prepareStatement(sql);
			deleteSt.setInt(1, game.getIdgame());
			deleteSt.execute();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Sucesso. Game deletado."));
		} catch (SQLException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Erro. Houve um erro ao deletar o game."));
		} catch (NullPointerException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Erro. Houve um erro ao deletar o game."));
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
