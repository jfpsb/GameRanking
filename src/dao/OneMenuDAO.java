package dao;

import java.sql.*;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import banco.Conexao;
import bean.OneMenuBean;

@ManagedBean(name="menuDAO")
@SessionScoped
public class OneMenuDAO {

	Conexao con = new Conexao();	

	public ArrayList<OneMenuBean> getGenero() {		

		Connection conn = con.getCon();
		
		ArrayList<OneMenuBean> genero = new ArrayList<OneMenuBean>();

		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT idgenero, descricao FROM genero ORDER BY descricao";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int idgenero = rs.getInt("idgenero");
				String descricao = rs.getString("descricao");
				OneMenuBean menuBean = new OneMenuBean();

				menuBean.setIdgenero(idgenero);
				menuBean.setDescricao(descricao);

				genero.add(menuBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genero;
	}
}