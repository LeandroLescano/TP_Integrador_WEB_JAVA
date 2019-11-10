package Negocio;

import Dominio.Alumno;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import AccesoDatos.AccesoDatosManager;

public class AlumnoNegocio {
		
	public ArrayList<Alumno> listarAlumnos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		PersonaNegocio negocioP = new PersonaNegocio();
		ArrayList<Alumno> listado = new ArrayList<Alumno>();
		String listar = "SELECT * FROM Alumnos";
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Alumno a = new Alumno();
				a.setParcial1(rs.getFloat("Par1"));
				a.setParcial2(rs.getFloat("Par2"));
				a.setRecuperatorio1(rs.getFloat("Rec1"));
				a.setRecuperatorio2(rs.getFloat("Rec2"));
				a.setNotaFinal(rs.getFloat("NotaFinal"));
				a.setSituacion(rs.getString("Situacion"));
				a = (Alumno) negocioP.obtenerPersona(rs.getInt("Legajo"), 'A');
				listado.add(a);
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
	
	public Alumno obtenerAlumno(int ID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		PersonaNegocio negocioP = new PersonaNegocio();
		String listar = "SELECT * FROM Alumnos where Legajo = " + ID;
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Alumno a = new Alumno();
				a.setParcial1(rs.getFloat("Par1"));
				a.setParcial2(rs.getFloat("Par2"));
				a.setRecuperatorio1(rs.getFloat("Rec1"));
				a.setRecuperatorio2(rs.getFloat("Rec2"));
				a.setNotaFinal(rs.getFloat("NotaFinal"));
				a.setSituacion(rs.getString("Situacion"));
				a = (Alumno) negocioP.obtenerPersona(rs.getInt("Legajo"), 'A');
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			accesoDatos.cerrarConexion();
		}
		
		return null;
	}
		
}
