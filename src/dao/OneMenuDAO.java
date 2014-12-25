package dao;

import java.sql.*;
import java.util.ArrayList;

import entidade.OneMenuEnt;
import banco.Conexao;

public class OneMenuDAO {

	Conexao con = new Conexao();	

	public ArrayList<OneMenuEnt> getGenero() {

		Connection conn = con.getCon();
		
		ArrayList<OneMenuEnt> genero = new ArrayList<OneMenuEnt>();

		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT idgenero, descricao FROM genero ORDER BY descricao";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int idgenero = rs.getInt("idgenero");
				String descricao = rs.getString("descricao");
				OneMenuEnt oneMenuEnt = new OneMenuEnt();

				oneMenuEnt.setIdgenero(idgenero);
				oneMenuEnt.setDescricao(descricao);

				genero.add(oneMenuEnt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genero;
	}
}