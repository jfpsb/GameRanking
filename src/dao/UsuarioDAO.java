package dao;

import java.sql.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import entidade.UsuarioEnt;
import banco.Conexao;

public class UsuarioDAO {
	
	Conexao conexao = new Conexao();
	
	public void salvar(UsuarioEnt usuario) {
		Connection conn = conexao.getCon();
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
}
