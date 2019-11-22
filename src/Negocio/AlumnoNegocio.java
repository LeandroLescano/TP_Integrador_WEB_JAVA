package Negocio;

import Dominio.Alumno;
import Dominio.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import AccesoDatos.AccesoDatosManager;

public class AlumnoNegocio {
		
	public ArrayList<Alumno> listarAlumnos(int IDCurso) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
		PersonaNegocio negocioP = new PersonaNegocio();
		ArrayList<Alumno> listado = new ArrayList<Alumno>();
		String listar = "SELECT IDAlumno, NotaFinal, Situacion, Par1, Rec1, Par2, Rec2, IDCurso FROM ALUMNOS_X_CURSO " + 
						"WHERE IDCurso = " + IDCurso;
		try {
			accesoDatos.abrirConexion();
			ResultSet rs = accesoDatos.executeConsulta(listar);
			while(rs.next())
			{
				Alumno a = new Alumno();
				a.setLegajo(rs.getInt("IDAlumno"));
				a.setParcial1(rs.getFloat("Par1"));
				a.setParcial2(rs.getFloat("Par2"));
				a.setRecuperatorio1(rs.getFloat("Rec1"));
				a.setRecuperatorio2(rs.getFloat("Rec2"));
				a.setNotaFinal(rs.getFloat("NotaFinal"));
				if(rs.getString("Situacion") != null)
					a.setSituacion(rs.getString("Situacion"));	
				else
					a.setSituacion("");
				Persona p = negocioP.obtenerPersona(rs.getInt("IDAlumno"), 'A');
				a.setApellido(p.getApellido());
				a.setNombre(p.getNombre());
				a.setDomicilio(p.getDomicilio());
				a.setFechNac(p.getFechNac());
				a.setTipo(p.getTipo());
				a.setTelefono(p.getTelefono());
				a.setMail(p.getMail());
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

	public boolean cargarNotas(Alumno a, int IDCurso) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		String modificar;
		AccesoDatosManager accesoDatos = new AccesoDatosManager();
			modificar = "UPDATE ALUMNOS_X_CURSO SET Par1="+ a.getParcial1() + ", Rec1="+ a.getRecuperatorio1() + ", Par2=" + a.getParcial2() + ", "
						+ "Rec2=" + a.getRecuperatorio2() + ", NotaFinal=" + a.getNotaFinal() + ", SITUACION='" + a.getSituacion() + "' WHERE IDALUMNO = "+ a.getLegajo()
						+ " AND IDCURSO = " + IDCurso; 
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
	
//	public Alumno obtenerAlumno(int ID) {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		}
//		catch(ClassNotFoundException e){
//			e.printStackTrace();
//		}
//		AccesoDatosManager accesoDatos = new AccesoDatosManager();
//		PersonaNegocio negocioP = new PersonaNegocio();
//		String listar = "SELECT * FROM Alumnos where Legajo = " + ID;
//		try {
//			accesoDatos.abrirConexion();
//			ResultSet rs = accesoDatos.executeConsulta(listar);
//			while(rs.next())
//			{
//				Alumno a = new Alumno();
//				a.setParcial1(rs.getFloat("Par1"));
//				a.setParcial2(rs.getFloat("Par2"));
//				a.setRecuperatorio1(rs.getFloat("Rec1"));
//				a.setRecuperatorio2(rs.getFloat("Rec2"));
//				a.setNotaFinal(rs.getFloat("NotaFinal"));
//				a.setSituacion(rs.getString("Situacion"));
//				a = (Alumno) negocioP.obtenerPersona(rs.getInt("Legajo"), 'A');
//				return a;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			accesoDatos.cerrarConexion();
//		}
//		
//		return null;
//	}
		
}
