package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.OneMenuDAO;

@ManagedBean(name="oneMenuBean")
@SessionScoped
public class OneMenuBean {
	private int idgenero;
	private String descricao;
	OneMenuDAO menuDAO = new OneMenuDAO();
	
	public int getIdgenero() {
		return idgenero;
	}
	public void setIdgenero(int idgenero) {
		this.idgenero = idgenero;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
}