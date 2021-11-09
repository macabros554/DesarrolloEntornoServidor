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
		name="catalogo", 
		urlPatterns="/catalogo"
)

public class CatalogoJ extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    		HttpSession sesion = request.getSession();
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		String nUsuario=null;
    		
    		if (sesion.isNew()) {         
    			response.sendRedirect("carritoJSP/Login.jsp");
                 
            } else {
            	nUsuario=(String) sesion.getAttribute("nombreUsu");
            	if (nUsuario==null) {
            		response.sendRedirect("carritoJSP/Login.jsp");
				}
        }

			
    		
    		out.println("<!DOCTYPE html>");
    		out.println("<html>");
    		out.println("<head>\n"
    				+ "<meta http-equiv=Content-Type content=text/html; charset=UTF-8 />"
    				+ "<title>Catalogo SuperMercado</title>"
    				+ "<link href=carritoJSP/Estilos.css rel=stylesheet>"
    				+ "</head>");
    		out.println("<body>");
    		
    		
    		out.println("<div>"
    				+ "<p>Usuario: "+nUsuario+"</p>"
    				+ "<div>");
    		out.println("<div class=divPadre>");
    		
    		out.println("<form action=/ProyectoCarrito/envio method=post>");
    		out.println("<div>"
    				+ "Harina 1Kg <br>"
    				+ "1,25 euros<br>"
    				+ "<input type=number name=harina1Kg value=0>"
    				+ "</div>");
    		out.println("<div>\n"
    				+ "Macarrones 1Kg <br>"
    				+ "1,50 euros <br>"
    				+ "<input type=number name=macarrones1Kg value=0>"
    				+ "</div>");
    		out.println("<div>"
    				+ "Lentejas 500g <br>"
    				+ "0,75 euros <br>"
    				+ "<input type=number name=lentejas500gramos value=0>"
    				+ "</div>");
    		out.println("<div>"
    				+ "Espaguetis 2Kg <br>"
    				+ "2,00 euros <br>"
    				+ "<input type=number name=espaguetis2Kg value=0>"
    				+ "</div>");
    		//out.println("/div>");
    		//veo incoerencias en el codigo
    		out.println("<input type=submit value=Aceptar class=botonForm>");
    		out.println("</form>");
    		out.println("</body>");
    		out.println("</html>");
    		sesion.setMaxInactiveInterval(180);
    		//out.println(" session.getMaxInactiveInterval(): " + sesion.getMaxInactiveInterval() + " secs");
    	    }
    	    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    	       throws ServletException, IOException {

    				
    	    	}
	
}
