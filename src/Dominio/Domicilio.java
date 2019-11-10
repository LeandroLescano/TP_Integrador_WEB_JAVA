package Dominio;

public class Domicilio {

	private int ID;	
	private String Calle;
	private Localidad Localidad;
	private Provincia Provincia;
	
	public Domicilio() {
		
	}
		
	public Domicilio(int iD, String calle, Localidad localidad, Provincia provincia) {
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

	public Localidad getLocalidad() {
		return Localidad;
	}


	public void setLocalidad(Localidad localidad) {
		Localidad = localidad;
	}
	public Provincia getProvincia() {
		return Provincia;
	}
	public void setProvincia(Provincia provincia) {
		Provincia = provincia;
	}		
		
}
