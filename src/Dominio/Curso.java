package Dominio;

import java.util.ArrayList;

public class Curso {

	private int ID;
	private Materia Materia;
	private String Semestre;
	private int A�o;
	private Persona Profesor;
	private ArrayList<Persona> Alumnos;
	
	public Curso() {
		
	}
	
	public Curso(int iD, Materia materia , String semestre, int a�o, Persona profesor, ArrayList<Persona> alumnos) {
		super();
		ID = iD;
		Materia = materia;
		Semestre = semestre;
		A�o = a�o;
		Profesor = profesor;
		Alumnos = alumnos;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Materia getMateria() {
		return Materia;
	}

	public void setMateria(Materia materia) {
		Materia = materia;
	}

	public String getSemestre() {
		return Semestre;
	}

	public void setSemestre(String semestre) {
		Semestre = semestre;
	}

	public int getA�o() {
		return A�o;
	}

	public void setA�o(int a�o) {
		A�o = a�o;
	}

	public Persona getProfesor() {
		return Profesor;
	}

	public void setProfesor(Persona profesor) {
		Profesor = profesor;
	}

	public ArrayList<Persona> getAlumnos() {
		return Alumnos;
	}

	public void setAlumnos(ArrayList<Persona> alumnos) {
		Alumnos = alumnos;
	}
}
