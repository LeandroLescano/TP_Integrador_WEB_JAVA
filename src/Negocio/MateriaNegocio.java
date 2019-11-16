package Negocio;

import Dominio.Materia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import AccesoDatos.AccesoDatosManager;

public class MateriaNegocio {
			
	public Materia obtenerMateria(int ID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		String listar = "SELECT * FROM MATERIAS WHERE ID = " + ID;
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Materia m = new Materia();
				m.setID(ID);
				m.setNombre(rs.getString("nombre"));
				return m;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			accesoDatos.cerrarConexion();
		}
		
		return null;
	}
	
	public ArrayList<Materia> listarMaterias() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		ArrayList<Materia> listado = new ArrayList<Materia>();
		String listar = "SELECT * FROM MATERIAS ORDER BY NOMBRE DESC";
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Materia m = new Materia();
				m.setID(rs.getInt("ID"));
				m.setNombre(rs.getString("nombre"));
				listado.add(m);
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
}
