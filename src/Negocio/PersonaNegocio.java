package Negocio;

import Dominio.Domicilio;
import Dominio.Localidad;
import Dominio.Persona;
import Dominio.Provincia;
import Dominio.TipoPersona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import AccesoDatos.AccesoDatosManager;

public class PersonaNegocio {
		
	public ArrayList<Persona> listarPersonas(char Tipo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		String listar;
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
//		DomicilioNegocio negocioD = new DomicilioNegocio();
//		TipoPersonaNegocio negocioTP = new TipoPersonaNegocio();
		ArrayList<Persona> listado = new ArrayList<Persona>();
		if(Tipo == 'P') {
//			listar = "SELECT * FROM PROFESORES";	
			listar = "SELECT P.*, D.* , TP.Nombre as 'Tipo', PROV.NOMBRE AS 'Provincia', L.Nombre as 'Localidad' FROM PROFESORES AS P "
					+ "INNER JOIN DOMICILIOS AS D ON D.ID = P.IDDOMICILIO "
					+ "INNER JOIN TIPOS_PERSONA AS TP ON TP.ID = P.IDTIPO "
					+ "INNER JOIN PROVINCIAS AS PROV ON PROV.ID = D.IDPROVINCIA "
					+ "INNER JOIN LOCALIDADES AS L ON L.ID = D.IDLOCALIDAD"; 
		}
		else {
//			listar = "SELECT * FROM ALUMNOS";
			listar = "SELECT A.*, D.* , TP.Nombre as 'Tipo', PROV.NOMBRE AS 'Provincia', L.Nombre as 'Localidad' FROM ALUMNOS AS A "
					+ "INNER JOIN DOMICILIOS AS D ON D.ID = A.IDDOMICILIO "
					+ "INNER JOIN TIPOS_PERSONA AS TP ON TP.ID = A.IDTIPO "
					+ "INNER JOIN PROVINCIAS AS PROV ON PROV.ID = D.IDPROVINCIA "
					+ "INNER JOIN LOCALIDADES AS L ON L.ID = D.IDLOCALIDAD"; 
		}
		try {	
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy"); 
				while(rs.next())
				{
					Persona p = new Persona();
					p.setLegajo(rs.getInt("Legajo"));
					p.setApellido(rs.getString("Apellido"));
					p.setNombre(rs.getString("Nombre"));
					try {
						p.setFechNac(dateFormat.parse(rs.getDate("FechaNacimiento").toString()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					p.setEstado(rs.getBoolean("Estado"));
					if(rs.getString("Mail") != null){
						p.setMail(rs.getString("Mail"));
					}
					else
					{
						p.setMail("Sin registro");
					}
					if(rs.getString("Telefono") != null){
						p.setTelefono(rs.getString("Telefono"));	
					}
					else {
						p.setTelefono("Sin registro");
					}
					TipoPersona tp = new TipoPersona();
					tp.setID(rs.getInt("IDTipo"));
					tp.setTipo(rs.getString("Tipo"));
					p.setTipo(tp);
					Domicilio d = new Domicilio();
					d.setID(rs.getInt("IDDomicilio"));
					d.setCalle(rs.getString("Calle"));
					Localidad l = new Localidad();
					l.setID(rs.getInt("IDLocalidad"));
					l.setNombre(rs.getString("Localidad"));
					d.setLocalidad(l);
					Provincia prov = new Provincia();
					prov.setID(rs.getInt("IDProvincia"));
					prov.setNombre(rs.getString("Provincia"));
					d.setProvincia(prov);
					p.setDomicilio(d);
//					p.setTipo(negocioTP.obtenerTipo(rs.getInt("IDTipo")));
//					p.setDomicilio(negocioD.obtenerDomicilio(rs.getInt("IDDomicilio")));
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
	
	public Persona obtenerPersona(int ID, char Tipo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		String listar;
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
//		DomicilioNegocio negocioD = new DomicilioNegocio();
//		TipoPersonaNegocio negocioTP = new TipoPersonaNegocio();
		if(Tipo == 'P') {
//			listar = "SELECT * FROM PROFESORES where Legajo = " + ID;		
			listar = "SELECT P.*, D.* , TP.Nombre as 'Tipo', PROV.NOMBRE AS 'Provincia', L.Nombre as 'Localidad' FROM PROFESORES AS P "
					+ "INNER JOIN DOMICILIOS AS D ON D.ID = P.IDDOMICILIO "
					+ "INNER JOIN TIPOS_PERSONA AS TP ON TP.ID = P.IDTIPO "
					+ "INNER JOIN PROVINCIAS AS PROV ON PROV.ID = D.IDPROVINCIA "
					+ "INNER JOIN LOCALIDADES AS L ON L.ID = D.IDLOCALIDAD WHERE LEGAJO = " + ID;  
		}
		else {
//			listar = "SELECT * FROM ALUMNOS where Legajo = " + ID;
			listar = "SELECT A.*, D.* , TP.Nombre as 'Tipo', PROV.NOMBRE AS 'Provincia', L.Nombre as 'Localidad' FROM ALUMNOS AS A "
					+ "INNER JOIN DOMICILIOS AS D ON D.ID = A.IDDOMICILIO "
					+ "INNER JOIN TIPOS_PERSONA AS TP ON TP.ID = A.IDTIPO "
					+ "INNER JOIN PROVINCIAS AS PROV ON PROV.ID = D.IDPROVINCIA "
					+ "INNER JOIN LOCALIDADES AS L ON L.ID = D.IDLOCALIDAD WHERE LEGAJO = " + ID; 
		}
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Persona p = new Persona();
				p.setLegajo(rs.getInt("ID"));
				p.setApellido(rs.getString("Apellido"));
				p.setNombre(rs.getString("Nombre"));
				p.setFechNac(rs.getDate("FechaNacimiento"));
				p.setEstado(rs.getBoolean("Estado"));
				if(rs.getString("Mail") != null){
					p.setMail(rs.getString("Mail"));
				}
				else
				{
					p.setMail("");
				}
				if(rs.getString("Telefono") != null){
					p.setTelefono(rs.getString("Telefono"));	
				}
				else {
					p.setTelefono("");
				}
				TipoPersona tp = new TipoPersona();
				tp.setID(rs.getInt("IDTipo"));
				tp.setTipo(rs.getString("Tipo"));
				p.setTipo(tp);
				Domicilio d = new Domicilio();
				d.setID(rs.getInt("IDDomicilio"));
				d.setCalle(rs.getString("Calle"));
				Localidad l = new Localidad();
				l.setID(rs.getInt("IDLocalidad"));
				l.setNombre(rs.getString("Localidad"));
				d.setLocalidad(l);
				Provincia prov = new Provincia();
				prov.setID(rs.getInt("IDProvincia"));
				prov.setNombre(rs.getString("Provincia"));
				d.setProvincia(prov);
				p.setDomicilio(d);
//				p.setTipo(negocioTP.obtenerTipo(rs.getInt("IDTipo")));
//				p.setDomicilio(negocioD.obtenerDomicilio(rs.getInt("IDDomicilio")));
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
