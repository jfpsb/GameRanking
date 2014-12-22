package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import banco.Conexao;
import entidade.GeneroEnt;

public class GeneroDAO {
	
	Conexao conexao = new Conexao();
	
	public void salvar(GeneroEnt genero) {
		Connection conn = conexao.getCon();
		PreparedStatement insereSt = null;
		
		String sql = "INSERT INTO genero (idgenero, descricao) VALUES (?, ?)";
		
		try {
			insereSt = conn.prepareStatement(sql);
			insereSt.setLong(1, genero.getIdgenero());
			insereSt.setString(2, genero.getDescricao());
			insereSt.executeUpdate();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Sucesso",  "Gênero salvo com sucesso."));
		} catch (SQLException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro",  "O código escolhido para o gênero já existe. Tente outro."));
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