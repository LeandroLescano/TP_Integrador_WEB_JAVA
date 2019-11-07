package Dominio;

public class TipoPersona {

	private int ID;
	private String Tipo;
	
	public TipoPersona() {
		
	}
	
	public TipoPersona(int iD, String tipo) {
		super();
		ID = iD;
		Tipo = tipo;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	
}
