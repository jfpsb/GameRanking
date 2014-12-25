package entidade;

public class GameEnt {
	int idgame;
	String titulo, marca, plataforma, faixaetaria, idgenero;
	
	public int getIdgame() {
		return idgame;
	}
	public void setIdgame(int idgame) {
		this.idgame = idgame;
	}
	public String getIdgenero() {
		return idgenero;
	}
	public void setIdgenero(String idgenero) {
		this.idgenero = idgenero;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public String getFaixaetaria() {
		return faixaetaria;
	}
	public void setFaixaetaria(String faixaetaria) {
		this.faixaetaria = faixaetaria;
	}
}
