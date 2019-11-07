package Dominio;

public class Direccion {

	private int ID;	
	private String Calle;
	private String Localidad;
	private Provincia Provincia;
	
	public Direccion() {
		
	}
	
	public Direccion(int iD, String calle, String localidad, Dominio.Provincia provincia) {
		super();
		ID = iD;
		Calle = calle;
		Localidad = localidad;
		Provincia = provincia;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getCalle() {
		return Calle;
	}
	public void setCalle(String calle) {
		Calle = calle;
	}
	public String getLocalidad() {
		return Localidad;
	}
	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}
	public Provincia getProvincia() {
		return Provincia;
	}
	public void setProvincia(Provincia provincia) {
		Provincia = provincia;
	}		
		
}
