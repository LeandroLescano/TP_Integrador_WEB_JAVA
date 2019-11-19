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

import Dominio.Alumno;
import Negocio.AlumnoNegocio;

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
		
		int IDCurso = -1;	
		HttpSession session = request.getSession();
		IDCurso = Integer.parseInt((String) session.getAttribute("IDCurso"));
		AlumnoNegocio negocioA = new AlumnoNegocio();
		String tabla = "";
		ArrayList<Alumno> listado = negocioA.listarAlumnos(IDCurso); 
		for(Alumno a : listado) {
			String Par1 = "", Par2 = "", Rec1 = "", Rec2 = "", NF = "";
			String[] Notas = {Par1, Par2, Rec1, Rec2, NF};
			Notas = chequearNotas(Notas, a);
					
			tabla += "<tr>" + 
					"<th scope='row'>1000</th>" + 
					"<td>"+ a.getApellido() +", " +a.getNombre()+"</td>" + 
					"<td><input type='text' class='form-control border form-nota' value='"+Notas[0]+"'></td>" + 
					"<td><input type='text' class='form-control border form-nota' value='"+Notas[1]+"'></td>" + 
					"<td><input type='text' class='form-control border form-nota' value='"+Notas[2]+"'></td>" + 
					"<td><input type='text' class='form-control border form-nota' value='"+Notas[3]+"'></td>" + 
					"<td><input type='text' class='form-control border form-nota' value='"+Notas[4]+"'></td>" + 
					"<td>" +
					"<select class='custom-select' style='width: 200px; margin-top: 8px;'>";
					if(a.getSituacion() == "Regular") {
						tabla +="<option value='0' class='dropdown-item'>Sin definir</option>" + 
								"<option value='1' selected class='dropdown-item'>Regular</option>" + 
								"<option value='2' class='dropdown-item'>Libre</option>"; 
					}
					else if(a.getSituacion() == "Libre"){
						tabla +="<option value='0' class='dropdown-item'>Sin definir</option>" +
								"<option value='0' class='dropdown-item'>Regular</option>" + 
								"<option value='1' selected class='dropdown-item'>Libre</option>"; 
					}
					else {
						tabla +="<option value='0' selected class='dropdown-item'>Sin definir</option>" +
								"<option value='0' class='dropdown-item'>Regular</option>" + 
								"<option value='1' class='dropdown-item'>Libre</option>"; 
					}
					tabla +="</select>" + 
					"</td>" + 
					" </tr>";
		}
		request.setAttribute("tablaAlumnosCurso", tabla);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/AlumnosCurso.jsp");		 
		 rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private String[] chequearNotas(String[] Notas, Alumno a) {
		if(a.getParcial1() % 1 == 0 && a.getParcial1() > 0) {
			Notas[0] = String.valueOf((int) a.getParcial1());
		}
		else if (a.getParcial1() > 0){
			Notas[0] = String.valueOf(a.getParcial1());
		}
		
		if(a.getParcial2() % 1 == 0 && a.getParcial2() > 0) {
			Notas[1] = String.valueOf((int) a.getParcial2());
		}
		else if (a.getParcial2() > 0){
			Notas[1] = String.valueOf(a.getParcial2());
		}
		
		if(a.getRecuperatorio1() % 1 == 0 && a.getRecuperatorio1() > 0) {
			Notas[2] = String.valueOf((int) a.getRecuperatorio1());
		}
		else if (a.getRecuperatorio1() > 0){
			Notas[2] = String.valueOf(a.getRecuperatorio1());
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

}
