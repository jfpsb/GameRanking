package banco;

import java.sql.*;

public class Conexao {
	private Connection con;
	private String url = "jdbc:mysql://localhost/";
	private String banco = "bdgameranking";
	private String usuario = "root";
	private String senha = "1124";
	
	public Conexao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url + banco, usuario, senha);
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}	
}
