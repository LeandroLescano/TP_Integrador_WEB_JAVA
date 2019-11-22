package Servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import AccesoDatos.AccesoDatosManager;
import Dominio.Materia;
import Negocio.MateriaNegocio;

/**
 * Servlet implementation class servletReportes
 */
@WebServlet("/servletReportes")
public class servletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletReportes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnFiltrar") != null){
			MateriaNegocio negocioM = new MateriaNegocio();
			Materia materiaSelect = null;
			int semestre = 0;
			int Año = 0;
			String tabla = "";
			if(request.getParameter("slMateria") != null && !request.getParameter("slMateria").equals("-1")) {
				materiaSelect = negocioM.obtenerMateria(Integer.parseInt(request.getParameter("slMateria"))); 				
			}
			if(request.getParameter("slSemestre")!= null && !request.getParameter("slSemestre").equals("-1")) {
				semestre = Integer.parseInt(request.getParameter("slSemestre")); 				
			}
			if(request.getParameter("slAño") != null && !request.getParameter("slAño").equals("-1")) {
				Año = Integer.parseInt(request.getParameter("slAño")); 				
			}
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			AccesoDatosManager accesoDatos = new AccesoDatosManager();
			String listar = "Select C.ID, M.Nombre," + 
					"(Select COUNT(Situacion) from alumnos_x_curso  AS AC " + 
					"INNER JOIN CURSOS AS CS ON CS.ID = AC.IDCURSO " + 
					"INNER JOIN MATERIAS AS MS ON MS.ID = CS.IDMATERIA " + 
					"WHERE MS.NOMBRE = M.NOMBRE AND Situacion = 'Regular') as Regularizados, " + 
					"(Select Count(IDAlumno) from alumnos_x_curso AS AC " + 
					"INNER JOIN CURSOS AS CS ON CS.ID = AC.IDCURSO " + 
					"INNER JOIN MATERIAS AS MS ON MS.ID = CS.IDMATERIA " + 
					"WHERE MS.NOMBRE = M.NOMBRE) as CantAlumnos , " + 
					"((Select COUNT(Situacion) from alumnos_x_curso where Situacion = 'Regular' and IDCurso = C.ID) / (Select Count(IDAlumno) from alumnos_x_curso " + 
					"WHERE IDCurso = C.ID))*100 as 'Porcentaje' " + 
					"from cursos as c " + 
					"INNER JOIN MATERIAS AS M ON M.ID = C.IDMATERIA ";
					if(materiaSelect != null) 
						listar += "WHERE M.ID = "+materiaSelect.getID()+" ";
					else
						listar += "WHERE M.ID > 0 ";
					
					if(semestre >0) {
						if(semestre == 1)
							listar += "AND C.SEMESTRE = 'Primer' ";
						else
							listar += "AND C.SEMESTRE = 'Segundo' ";
					}
					else
						listar += "AND C.SEMESTRE in ('Primer', 'Segundo') ";
					
					if(Año > 0)
						listar += "AND C.AÑO = "+Año+" ";
					else
						listar += "AND C.AÑO > 0 ";
					
					 listar +="group by C.IDMATERIA;";
			try {
				accesoDatos.abrirConexion();
				ResultSet rs = accesoDatos.executeConsulta(listar);
				while(rs.next())
				{
					tabla += "<tr>" + 
						      "<td>" + rs.getString("Nombre") + "</td>" +
						      "<td>" + rs.getInt("CantAlumnos") + "</td>"+ 
						      "<td>" + rs.getInt("Regularizados") + "</td>" +
						      "<td>" + Math.round(rs.getFloat("Porcentaje")*100.0)/100.0 + " %</td>" + 
				   			 "</tr>";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				accesoDatos.cerrarConexion();
			}
			 request.setAttribute("tabla", tabla);
			 if(materiaSelect != null)
			 	request.setAttribute("Materia", materiaSelect.getID());
			 if(semestre != 0)
				 request.setAttribute("Semestre", semestre);
			 if(Año != 0)
				 request.setAttribute("Año", Año);
		
			 RequestDispatcher rd = request.getRequestDispatcher("/Reportes.jsp");		 
			 rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Reporte") != null){
			MateriaNegocio negocioM = new MateriaNegocio();
			Materia materiaSelect = null;
			String semestre = null;
			int Año = 0;
			String tabla = "";
			if(request.getParameter("slMateria") != null && request.getParameter("slMateria").equals("-1")) {
				materiaSelect = negocioM.obtenerMateria(Integer.parseInt(request.getParameter("slMateria"))); 				
			}
			if(request.getParameter("slSemestre")!= null && request.getParameter("slSemestre").equals("-1")) {
				semestre = request.getParameter("slMateria"); 				
			}
			if(request.getParameter("slAño") != null && request.getParameter("slAño").equals("-1")) {
				Año = Integer.parseInt(request.getParameter("slAño")); 				
			}
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			AccesoDatosManager accesoDatos = new AccesoDatosManager();
			String listar = "Select C.ID, M.Nombre," + 
					"(Select COUNT(Situacion) from alumnos_x_curso where Situacion = 'Regular' and IDCurso = C.ID) as Regularizados, " + 
					"(Select Count(IDAlumno) from alumnos_x_curso where IDCurso = C.ID) as CantAlumnos , " + 
					"((Select COUNT(Situacion) from alumnos_x_curso where Situacion = 'Regular' and IDCurso = C.ID) / (Select Count(IDAlumno) from alumnos_x_curso " + 
					"WHERE IDCurso = C.ID))*100 as 'Porcentaje' " + 
					"from cursos as c " + 
					"INNER JOIN MATERIAS AS M ON M.ID = C.IDMATERIA ";
					if(materiaSelect != null) 
						listar += "WHERE M.ID = 7 ";
					else
						listar += "WHERE M.ID > 0 ";
					
					if(semestre != null)
						listar += "AND C.SEMESTRE = '"+semestre+"' ";
					else
						listar += "AND C.SEMESTRE in ('Primer', 'Segundo') ";
					
					if(Año > 0)
						listar += "AND C.AÑO = "+Año+" ";
					else
						listar += "AND C.AÑO > 0 ";
					
					 listar +="group by C.IDMATERIA;";
			try {
				accesoDatos.abrirConexion();
				ResultSet rs = accesoDatos.executeConsulta(listar);
				while(rs.next())
				{
					tabla += "<tr>" + 
						      "<td>" + rs.getString("Nombre") + "</td>" +
						      "<td>" + rs.getInt("Regularizados") + "</td>" +
						      "<td>" + rs.getInt("CantAlumnos") + "</td>"+ 
						      "<td> %" + Math.round(rs.getFloat("Porcentaje")*100.0)/100.0 + "</td>" + 
				   			 "</tr>";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				accesoDatos.cerrarConexion();
			}
			String json = new Gson().toJson(tabla);
			
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);	
		}
		
	}		
}