package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import entidade.GameEnt;
import banco.Conexao;

public class GameDAO {
	Conexao conexao = new Conexao();
	
	public void salvar (GameEnt game) {
		Connection conn = conexao.getCon();
		PreparedStatement insereSt = null;
		
		String sql = "INSERT INTO game (idgame, titulo, marca, plataforma, faixaetaria, fk_idgenero) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			insereSt = conn.prepareStatement(sql);
			insereSt.setLong(1, game.getIdgame());
			insereSt.setString(2, game.getTitulo());
			insereSt.setString(3, game.getMarca());
			insereSt.setString(4, game.getPlataforma());
			insereSt.setString(5, game.getFaixaetaria());
			insereSt.setLong(6, game.getIdgenero());
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
}
