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
		name="factura", 
		urlPatterns="/factura"
)

public class Factura extends HttpServlet{
	
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
			String ciudad=null;
			String calle=null;
			String numero=null;
			String numeroTelefono=null;
			String tipoEnv=null;
			
			if (sesion.isNew() || sesion==null) {         
				response.sendRedirect("carritoJSP/Login.jsp");
	        } else {
	        	nUsuario=(String) sesion.getAttribute("nombreUsu");
	        	//Recuperar datos enviados por post
	        	ciudad=request.getParameter("ciudad");
	        	calle=request.getParameter("calle");
	        	numero=request.getParameter("numero");
	        	numeroTelefono=request.getParameter("numeroTelefono");
	        	tipoEnv=request.getParameter("tipoEnv");
	    		//Recuperar datos guardados en la sesion
	        	arina1Kg= (double) sesion.getAttribute("arina1Kg");
	        	macarrones1Kg=(double) sesion.getAttribute("macarrones1Kg");
	        	lentejas500gramos=(double) sesion.getAttribute("lentejas500gramos");
	        	espaguetis2Kg=(double) sesion.getAttribute("espaguetis2Kg");
	    }
			
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>"
					+ "	<meta http-equiv=”Content-Type” content=”text/html; charset=UTF-8 />"
					+ "	<title>Factura SuperMercado</title>"
					+ "	<link href=carritoJSP/Estilos.css rel=stylesheet>"
					+ "	</head>");
			out.println("<body>");
			
			out.println("<div>"
					+ "<p>"+nUsuario+"</p>"
					+ "<div>");
			out.println("<div>");
			out.println("<div>");

			if (arina1Kg!=0) {
				out.println("<div>"
				+ "<p>Uster a pedido "+ arina1Kg+" aquetes de arina 1Kg su precio total es "+ arina1Kg*1.25+"</p>"
				+ "</div>");
				}
			if (macarrones1Kg!=0) {
				out.println("<div>"
				+ "<p>Uster a pedido "+ macarrones1Kg+" aquetes de macarrones de 1Kg su precio total es "+ macarrones1Kg*1.50+"</p>"
				+ "</div>");
				}
			if (lentejas500gramos!=0) {
				out.println("<div>"
				+ "<p>Uster a pedido "+ lentejas500gramos+" paquetes de lentejas de 500g de 2Kg su precio total es "+ lentejas500gramos*0.75+"</p>"
				+ "</div>");
				}
			if (espaguetis2Kg!=0) {
				out.println("<div>"
				+ "<p>Uster a pedido "+ espaguetis2Kg+" paquetes de espaguetis de 2Kg su precio total es "+ espaguetis2Kg*2.00+"</p>"
				+ "</div>");
				}
			out.println("<div>"
					+ "<p>El envio es "+ 2.50 +"</p>"
					+ "</div>");
			out.println("<div>"
			+ "<p>La direccion a la que se enviara es "+ ciudad+" calle "+ calle +" numero "+ numero +"</p>"
			+ "<p>El numero de telefono es "+ numeroTelefono +"</p>"
			+ "</div>");
			
			out.println("<div>"
					+ "<p>El precio total es "+ (espaguetis2Kg*2.00+lentejas500gramos*0.75+macarrones1Kg*1.50+arina1Kg*1.25+2.50) +"</p>"
					+ "</div>");
			out.println("<div>"
					+ "<p>Si los datos son correctos pulse aceptar"+"</p>"
					+ "</div>");
			out.println("<form action=/ProyectoCarrito/fin method=post>");
			out.println("<div>"
					+ "<input type=submit value=Aceptar>"
					+ "</div>");
			out.println("</form>");




			out.println("</body>");
			out.println("</html>");
			sesion.setMaxInactiveInterval(180);
	    	    	}
	

}
