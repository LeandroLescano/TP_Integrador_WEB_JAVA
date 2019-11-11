package Negocio;

import Dominio.Provincia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import AccesoDatos.AccesoDatosManager;

public class ProvinciaNegocio {
	
	public ArrayList<Provincia> listarProvincias() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		String listar = "SELECT * FROM PROVINCIAS";
		ArrayList<Provincia> listado = new ArrayList<Provincia>();
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Provincia p = new Provincia();
				p.setID(rs.getInt("ID"));
				p.setNombre(rs.getString("nombre"));
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
	
	public Provincia obtenerProvincia(int ID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		String listar = "SELECT * FROM PROVINCIAS WHERE ID = " + ID;
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Provincia p = new Provincia();
				p.setID(ID);
				p.setNombre(rs.getString("nombre"));
				return p;
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
