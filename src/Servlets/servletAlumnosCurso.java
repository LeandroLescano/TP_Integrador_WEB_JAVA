package Servlets;

import java.io.IOException;

import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import Dominio.Alumno;
import Negocio.AlumnoNegocio;
import Negocio.PersonaNegocio;

/**
 * Servlet implementation class servletAlumnosCurso
 */
@WebServlet("/servletAlumnosCurso")
public class servletAlumnosCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAlumnosCurso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int IDProfesor = -1;
		if(session.getAttribute("IDProfesor") == null) {
			response.sendRedirect("./Inicio.jsp");
		}
		else {
			IDProfesor = Integer.parseInt(session.getAttribute("IDProfesor").toString());			
			PersonaNegocio negocioP = new PersonaNegocio();
			
			int IDCurso = -1;	
			IDCurso = Integer.parseInt((String) session.getAttribute("IDCurso"));
			AlumnoNegocio negocioA = new AlumnoNegocio();
			String nombreProfesor = negocioP.obtenerNombre(IDProfesor, 'P');
			String tabla = "";
			ArrayList<Alumno> listado = negocioA.listarAlumnos(IDCurso); 
			for(Alumno a : listado) {
				String Par1 = "", Par2 = "", Rec1 = "", Rec2 = "", NF = "";
				String[] Notas = {Par1, Par2, Rec1, Rec2, NF};
				Notas = chequearNotas(Notas, a);
						
				tabla += "<tr>" + 
						"<th scope='row'>"+a.getLegajo()+"</th>" + 
						"<td>"+ a.getApellido() +", " +a.getNombre()+"</td>" + 
						"<td><input type='number' step='.01' name='Par1-"+a.getLegajo()+"' class='form-control border form-nota' value='"+Notas[0]+"'></td>";
						if(mayorSeis(Notas[0])) {
							tabla += "<td><input type='number' step='.01' name='Rec1-"+a.getLegajo()+"' disabled class='form-control border form-nota' value='"+Notas[1]+"'></td>";
						}
						else {
							tabla += "<td><input type='number' step='.01' name='Rec1-"+a.getLegajo()+"' class='form-control border form-nota' value='"+Notas[1]+"'></td>"; 
						}														

						tabla += "<td><input type='number' step='.01' name='Par2-"+a.getLegajo()+"' class='form-control border form-nota' value='"+Notas[2]+"'></td>";
						if(mayorSeis(Notas[2])) {
							tabla += "<td><input type='number' step='.01' name='Rec2-"+a.getLegajo()+"' disabled class='form-control border form-nota' value='"+Notas[3]+"'></td>";
						}
						else {
							tabla += "<td><input type='number' step='.01' name='Rec2-"+a.getLegajo()+"' class='form-control border form-nota' value='"+Notas[3]+"'></td>"; 

						}
						tabla +="<td><input type='number' step='.01' name='NotF-"+a.getLegajo()+"' class='form-control border form-nota' value='"+Notas[4]+"'></td>" + 
						"<td>" +
						"<select class='custom-select' name='Situ-"+a.getLegajo()+"'  style='width: 200px; margin-top: 8px;'>";
						if(a.getSituacion().equals("Regular")) {
							tabla +="<option value='Sin definir' class='dropdown-item'>Sin definir</option>" + 
									"<option value='Regular' selected class='dropdown-item'>Regular</option>" + 
									"<option value='Libre' class='dropdown-item'>Libre</option>"; 
						}
						else if(a.getSituacion().equals("Libre")){
							tabla +="<option value='Sin definir' class='dropdown-item'>Sin definir</option>" +
									"<option value='Regular' class='dropdown-item'>Regular</option>" + 
									"<option value='Libre' selected class='dropdown-item'>Libre</option>"; 
						}
						else {
							tabla +="<option value='Sin definir' selected class='dropdown-item'>Sin definir</option>" +
									"<option value='Regular' class='dropdown-item'>Regular</option>" + 
									"<option value='Libre' class='dropdown-item'>Libre</option>"; 
						}
						tabla +="</select>" + 
						"</td>" + 
						" </tr>";
			}
			request.setAttribute("tablaAlumnosCurso", tabla);
			request.setAttribute("NombreP", nombreProfesor);
			request.setAttribute("CursoActual", IDCurso);
			if(request.getAttribute("Cargar") != null)
			{
				request.setAttribute("ResultToast", request.getAttribute("Cargar") );
				request.setAttribute("Cargar", null);
			}
			 
			
			RequestDispatcher rd = request.getRequestDispatcher("/AlumnosCurso.jsp");		 
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnCargar") != null) {
			int IDCurso = -1;	
			HttpSession session = request.getSession();
			IDCurso = Integer.parseInt((String) session.getAttribute("IDCurso"));
			AlumnoNegocio negocioA = new AlumnoNegocio();
			ArrayList<Alumno> listado = negocioA.listarAlumnos(IDCurso);
			for(Alumno a : listado) {
				Alumno aCargar = cargarDatos(a.getLegajo(), request);
				aCargar.setLegajo(a.getLegajo());
				if(negocioA.cargarNotas(aCargar, IDCurso)) {
					
				}
			}
			
			request.setAttribute("Cargar", "Cargado");
			
			doGet(request, response);
		}
		
	}
	
	private Alumno cargarDatos(int Legajo, HttpServletRequest request) {
		Alumno a = new Alumno();
		if(request.getParameter("Par1-"+Legajo) != null && request.getParameter("Par1-"+Legajo).length() > 0)
			a.setParcial1(Float.parseFloat(request.getParameter("Par1-"+Legajo)));
		if(request.getParameter("Par2-"+Legajo) != null && request.getParameter("Par2-"+Legajo).length() > 0)
			a.setParcial2(Float.parseFloat(request.getParameter("Par2-"+Legajo)));
		if(request.getParameter("Rec1-"+Legajo) != null && request.getParameter("Rec1-"+Legajo).length() > 0)
			a.setRecuperatorio1(Float.parseFloat(request.getParameter("Rec1-"+Legajo)));
		if(request.getParameter("Rec2-"+Legajo) != null && request.getParameter("Rec2-"+Legajo).length() > 0)
			a.setRecuperatorio2(Float.parseFloat(request.getParameter("Rec2-"+Legajo)));
		if(request.getParameter("NotF-"+Legajo) != null && request.getParameter("NotF-"+Legajo).length() > 0)
			a.setNotaFinal(Float.parseFloat(request.getParameter("NotF-"+Legajo)));
		if(request.getParameter("Situ-"+Legajo) != null && request.getParameter("Situ-"+Legajo).length() > 0)
			a.setSituacion(request.getParameter("Situ-"+Legajo));
		
		return a;
		
	}
	
	private String[] chequearNotas(String[] Notas, Alumno a) {
		if(a.getParcial1() % 1 == 0 && a.getParcial1() > 0) {
			Notas[0] = String.valueOf((int) a.getParcial1());
		}
		else if (a.getParcial1() > 0){
			Notas[0] = String.valueOf(a.getParcial1());
		}
		
		if(a.getRecuperatorio1() % 1 == 0 && a.getRecuperatorio1() > 0) {
			Notas[1] = String.valueOf((int) a.getRecuperatorio1());
		}
		else if (a.getRecuperatorio1() > 0){
			Notas[1] = String.valueOf(a.getRecuperatorio1());
		}
		
		if(a.getParcial2() % 1 == 0 && a.getParcial2() > 0) {
			Notas[2] = String.valueOf((int) a.getParcial2());
		}
		else if (a.getParcial2() > 0){
			Notas[2] = String.valueOf(a.getParcial2());
		}
		
		if(a.getRecuperatorio2() % 1 == 0 && a.getRecuperatorio2() > 0) {
			Notas[3] = String.valueOf((int) a.getRecuperatorio2());
		}
		else if (a.getRecuperatorio2() > 0){
			Notas[3] = String.valueOf(a.getRecuperatorio2());
		}
		
		if(a.getNotaFinal() % 1 == 0 && a.getNotaFinal() > 0) {
			Notas[4] = String.valueOf((int) a.getNotaFinal());
		}
		else if(a.getNotaFinal() > 0){
			Notas[4] = String.valueOf(a.getNotaFinal());
		}
		
		return Notas;
	}
	
	private boolean mayorSeis(String num) {
		if(num != "") {
			try {
				if(Integer.parseInt(num) >= 6) {
					return true;				
				}
				else {
					return false;
				}		
			} catch (NumberFormatException excepcion) {
				if(Float.parseFloat(num) >= 6) {
					return true;				
				}
				else {
					return false;
				}	
	        }
		}
		return false;
	}

}
