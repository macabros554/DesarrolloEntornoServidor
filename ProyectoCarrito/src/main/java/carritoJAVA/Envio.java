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
		name="envio", 
		urlPatterns="/envio"
)

public class Envio extends HttpServlet{
	
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
		double arina1Kg=0;
		double macarrones1Kg=0;
		double lentejas500gramos=0;
		double espaguetis2Kg=0;
		
		if (sesion.isNew() || sesion==null) {         
			response.sendRedirect("carritoJSP/Login.jsp");
        } else {
        	nUsuario=(String) sesion.getAttribute("nombreUsu");
    		arina1Kg=Double.parseDouble(request.getParameter("arina1Kg"));
    		macarrones1Kg=Double.parseDouble(request.getParameter("macarrones1Kg"));
    		lentejas500gramos=Double.parseDouble(request.getParameter("lentejas500gramos"));
    		espaguetis2Kg=Double.parseDouble(request.getParameter("espaguetis2Kg"));

    }
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>\n"
				+ "	<meta http-equiv=”Content-Type” content=”text/html; charset=UTF-8 />\n"
				+ "	<title>Envio SuperMercado</title>\n"
				+ "	<link href=carritoJSP/Estilos.css rel=stylesheet>"
				+ "	</head>");
		out.println("<body>");
		
		out.println("<div>"
				+ "<p>"+nUsuario+"</p>"
				+ "<div>");
		out.println("<div>");
		out.println("<div>");
		out.println("<form action=/ProyectoCarrito/factura method=post>");
		if (arina1Kg>0) {
				out.println("<div>"
				+ "<p>Uster a pedido "+ arina1Kg+" aquetes de arina 1Kg su precio es "+ arina1Kg*1.25+"</p>"
				+ "</div>");
		}
		if (macarrones1Kg>0) {
			out.println("<div>"
		+ "<p>Uster a pedido "+ macarrones1Kg+" aquetes de macarrones de 1Kg su precio es "+ macarrones1Kg*1.50+"</p>"
		+ "</div>");
		}
		if (lentejas500gramos>0) {
			out.println("<div>"
		+ "<p>Uster a pedido "+ lentejas500gramos+" paquetes de lentejas de 500g de 2Kg su precio es "+ lentejas500gramos*0.75+"</p>"
		+ "</div>");
		}
		if (espaguetis2Kg > 0) {
			out.println("<div>"
		+ "<p>Uster a pedido "+ espaguetis2Kg+" paquetes de espaguetis de 2Kg su precio es "+ espaguetis2Kg*2.00+"</p>"
		+ "</div>");
		}

		out.println("<div>"
				+ "	<label for=Ciudad>Ciudad:</label>"
				+ "<input type=text name=ciudad required>"
				+ "</div>");
		out.println("<div>"
				+ "	<label for=Calle>Calle:</label>"
				+ "<input type=text name=calle required>"
				+ "</div>");
		out.println("<div>"
				+ "	<label for=numero>Numero:</label>"
				+ "<input type=text name=numero required>"
				+ "</div>");
		out.println("<div>"
				+ "	<label for=numeroTelefono>Numero de telefono:</label>"
				+ "<input type=text name=numeroTelefono required>"
				+ "</div>");
		out.println("<div>"
				+ "	<label for=tipoEnv>Tipo envio:</label>"
				+ " normal"
				+ "</div>");
		out.println("<div>"
				+ "<input type=submit value=Enviar>"
				+ "</div>");
		
		// Terminar de hacer tipo de envio
		
		//subir dator a la sesion
		if (arina1Kg<0) {
			arina1Kg=0;
			}
		if (macarrones1Kg<0) {
			macarrones1Kg=0;
			}
		if (lentejas500gramos<0) {
			lentejas500gramos=0;
			}
		if (espaguetis2Kg < 0) {
			espaguetis2Kg=0;
			}
		sesion.setAttribute("arina1Kg", arina1Kg);
		sesion.setAttribute("macarrones1Kg", macarrones1Kg);
		sesion.setAttribute("lentejas500gramos", lentejas500gramos);
		sesion.setAttribute("espaguetis2Kg", espaguetis2Kg);
		out.println("</form>");

		out.println("</body>");
		out.println("</html>");
		sesion.setMaxInactiveInterval(180);
    	    	}
}
