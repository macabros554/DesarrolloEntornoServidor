package carritoJAVA;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
		name="login", 
		urlPatterns="/login"
)

public class Logear extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
       throws ServletException, IOException {

    		}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    	       throws ServletException, IOException {
    				HttpSession sesion = request.getSession();
    				
    				Map<String, String> usuariosReg = new HashMap<String, String>();
    				usuariosReg.put("admin1", "1234");
    				usuariosReg.put("usu2", "112");
    				usuariosReg.put("usu1", "113");
    				
    				String nombre = request.getParameter("nombreUsuario");
    				String contrasena = request.getParameter("contrasenia");
    				
    				if (usuariosReg.containsKey(nombre) && usuariosReg.get(nombre).equals(contrasena) && sesion.getAttribute("nombreUsuario")==null) {
						System.out.println("Sesion iniciada");
						sesion.setAttribute("nombreUsu", nombre);
						response.sendRedirect("catalogo");
					}else {
						response.sendRedirect("carritoJSP/Login.jsp");
						System.out.println("El nombre o contraseña no coincide");
					}
    				
    				
    				
    	    	}
}

