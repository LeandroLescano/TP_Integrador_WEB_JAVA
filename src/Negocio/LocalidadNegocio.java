package Negocio;

import Dominio.Localidad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import AccesoDatos.AccesoDatosManager;

public class LocalidadNegocio {
			
	public Localidad obtenerLocalidad(int ID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		String listar = "SELECT * FROM LOCALIDADES WHERE ID = " + ID;
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Localidad l = new Localidad();
				l.setID(ID);
				l.setNombre(rs.getString("nombre"));
				return l;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			accesoDatos.cerrarConexion();
		}
		
		return null;
	}
		
	public ArrayList<Localidad> listarLocalidad(int IDProv){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		ArrayList<Localidad> listado = new ArrayList<Localidad>();
		String listar = "SELECT * FROM LOCALIDADES WHERE PROVINCIA_ID = " + IDProv + " ORDER BY NOMBRE ASC";
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Localidad l = new Localidad();
				l.setID(rs.getInt("id"));
				l.setNombre(rs.getString("nombre"));
				listado.add(l);
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
