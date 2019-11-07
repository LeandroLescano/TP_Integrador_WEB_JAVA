package Negocio;

import Dominio.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import AccesoDatos.AccesoDatosManager;

public class AlumnoNegocio {
		
	public ArrayList<Persona> listarAlumnos()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		ArrayList<Persona> lista = new ArrayList<Persona>();
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		String listar = "SELECT * FROM ALUMNOS";
		ResultSet rs = accesoDatos.executeConsulta(listar);
		try {
			while(rs.next())
			{
				Persona a = new Persona();
				a.setLegajo(rs.getInt("legajo"));
				a.setApellido(rs.getString("apellido"));
				a.setNombre(rs.getString("nombre"));
				lista.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public Persona obtenerAlumno() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		String listar = "SELECT * FROM ALUMNOS";
		ResultSet rs = accesoDatos.executeConsulta(listar);
		try {
			while(rs.next())
			{
				Persona a = new Persona();
				a.setLegajo(rs.getInt("legajo"));
				a.setApellido(rs.getString("apellido"));
				a.setNombre(rs.getString("nombre"));
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
		
}
