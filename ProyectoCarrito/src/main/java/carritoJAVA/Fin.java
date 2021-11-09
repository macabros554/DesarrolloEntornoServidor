package carritoJAVA;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
		name="fin", 
		urlPatterns="/fin"
)

public class Fin extends HttpServlet{

	 public void doGet(HttpServletRequest request, HttpServletResponse response) 
	    		throws ServletException, IOException {
	    	HttpSession sesion = request.getSession();
			response.setContentType("text/html");
			
			if (sesion.isNew() || sesion==null) {         
				response.sendRedirect("carritoJSP/Login.jsp");
	        }else {
	        	response.sendRedirect("carritoJSP/Login.jsp");
	        }
	    	    }
	    	    
	    public void doPost(HttpServletRequest request, HttpServletResponse response) 
	    	       throws ServletException, IOException {
	    	
	    	HttpSession sesion = request.getSession();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String nUsuario=null;
			
			if (sesion.isNew()) {         
				response.sendRedirect("carritoJSP/Login.jsp");
	        } else {
	        	nUsuario=(String) sesion.getAttribute("nombreUsu");
	        }
			

			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>\n"
					+ "<meta http-equiv=”Content-Type” content=”text/html; charset=UTF-8 />\n"
					+ "<title>Factura SuperMercado</title>\n"
					+ "<link href=carritoJSP/Estilos.css rel=stylesheet>"
					+ "</head>");
			out.println("<body>");
			
			out.println("<h1>Pulsa el boton para volver al login ");
			out.println("<a href=carritoJSP/Login.jsp><input type=submit value=Terminar class=botonForm></a>");
			out.println("</body>");
			out.println("</html>");


			sesion.invalidate();
	    	    	}
	
}
