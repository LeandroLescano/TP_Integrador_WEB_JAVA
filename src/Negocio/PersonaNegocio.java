package Negocio;

import Dominio.Domicilio;
import Dominio.Localidad;
import Dominio.Persona;
import Dominio.Provincia;
import Dominio.TipoPersona;

import java.sql.ResultSet;
import java.sql.SQLException;
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
					+ "INNER JOIN LOCALIDADES AS L ON L.ID = D.IDLOCALIDAD "
					+ "order by legajo asc"; 
		}
		try {	
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
				while(rs.next())
				{
					Persona p = new Persona();
					p.setLegajo(rs.getInt("Legajo"));
					p.setApellido(rs.getString("Apellido"));
					p.setNombre(rs.getString("Nombre"));
					p.setFechNac(rs.getDate("FechaNacimiento").toLocalDate());
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
				p.setLegajo(rs.getInt("Legajo"));
				p.setApellido(rs.getString("Apellido"));
				p.setNombre(rs.getString("Nombre"));
				p.setFechNac(rs.getDate("FechaNacimiento").toLocalDate());
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
	
	public String obtenerNombre(int ID, char Tipo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		String listar;
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		if(Tipo == 'P') {
			listar = "SELECT APELLIDO, NOMBRE FROM PROFESORES WHERE LEGAJO = " + ID;  
		}
		else {
			listar = "SELECT APELLIDO, NOMBRE FROM ALUMNOS WHERE LEGAJO = " + ID; 
		}
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Persona p = new Persona();
				p.setApellido(rs.getString("Apellido"));
				p.setNombre(rs.getString("Nombre"));
				return p.getApellido() + ", " + p.getNombre();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			accesoDatos.cerrarConexion();
		}
		
		return null;
	}
	
	public boolean eliminarPersona(int ID, char Tipo) 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		String eliminar;
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		if(Tipo == 'P') {	
			eliminar = "UPDATE PROFESORES SET ESTADO = 0 WHERE LEGAJO = " + ID; 
		}
		else {
			eliminar = "UPDATE ALUMNOS SET ESTADO = 0 WHERE LEGAJO = " + ID; 
		}
		try {
			accesoDatos.abrirConexion();
			if(accesoDatos.executeAccion((eliminar)) > 0){
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
	
	public boolean agregarPersona(Persona p) 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		String agregar;
		String FechaNac = p.getFechNac().toString();
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		if(p.getTipo().getID() == 1) {	
			agregar = "INSERT INTO PROFESORES (APELLIDO, NOMBRE, IDDOMICILIO, IDTIPO, MAIL, TELEFONO, FECHANACIMIENTO) "
 					+ "VALUES ('"+p.getApellido() + "', '"+ p.getNombre() + "', " + p.getDomicilio().getID() + ", 1, '" + p.getMail() + "', '" + p.getTelefono() + "', '" + FechaNac + "')";
		}
		else {
			agregar = "INSERT INTO ALUMNOS (APELLIDO, NOMBRE, IDDOMICILIO, IDTIPO, MAIL, TELEFONO, FECHANACIMIENTO) "
 					+ "VALUES ('"+p.getApellido() + "', '"+ p.getNombre() + "', " + p.getDomicilio().getID() + ", 2, '" + p.getMail() + "', '" + p.getTelefono() + "', '" + FechaNac + "')"; 
		}
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
	
	public boolean modificarPersona(Persona p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		String modificar;
		String FechaNac = p.getFechNac().toString();
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		if(p.getTipo().getID() == 1) {	
			modificar = "UPDATE PROFESORES SET APELLIDO='"+p.getApellido() + "', NOMBRE ='"+ p.getNombre() + "', IDDOMICILIO=" + p.getDomicilio().getID() + ", "
					+ "MAIL='" + p.getMail() + "', TELEFONO='" + p.getTelefono() + "', FECHANACIMIENTO='" + FechaNac + "' WHERE LEGAJO = "+ p.getLegajo();
		}
		else {
			modificar = "UPDATE ALUMNOS SET APELLIDO='"+p.getApellido() + "', NOMBRE ='"+ p.getNombre() + "', IDDOMICILIO=" + p.getDomicilio().getID() + ", "
					+ "MAIL='" + p.getMail() + "', TELEFONO='" + p.getTelefono() + "', FECHANACIMIENTO='" + FechaNac + "' WHERE LEGAJO = "+ p.getLegajo(); 
		}
		try {
			accesoDatos.abrirConexion();
			if(accesoDatos.executeAccion((modificar)) > 0){
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
	
	public int nuevoLegajo(char Tipo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		String nuevoLegajo;
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		if(Tipo == 'P') {	
			nuevoLegajo = "SELECT MAX(LEGAJO)+1 as legajo FROM PROFESORES"; 
		}
		else {
			nuevoLegajo = "SELECT MAX(LEGAJO)+1 as legajo FROM ALUMNOS"; 
		}
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(nuevoLegajo);
			while(rs.next())
			{
				return rs.getInt("legajo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			accesoDatos.cerrarConexion();
		}
		return -1;	
	}

	public int validarIngreso(String email, String contra) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		String Usuario;
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		Usuario = "SELECT CONTRASEÑA, IDPROFESOR, TIPO FROM USUARIOS WHERE EMAIL = '" + email + "'"; 
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(Usuario);
			while(rs.next())
			{
				if(contra.equals(rs.getString("Contraseña"))) {
					if(rs.getString("Tipo").equals("Profesor")) {
						return rs.getInt("IDProfesor");						
					}
					else {
						return 0;
					}
				}
				else {
					return -1;
				}
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
