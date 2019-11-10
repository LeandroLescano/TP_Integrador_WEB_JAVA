package Negocio;

import Dominio.TipoPersona;

import java.sql.ResultSet;
import java.sql.SQLException;

import AccesoDatos.AccesoDatosManager;

public class TipoPersonaNegocio {
			
	public TipoPersona obtenerTipo(int ID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		String listar = "SELECT * FROM TIPOS_PERSONA WHERE ID = " + ID;
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				TipoPersona p = new TipoPersona();
				p.setID(ID);
				p.setTipo(rs.getString("nombre"));
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
