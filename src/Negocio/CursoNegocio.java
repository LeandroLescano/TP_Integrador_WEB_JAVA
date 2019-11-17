package Negocio;

import Dominio.Curso;
import Dominio.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import AccesoDatos.AccesoDatosManager;

public class CursoNegocio {
	
	public boolean agregarCurso(Curso c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		String agregar;
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		agregar = "INSERT INTO CURSOS (IDMATERIA, IDPROFESOR, AÑO, SEMESTRE)"
				+ " VALUES ("+c.getMateria().getID()+", "+c.getProfesor().getLegajo()+", " +c.getAño()+ ", '"+c.getSemestre()+"')";
		try {
			accesoDatos.abrirConexion();
			if(accesoDatos.executeAccion((agregar)) > 0){
				return true;			
			}
			else{
				return false;
			}
		}
		finally {
			accesoDatos.cerrarConexion();
		}	
	}
		
	public ArrayList<Curso> listarCursos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		PersonaNegocio negocioP = new PersonaNegocio();
		MateriaNegocio negocioM = new MateriaNegocio();
		ArrayList<Curso> listado = new ArrayList<Curso>();
		String listar = "SELECT * FROM CURSOS";
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Curso c = new Curso();
				c.setID(rs.getInt("ID"));
				c.setAño(rs.getInt("Año"));
				c.setMateria(negocioM.obtenerMateria(rs.getInt("IDMateria")));
				c.setProfesor(negocioP.obtenerPersona(rs.getInt("IDProfesor"), 'P'));
				c.setSemestre(rs.getString("Semestre"));
				listado.add(c);
			}
			return listado;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			accesoDatos.cerrarConexion();
		}
		
		return null;
	}
	
	public Curso obtenerCurso(int ID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		PersonaNegocio negocioP = new PersonaNegocio();
		MateriaNegocio negocioM = new MateriaNegocio();
		String listar = "SELECT * FROM CURSOS where Legajo = " + ID;
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Curso c = new Curso();
				c.setID(rs.getInt("ID"));
				c.setAño(rs.getInt("Año"));
				c.setMateria(negocioM.obtenerMateria(rs.getInt("IDMateria")));
				c.setProfesor(negocioP.obtenerPersona(rs.getInt("IDDocente"), 'P'));
				c.setSemestre(rs.getString("Semestre"));
				c.setAlumnos(listarAlumnosCurso(c.getID()));
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			accesoDatos.cerrarConexion();
		}
		
		return null;
	}
	
	public ArrayList<Persona> listarAlumnosCurso(int IDCurso) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		PersonaNegocio negocioP = new PersonaNegocio();
		ArrayList<Persona> listado = new ArrayList<Persona>();
		String listar = "SELECT * FROM ALUMNOS_X_CURSO where IDCurso = " + IDCurso;
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Persona p = new Persona();
				p = negocioP.obtenerPersona(rs.getInt("IDAlumno"), 'A');
				listado.add(p);
			}
			return listado;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			accesoDatos.cerrarConexion();
		}
		return null;
	}
		
	public boolean agregarAlumnosCurso(String[] Legajos, int IDCurso) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		String agregar;
		try {
			accesoDatos.abrirConexion();
			for(String l : Legajos) {
				agregar = "INSERT INTO ALUMNOS_X_CURSO (IDALUMNO, IDCURSO) VALUE ("+ l +", " + IDCurso + ")";
				if(accesoDatos.executeAccion((agregar)) > 0){			
				}
				else{
					return false;
				}			
			}
			return true;
		}
		finally {
			accesoDatos.cerrarConexion();
		}			
	}
	
	public int nuevoID() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		String nuevoID;
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		nuevoID = "SELECT MAX(ID)+1 as ID FROM CURSOS"; 

		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(nuevoID);
			while(rs.next())
			{
				return rs.getInt("ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			accesoDatos.cerrarConexion();
		}
		return -1;	
	}
}
