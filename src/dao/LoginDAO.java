package dao;

import java.sql.*;

import banco.Conexao;

public class LoginDAO {
	Conexao conexao = new Conexao();
	Connection conn;
	Statement consulta;
	ResultSet rs;
	
	public String getSenhaBD (String login) {
		conn = conexao.getCon();
		consulta = null;
		rs = null;
		String senhaBD = null;
		String sql = "SELECT senha FROM usuario WHERE idusuario = " + login;
		
		try {
			consulta = conn.createStatement();
			rs = consulta.executeQuery(sql);
			
			while(rs.next()) {
				senhaBD = rs.getString("senha");
			}
		} catch (SQLException e) {
			System.out.println("erro");
		} 
		
		return senhaBD;
	}
	
	public boolean isADM (String login) {
		conn = conexao.getCon();
		consulta = null;
		rs = null;
		boolean admin = false;
		String sql = "SELECT admin FROM usuario WHERE idusuario = " + login;
		
		try {
			consulta = conn.createStatement();
			rs = consulta.executeQuery(sql);
			
			while(rs.next()) {
				admin = rs.getBoolean("admin");
			}
		} catch (SQLException e) {
			System.out.println("erro");
			e.printStackTrace();
		} finally {
			try {
			rs.close();
			consulta.close();
			conn.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}	
		}
		
		return admin;
	}
}
