package entidade;

public class LoginEnt {
	private String login, senha, senhaBD=null;
	private boolean isAdm;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhaBD() {
		return senhaBD;
	}

	public void setSenhaBD(String senhaBD) {
		this.senhaBD = senhaBD;
	}
	
	public boolean isAdm() {
		return isAdm;
	}

	public void setAdm(boolean isAdm) {
		this.isAdm = isAdm;
	}
}
