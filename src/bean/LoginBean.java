package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.LoginDAO;
import entidade.LoginEnt;

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean {
	
	LoginEnt loginEnt = new LoginEnt();
	
	public LoginEnt getLoginEnt() {
		return loginEnt;
	}

	public void setLoginEnt(LoginEnt loginEnt) {
		this.loginEnt = loginEnt;
	}
	
	public String entrar() {
		return "login";
	}

	public String autenticacao() {
		LoginDAO loginDAO = new LoginDAO();
		String login;
		String senhaBD, senha;
		boolean isAdm;
		
		login = loginEnt.getLogin();
		senha = loginEnt.getSenha();
		senhaBD = loginDAO.getSenhaBD(login);
		isAdm = loginDAO.isADM(login);		
		
		if(senha.equals(senhaBD)) {
			if (isAdm==false) {
				return "usuario/telaUsuario";
			} else {
				return "adm/telaADM";
			}
		}
		return "loginErrado";
	}
}
