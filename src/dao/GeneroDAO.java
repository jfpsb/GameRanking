package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import banco.Conexao;
import entidade.GeneroEnt;

public class GeneroDAO {
	
	Conexao conexao = new Conexao();
	
	public void salvar(GeneroEnt genero) {
		Connection conn = new Conexao().getCon();
		PreparedStatement insereSt = null;
		
		String sql = "INSERT INTO genero (idgenero, descricao) VALUES (?, ?)";
		
		try {
			insereSt = conn.prepareStatement(sql);
			insereSt.setString(1, genero.getIdgenero());
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
	
	public ArrayList<GeneroEnt> pesquisaGenero() {

		Connection conn = new Conexao().getCon();

		ArrayList<GeneroEnt> dadosGen = new ArrayList<GeneroEnt>();

		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT idgenero, descricao FROM genero ORDER BY descricao";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String idgenero = rs.getString("idgenero");
				String descricao = rs.getString("descricao");

				GeneroEnt generoEnt = new GeneroEnt();
				generoEnt.setIdgenero(idgenero);
				generoEnt.setDescricao(descricao);

				dadosGen.add(generoEnt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dadosGen;		
	}

	public void deletar(GeneroEnt genero) {
		Connection conn = new Conexao().getCon();
		
		PreparedStatement deleteSt = null;

		String sql = "DELETE FROM genero WHERE idgenero = ?";

		try {
			deleteSt = conn.prepareStatement(sql);
			deleteSt.setString(1, genero.getIdgenero());
			deleteSt.execute();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Sucesso. Gênero deletado."));
		} catch (SQLException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Erro. Houve um erro ao deletar o gênero."));
		} catch (NullPointerException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Erro. Houve um erro ao deletar o gênero."));
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}