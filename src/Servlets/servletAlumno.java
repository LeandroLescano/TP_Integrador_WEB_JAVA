package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Persona;
import Negocio.AlumnoNegocio;

/**
 * Servlet implementation class servletSeguro
 */
@WebServlet("/servletSeguro")
public class servletAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAlumno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 if(request.getParameter("btnAgregar") != null)
		 {
		 }
		 
		 RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");		 
		 rd.forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnActualizar") != null)
		{			
			AlumnoNegocio negocioA = new AlumnoNegocio();
			ArrayList<Persona> listado = negocioA.listarAlumnos();
			String tabla ="";
			int Tipo = Integer.parseInt(request.getParameter("tipos"));
			for(Persona s : listado)
			{
					tabla += "<tr>" + 
				   	"<th class='thID' scope='row'>" + s.getLegajo() + "</th>" + 
				   "<td class ='tdDesc'>" + s.getApellido() + ", " + s.getNombre()  + "</td>" + 
				      "<td><input type='text' class='form-control border' value='10' style='width: 40px; padding: 5px;'></td>" + 
					  "<td><select name='tipos' class='custom-select' style='width: 200px; margin-top: 8px;'>" +
			 		 	"<option value='0' class='dropdown-item'>Regular</option>" + 
			 		 	"<option value='1' class='dropdown-item'>Libre</option>" + 
			 			 "/select></td></tr>";
			}	
			
			if(Tipo != 0)
			{
				request.setAttribute("IDTipo", Tipo);
//				request.setAttribute("DescTipo", negocioT.obtenerTipo(Tipo));
			}
			request.setAttribute("tablaSeguro", tabla);
		} 
		
		 RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");		 
		 rd.forward(request, response);
		 
		
	}

}
