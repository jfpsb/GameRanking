package dao;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import entidade.UsuarioEnt;
import banco.Conexao;

public class UsuarioDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	public void salvar(UsuarioEnt usuario) {
		Connection conn = new Conexao().getCon();
		
		PreparedStatement insereSt = null;

		String sql = "INSERT INTO usuario (idusuario, nome, email, senha, admin) VALUES (?, ?, ?, ?, ?)";

		try {
			insereSt = conn.prepareStatement(sql);
			insereSt.setLong(1, usuario.getIdusuario());
			insereSt.setString(2, usuario.getNome());
			insereSt.setString(3, usuario.getEmail());
			insereSt.setString(4, usuario.getSenha());
			insereSt.setBoolean(5, usuario.isAdmin());
			insereSt.executeUpdate();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Sucesso",
					"Usuário cadastrado com sucesso."));
		} catch (SQLException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro",
					"Você escolheu um login repetido. Tente outro."));
		} finally {
			try {
				conn.close();
				insereSt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deletar(UsuarioEnt usuario) {
		Connection conn = new Conexao().getCon();
		
		PreparedStatement deleteSt = null;

		String sql = "DELETE FROM usuario WHERE idusuario = ?";

		try {
			deleteSt = conn.prepareStatement(sql);
			deleteSt.setInt(1, usuario.getIdusuario());
			deleteSt.execute();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Sucesso. Usuário deletado."));
		} catch (SQLException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Erro. Houve um erro ao deletar usuário."));
		} catch (NullPointerException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Erro. Houve um erro ao deletar usuário."));
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<UsuarioEnt> pesquisaAluno() {

		Connection conn = new Conexao().getCon();

		ArrayList<UsuarioEnt> dadosUser = new ArrayList<UsuarioEnt>();

		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT idusuario, nome, email, admin FROM usuario ORDER BY nome";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int idusuario = rs.getInt("idusuario");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				boolean admin = rs.getBoolean("admin");

				UsuarioEnt usuarioEnt = new UsuarioEnt();
				usuarioEnt.setIdusuario(idusuario);
				usuarioEnt.setNome(nome);
				usuarioEnt.setEmail(email);
				usuarioEnt.setAdmin(admin);

				dadosUser.add(usuarioEnt);
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
		
		return dadosUser;		
	}
}
