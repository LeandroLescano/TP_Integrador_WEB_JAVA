package Dominio;

public class Materia {

	private int ID;
	private String Nombre;
	
	public Materia() {

	}
	
	public Materia(int iD, String nombre) {
		super();
		ID = iD;
		Nombre = nombre;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	
	
}
