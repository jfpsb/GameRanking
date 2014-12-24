package dao;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entidade.UsuarioEnt;
import banco.Conexao;

@ManagedBean(name="usuarioDAO")
@SessionScoped
public class UsuarioDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	Conexao con = new Conexao();
	
	public void salvar(UsuarioEnt usuario) {
		Connection conn = con.getCon();
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
			context.addMessage(null, new FacesMessage("Sucesso",  "Usuário cadastrado com sucesso."));
		} catch (SQLException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro",  "Você escolheu um login repetido. Tente outro."));
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
	
	public void deletar(UsuarioEnt usuario) {
		Connection conn = con.getCon();
		PreparedStatement deleteSt = null;
		
		String sql = "DELETE FROM usuario WHERE idusuario = ?";
		
		try {
			deleteSt = conn.prepareStatement(sql);
			deleteSt.setInt(1, usuario.getIdusuario());
			deleteSt.execute();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Sucesso. Usuário deletado."));
		} catch (SQLException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro. Houve um erro ao deletar usuário."));
		} finally {
			try {
				deleteSt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}	

	public ArrayList<UsuarioEnt> pesquisaAluno() {

		Connection conn = con.getCon();
		
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
		}
		return dadosUser;
	}
}
