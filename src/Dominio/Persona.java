package Dominio;

import java.time.LocalDate;

public class Persona {

	private int Legajo;
	private String Apellido;
	private String Nombre;
	private LocalDate FechNac;
	private String Mail;
	private String Telefono;
	private Domicilio Domicilio;
	private TipoPersona Tipo;
	private boolean Estado;
	
	public Persona()
	{
		
	}

	public Persona(int legajo, String apellido, String nombre, LocalDate fechNac, String mail, String telefono,
			Domicilio domicilio, TipoPersona tipo, boolean estado) {
		super();
		Legajo = legajo;
		Apellido = apellido;
		Nombre = nombre;
		FechNac = fechNac;
		Mail = mail;
		Telefono = telefono;
		Domicilio = domicilio;
		Tipo = tipo;
		Estado = estado;
	}



	public boolean isEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}

	public int getLegajo() {
		return Legajo;
	}

	public void setLegajo(int legajo) {
		Legajo = legajo;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public LocalDate getFechNac() {
		return FechNac;
	}

	public void setFechNac(LocalDate fechNac) {
		FechNac = fechNac;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String teléfono) {
		Telefono = teléfono;
	}

	public Domicilio getDomicilio() {
		return Domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		Domicilio = domicilio;
	}

	public TipoPersona getTipo() {
		return Tipo;
	}

	public void setTipo(TipoPersona tipo) {
		Tipo = tipo;
	}
	
}
