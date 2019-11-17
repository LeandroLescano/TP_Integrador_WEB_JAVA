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

import Dominio.Curso;
import Dominio.Localidad;
import Dominio.Persona;
import Negocio.CursoNegocio;
import Negocio.LocalidadNegocio;
import Negocio.MateriaNegocio;
import Negocio.PersonaNegocio;

/**
 * Servlet implementation class servletCurso
 */
@WebServlet("/servletCurso")
public class servletCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletCurso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CursoNegocio negocioC = new CursoNegocio();
		ArrayList<Curso> listado = negocioC.listarCursos();
		String tabla = "";
		for(Curso c : listado)
		{			
            tabla += "<tr>" + 
		      "<th scope='row'>" + c.getID()+ "</th>" + 
		      "<td>" +c.getMateria().getNombre()+ "</td>" +
		      "<td>" + c.getSemestre() + "</td>" +
		      "<td>" + c.getAño() + "</td>"+ 
		      "<td>" + c.getProfesor().getLegajo() + " - " + c.getProfesor().getApellido() + ", " +  c.getProfesor().getNombre() +"</td>" + 
		      "<td><a href='' onclick='mostrarAlumnos("+c.getID()+")' class='btn btn-primary'>Ver alumnos</a></td>" +
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
		 request.setAttribute("tabla", tabla);
	
		 RequestDispatcher rd = request.getRequestDispatcher("/Cursos.jsp");		 
		 rd.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// === DEVOLVER TODOS LOS DATOS DE UN PROFESOR
		
		if(request.getParameter("legajo") != null){
			
			int legajo = Integer.parseInt(request.getParameter("legajo"));
			PersonaNegocio negocioP = new PersonaNegocio();
			Persona p = negocioP.obtenerPersona(legajo, 'P');
			String json = new Gson().toJson(p);
			
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
		
		// === DEVOLVER NUEVO ID
		
		else if(request.getParameter("nuevoID") != null){
			CursoNegocio negocioC = new CursoNegocio();
			int nuevoID = negocioC.nuevoID();
			String json = new Gson().toJson(nuevoID);
			
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
		
		// === AGREGAR CURSO
		
		else if(request.getParameter("btnAgregar") != null){
			CursoNegocio negocioC = new CursoNegocio();			
			Curso nuevo = new Curso();
			String[] Legajos = cargarDatos(nuevo, request);
			if(negocioC.agregarCurso(nuevo)) {
				negocioC.agregarAlumnosCurso(Legajos, nuevo.getID());
				request.setAttribute("Agregar", "Agregado");
			}
			else {
				request.setAttribute("Agregar", "ErrorC");
			}	
			
			doGet(request, response);
		}
		
		// === LISTAR LAS LOCALIDADES DE UNA DETERMINADA PROVINCIA
		
		else if(request.getParameter("provincia") != null){
			int IDProv = Integer.parseInt(request.getParameter("provincia"));
			LocalidadNegocio negocioL = new LocalidadNegocio();
			ArrayList<Localidad> listado = negocioL.listarLocalidad(IDProv);
			String options = "";
			for(Localidad l : listado) {
				options += "<option value='" + l.getID() + "'> " + l.getNombre() + "</option>";
			}
			String json = new Gson().toJson(options);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
		
		// === ELIMINAR PROFESOR
		
		else if(request.getParameter("btnEliminar") != null){
			int legajo = Integer.parseInt(request.getParameter("Legajo"));
			PersonaNegocio negocioP = new PersonaNegocio();
			if(negocioP.eliminarPersona(legajo, 'P')) {
				request.setAttribute("Eliminar", "Eliminado");
			}
			else {
				request.setAttribute("Eliminar", "ErrorE");
			}
				
			 doGet(request, response);
		}
		
		// === LISTAR ALUMNOS POR CURSO
		
		else if (request.getParameter("listarAlumnosCurso") != null){
			CursoNegocio negocioC = new CursoNegocio();
			String tablaAlum = "";
			int IDCurso = Integer.parseInt(request.getParameter("listarAlumnosCurso"));
			ArrayList<Persona> listadoAlumnos = negocioC.listarAlumnosCurso(IDCurso);
			for (Persona p : listadoAlumnos) {
				tablaAlum += "<tr>" + 
						"<th scope='row'>"+ p.getLegajo()+"</th>" + 
						"<td>"+p.getApellido()+", " + p.getNombre()+"</td>" + 
						"</tr>";
			}
			String json = new Gson().toJson(tablaAlum);
			
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);	
		}
		
		
	}
	
	private String[] cargarDatos(Curso c, HttpServletRequest request) {
		MateriaNegocio negocioM = new MateriaNegocio();
		PersonaNegocio negocioP = new PersonaNegocio();
		c.setID(Integer.parseInt(request.getParameter("txtNumero")));
		c.setAño(Integer.parseInt(request.getParameter("slAño")));
		c.setMateria(negocioM.obtenerMateria(Integer.parseInt(request.getParameter("slMateria"))));
		c.setProfesor(negocioP.obtenerPersona(Integer.parseInt(request.getParameter("slProfesor")), 'P'));
		c.setSemestre(request.getParameter("slSemestre"));
		String LegajosHidden = request.getParameter("LegajosAlumnos");
		return LegajosHidden.split(",");		
	}

}
