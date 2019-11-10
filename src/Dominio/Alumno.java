package Dominio;

public class Alumno extends Persona{

	private float Parcial1;
	private float Recuperatorio1;
	private float Parcial2;
	private float Recuperatorio2;
	private float NotaFinal;
	private String Situacion;
	
	
	public Alumno()
	{
		
	}

	public Alumno(float parcial1, float recuperatorio1, float parcial2, float recuperatorio2, float notaFinal,
			String situacion) {
		super();
		Parcial1 = parcial1;
		Recuperatorio1 = recuperatorio1;
		Parcial2 = parcial2;
		Recuperatorio2 = recuperatorio2;
		NotaFinal = notaFinal;
		Situacion = situacion;
	}

	public float getParcial1() {
		return Parcial1;
	}


	public void setParcial1(float parcial1) {
		Parcial1 = parcial1;
	}


	public float getRecuperatorio1() {
		return Recuperatorio1;
	}


	public void setRecuperatorio1(float recuperatorio1) {
		Recuperatorio1 = recuperatorio1;
	}


	public float getParcial2() {
		return Parcial2;
	}


	public void setParcial2(float parcial2) {
		Parcial2 = parcial2;
	}


	public float getRecuperatorio2() {
		return Recuperatorio2;
	}


	public void setRecuperatorio2(float recuperatorio2) {
		Recuperatorio2 = recuperatorio2;
	}


	public float getNotaFinal() {
		return NotaFinal;
	}


	public void setNotaFinal(float notaFinal) {
		NotaFinal = notaFinal;
	}


	public String getSituacion() {
		return Situacion;
	}


	public void setSituacion(String situacion) {
		Situacion = situacion;
	}
	
	
	
}
