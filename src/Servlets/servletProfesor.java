package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Domicilio;
import Dominio.Localidad;
import Dominio.Persona;
import Dominio.TipoPersona;
import Negocio.DomicilioNegocio;
import Negocio.LocalidadNegocio;
import Negocio.PersonaNegocio;
import Negocio.ProvinciaNegocio;

/**
 * Servlet implementation class servletProfesor
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
				if(p.isEstado())
				{				
		            tabla += "<tr>" + 
				      "<th scope='row'>" + p.getLegajo ()+ "</th>" + 
				      "<td>" + p.getApellido() + ", " + p.getNombre()  + "</td>" +
				      "<td>" + p.getMail() + "</td>" +
				      "<td>" + p.getTelefono() + "</td>"+ 
				      "<td>" + 
		              "<button type='button' class='btn btn-primary btn-icon' onclick='mostrarPersona("+ p.getLegajo() +", \"P\")' id='btnVer" + p.getLegajo() + "'><img src='https://i.ibb.co/yNgpRb4/eye.png' height='30' width='30' /></button>" +
		              "<button type='button' class='btn btn-success btn-icon' onclick='editarPersona("+ p.getLegajo() +", \"P\")' id='btnEditar" + p.getLegajo() + "'><img src='https://i.ibb.co/7Yj831F/edit.png' height='30' width='30'/></button>" +
		              "<button type='button' class='btn btn-danger btn-icon' onclick='eliminarProfesor("+ p.getLegajo() +", \"P\")' id='btnEliminar" + p.getLegajo() + "'><img src='https://i.ibb.co/JK4T4ZR/delete.png' height='30' width='30' /></button>" +
		            "</td>" + 
		   			 "</tr>";
				}
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
	
		 RequestDispatcher rd = request.getRequestDispatcher("/Profesores.jsp");		 
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
		
		else if(request.getParameter("nuevoLegajo") != null){
			PersonaNegocio negocioP = new PersonaNegocio();
			int nuevoLegajo = negocioP.nuevoLegajo('P');
			String json = new Gson().toJson(nuevoLegajo);
			
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
		
		// === AGREGAR O MODIFICAR PROFESOR
		
		else if(request.getParameter("btnAgregar") != null){
			PersonaNegocio negocioPer = new PersonaNegocio();
			if(request.getParameter("btnAgregar").equals("Agregar")) {				
				Persona nueva = new Persona();
				cargarDatos(nueva, request, 'A');
				
				if(negocioPer.agregarPersona(nueva)) {
					request.setAttribute("Agregar", "Agregado");
				}
				else {
					request.setAttribute("Agregar", "ErrorA");
				}
			}
			else {
				Persona modif = negocioPer.obtenerPersona(Integer.parseInt(request.getParameter("txtLegajo")), 'P');
				modif.setLegajo(Integer.parseInt(request.getParameter("txtLegajo")));
				cargarDatos(modif, request, 'M');
				
				if(negocioPer.modificarPersona(modif)) {
					request.setAttribute("Modificar", "Modificado");
				}
				else {
					request.setAttribute("Modificar", "ErrorM");
				}			
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
		
		
	}
	
	private void cargarDatos(Persona p, HttpServletRequest request, char Tipo) {
		LocalidadNegocio negocioL = new LocalidadNegocio();
		ProvinciaNegocio negocioP = new ProvinciaNegocio();
		DomicilioNegocio negocioD = new DomicilioNegocio();
		TipoPersona tp = new TipoPersona();
		Domicilio dom = new Domicilio();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dom.setCalle(request.getParameter("txtCalle"));
		dom.setLocalidad(negocioL.obtenerLocalidad(Integer.parseInt(request.getParameter("txtLocalidad"))));
		dom.setProvincia(negocioP.obtenerProvincia(Integer.parseInt(request.getParameter("txtProvincia"))));
		if(Tipo == 'A') {
			dom.setID(negocioD.agregarDomicilio(dom));			
		}
		else {
			dom.setID(p.getDomicilio().getID());
			negocioD.modificarDomicilio(dom);
		}
		p.setApellido(request.getParameter("txtApellido"));
		p.setNombre(request.getParameter("txtNombre"));
		p.setMail(request.getParameter("txtMail"));
		p.setTelefono(request.getParameter("txtTelefono"));
		p.setFechNac(LocalDate.parse(request.getParameter("dtpFechNac"), dateFormat));
		tp.setID(1);
		tp.setTipo("Profesor");
		p.setTipo(tp);
		p.setDomicilio(dom);
	}

}
