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

import Dominio.Persona;
import Negocio.PersonaNegocio;

/**
 * Servlet implementation class servletSeguro
 */
@WebServlet("/servletProfesor")
public class servletProfesor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletProfesor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersonaNegocio negocioP = new PersonaNegocio();
		ArrayList<Persona> listado = negocioP.listarPersonas('P');
		String tabla = "";
		for(Persona p : listado)
		{
//				tabla += "<tr>" + 
//			   	"<th class='thID' scope='row'>" + p.getLegajo() + "</th>" + 
//			   "<td class ='tdDesc'>" + p.getApellido() + ", " + p.getNombre()  + "</td>" + 
//			      "<td><input type='text' class='form-control border' value='10' style='width: 40px; padding: 5px;'></td>" + 
//				  "<td><select name='tipos' class='custom-select' style='width: 200px; margin-top: 8px;'>" +
//		 		 	"<option value='0' class='dropdown-item'>Regular</option>" + 
//		 		 	"<option value='1' class='dropdown-item'>Libre</option>" + 
//		 			 "/select></td></tr>";
				
	            tabla += "<tr>" + 
			      "<th scope='row'>" + p.getLegajo ()+ "</th>" + 
			      "<td>" + p.getApellido() + ", " + p.getNombre()  + "</td>" +
			      "<td>" + p.getMail() + "</td>" +
			      "<td>" + p.getTelefono() + "</td>"+ 
			      "<td>" + 
	              "<button type='button' class='btn btn-primary btn-icon' name='btnVer' onclick='mostrarProfesor("+ p.getLegajo() +")' id='btnVer" + p.getLegajo() + "'><img src='https://i.ibb.co/yNgpRb4/eye.png' height='30' width='30' /></button>" +
	              "<button type='button' class='btn btn-success btn-icon' id='btnEditar" + p.getLegajo() + "'><img src='https://i.ibb.co/7Yj831F/edit.png' height='30' width='30'/></button>" +
	              "<button type='button' class='btn btn-danger btn-icon' id='btnEliminar" + p.getLegajo() + "'><img src='https://i.ibb.co/JK4T4ZR/delete.png' height='30' width='30' /></button>" +
	            "</td>" + 
	   			 "</tr>";
		}	

		 request.setAttribute("tabla", tabla);
	
		 RequestDispatcher rd = request.getRequestDispatcher("/Profesores.jsp");		 
		 rd.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("legajo") != null){
			
			int legajo = Integer.parseInt(request.getParameter("legajo"));
			PersonaNegocio negocioP = new PersonaNegocio();
			Persona p = negocioP.obtenerPersona(legajo, 'P');
			String json = new Gson().toJson(p);
			
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
		else if(request.getParameter("btnListar") != null) //LISTAR PROFESORES Y ALUMNOS
		{			
			PersonaNegocio negocioP = new PersonaNegocio();
			ArrayList<Persona> listado = negocioP.listarPersonas('P');
			String tabla = "";
			for(Persona p : listado)
			{
//					tabla += "<tr>" + 
//				   	"<th class='thID' scope='row'>" + p.getLegajo() + "</th>" + 
//				   "<td class ='tdDesc'>" + p.getApellido() + ", " + p.getNombre()  + "</td>" + 
//				      "<td><input type='text' class='form-control border' value='10' style='width: 40px; padding: 5px;'></td>" + 
//					  "<td><select name='tipos' class='custom-select' style='width: 200px; margin-top: 8px;'>" +
//			 		 	"<option value='0' class='dropdown-item'>Regular</option>" + 
//			 		 	"<option value='1' class='dropdown-item'>Libre</option>" + 
//			 			 "/select></td></tr>";
					
		            tabla += "<tr>" + 
				      "<th scope='row'>" + p.getLegajo ()+ "</th>" + 
				      "<td>" + p.getApellido() + ", " + p.getNombre()  + "</td>" +
				      "<td>" + p.getMail() + "</td>" +
				      "<td>" + p.getTelefono() + "</td>"+ 
				      "<td>" + 
		              "<button type='button' class='btn btn-primary btn-icon' id='btnVer" + p.getLegajo() + "'><img src='https://i.ibb.co/yNgpRb4/eye.png' height='30' width='30' /></button>" +
		              "<button type='button' class='btn btn-success btn-icon' id='btnEditar" + p.getLegajo() + "'><img src='https://i.ibb.co/7Yj831F/edit.png' height='30' width='30'/></button>" +
		              "<button type='button' class='btn btn-danger btn-icon' id='btnEliminar" + p.getLegajo() + "'><img src='https://i.ibb.co/JK4T4ZR/delete.png' height='30' width='30' /></button>" +
		            "</td>" + 
		   			 "</tr>";
			}	

			request.setAttribute("tabla", tabla);
			RequestDispatcher rd = request.getRequestDispatcher("/Profesores.jsp");		 
			rd.forward(request, response);
		} 
		
		 
		
	}

}
