package Negocio;

import Dominio.Domicilio;

import java.sql.ResultSet;
import java.sql.SQLException;

import AccesoDatos.AccesoDatosManager;

public class DomicilioNegocio {
			
	public Domicilio obtenerDomicilio(int ID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		Domicilio d = new Domicilio();
		LocalidadNegocio negocioL = new LocalidadNegocio();
		ProvinciaNegocio negocioP = new ProvinciaNegocio();
		String listar = "SELECT * FROM DOMICILIOS WHERE ID = " + ID;
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				d.setCalle(rs.getString("Calle"));
				d.setProvincia(negocioP.obtenerProvincia(rs.getInt("IDProvincia")));
				d.setLocalidad(negocioL.obtenerLocalidad(rs.getInt("IDLocalidad")));
				return d;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			accesoDatos.cerrarConexion();
		}
		
		return null;
	}
	
	public int agregarDomicilio(Domicilio d) 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		String agregar;
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		agregar = "INSERT INTO DOMICILIOS (CALLE, IDLOCALIDAD, IDPROVINCIA)"
				+ "VALUES ('"+ d.getCalle() + "', "+ d.getLocalidad().getID()+ ", " + d.getProvincia().getID() + ")";			
		try {
			accesoDatos.abrirConexion();
			return accesoDatos.executeAccionReturn(agregar);
		}
		finally {
			accesoDatos.cerrarConexion();
		}	
	}
		
}
