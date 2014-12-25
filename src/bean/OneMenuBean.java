package bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.OneMenuDAO;
import entidade.OneMenuEnt;

@ManagedBean(name="oneMenuBean")
@RequestScoped
public class OneMenuBean {
	OneMenuDAO oneMenuDAO = new OneMenuDAO();
	OneMenuEnt oneMenuEnt = new OneMenuEnt();
	
	public ArrayList <OneMenuEnt> listar () {
		return oneMenuDAO.getGenero();
	}
}