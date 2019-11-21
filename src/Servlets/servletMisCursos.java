package Servlets;

import java.io.IOException;

import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dominio.Curso;
import Negocio.CursoNegocio;
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
				response.sendRedirect("./Inicio.jsp");
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
	            		//"<td><button onclick='mostrarMisAlumnos("+c.getID()+")' class='btn btn-primary'>Ver alumnos</button></td>" +
	            		"<td><button type='submit' name='btnVerAlumnos' value='"+c.getID()+"' class='btn btn-primary'>Ver alumnos</button></td>" + 
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
		
		if (request.getParameter("btnVerAlumnos") != null) {
			HttpSession session = request.getSession();
			String IDCurso = request.getParameter("CursoSelect").toString();
			session.setAttribute("IDCurso", IDCurso);
			
			 response.sendRedirect("/TP_Integrador_Lescano/servletAlumnosCurso");
//			 RequestDispatcher rd = request.getRequestDispatcher("/servletAlumnosCurso");		 
//			 rd.forward(request, response);
		}
		
		
	}

}
