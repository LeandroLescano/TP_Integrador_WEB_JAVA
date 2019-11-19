package Servlets;

import java.io.IOException;

import java.util.ArrayList;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dominio.Alumno;
import Dominio.Curso;
import Dominio.Localidad;
import Dominio.Persona;
import Negocio.AlumnoNegocio;
import Negocio.CursoNegocio;
import Negocio.LocalidadNegocio;
import Negocio.MateriaNegocio;
import Negocio.PersonaNegocio;

/**
 * Servlet implementation class servletMisCursos
 */
@WebServlet("/servletMisCursos")
public class servletMisCursos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletMisCursos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CursoNegocio negocioC = new CursoNegocio();
		HttpSession session = request.getSession();
		int IDProfesor = -1;
		if(session.getAttribute("IDProfesor") == null) {
			response.sendRedirect("/Inicio.jsp");
		}
		else {
			IDProfesor = Integer.parseInt(session.getAttribute("IDProfesor").toString());			
		PersonaNegocio negocioP = new PersonaNegocio();
		String nombreProfesor = negocioP.obtenerNombre(IDProfesor, 'P');
		ArrayList<Curso> listado = negocioC.listarCursos(IDProfesor);
		String tabla = "";
		for(Curso c : listado)
		{			
            tabla += "<tr>" + 
            		"<th scope='row'>"+c.getID()+"</th>" + 
            		"<td>"+c.getMateria().getNombre()+"</td>" + 
            		"<td>"+c.getSemestre()+"</td>" + 
            		"<td>"+c.getAño()+"</td>" + 
            		"<td><button onclick='mostrarMisAlumnos("+c.getID()+")' class='btn btn-primary'>Ver alumnos</button></td>" + 
            		"</tr>";
		}	

		if(request.getAttribute("Eliminar") != null)
		{
			request.setAttribute("ResultToast", request.getAttribute("Eliminar") );
			request.setAttribute("Eliminar", null);
		}
		else if(request.getAttribute("Agregar") != null)
		{
			request.setAttribute("ResultToast", request.getAttribute("Agregar") );
			request.setAttribute("Agregar", null);
		}
		 request.setAttribute("NombreP", nombreProfesor);
		 request.setAttribute("tabla", tabla);
	
		 RequestDispatcher rd = request.getRequestDispatcher("/MisCursos.jsp");		 
		 rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// === LISTAR ALUMNOS POR CURSO
		
		if (request.getParameter("setIDCurso") != null) {
			AlumnoNegocio negocioA = new AlumnoNegocio();
			String tabla = "";
			ArrayList<Alumno> listado = negocioA.listarAlumnos(Integer.parseInt(request.getParameter("setIDCurso"))); 
			for(Alumno a : listado) {
				tabla += "<tr>" + 
						"<th scope='row'>1000</th>" + 
						"<td>"+ a.getApellido() +", " +a.getNombre()+"</td>" + 
						"<td><input type='text' class='form-control border form-nota' value='"+a.getParcial1()+"'></td>" + 
						"<td><input type='text' class='form-control border form-nota' value='"+a.getRecuperatorio1()+"'></td>" + 
						"<td><input type='text' class='form-control border form-nota' value='"+a.getParcial2()+"'></td>" + 
						"<td><input type='text' class='form-control border form-nota' value='"+a.getRecuperatorio2()+"'></td>" + 
						"<td><input type='text' class='form-control border form-nota' value='"+a.getNotaFinal()+"'></td>" + 
						"<td>" +
						"<select class='custom-select' style='width: 200px; margin-top: 8px;'>";
						if(a.getSituacion() == "Regular") {
							tabla +="<option value='0' selected class='dropdown-item'>Regular</option>" + 
									"<option value='1' class='dropdown-item'>Libre</option>"; 
						}
						else if(a.getSituacion() == "Libre"){
							tabla +="<option value='0' class='dropdown-item'>Regular</option>" + 
									"<option value='1' selected class='dropdown-item'>Libre</option>"; 
						}
						else {
							tabla +="<option value='0' class='dropdown-item'>Regular</option>" + 
									"<option value='1' class='dropdown-item'>Libre</option>"; 
						}
						tabla +="</select>" + 
						"</td>" + 
						" </tr>";
			}
			request.setAttribute("tablaAlumnosCurso", tabla);
			
			//response.sendRedirect("/TP_Integrador_Lescano/AlumnosCurso.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/AlumnosCurso.jsp");		 
			rd.forward(request, response);
		}
		
		
	}

}
