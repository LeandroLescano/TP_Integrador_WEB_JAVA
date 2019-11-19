package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Negocio.PersonaNegocio;

/**
 * Servlet implementation class servletMisCursos
 */
@WebServlet("/servletLogueo")
public class servletLogueo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletLogueo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if (request.getParameter("btnIngresar") != null) {
			String Email = request.getParameter("txtEmail");
			String Contra = request.getParameter("txtContraseña");
			PersonaNegocio negocioP = new PersonaNegocio();
			int IDProfesor = negocioP.validarIngreso(Email, Contra);
			if(IDProfesor != -1) {
				HttpSession session = request.getSession();
				session.setAttribute("IDProfesor", IDProfesor);
				
				 response.sendRedirect("/TP_Integrador_Lescano/servletMisCursos");
			}
		}
		
		
	}

}
