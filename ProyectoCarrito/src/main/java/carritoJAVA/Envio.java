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
	
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	HttpSession sesion = request.getSession();
		response.setContentType("text/html");
		
		if (sesion.isNew()) {         
			response.sendRedirect("carritoJSP/Login.jsp");
        }else {
        	response.sendRedirect("carritoJSP/Login.jsp");
        }
    	    }
    	    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    	       throws ServletException, IOException {
    	
    	HttpSession sesion = request.getSession();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String nUsuario=null;
		double harina1Kg=0;
		double macarrones1Kg=0;
		double lentejas500gramos=0;
		double espaguetis2Kg=0;
		

    	nUsuario=(String) sesion.getAttribute("nombreUsu");
		harina1Kg=Double.parseDouble(request.getParameter("harina1Kg"));
		macarrones1Kg=Double.parseDouble(request.getParameter("macarrones1Kg"));
		lentejas500gramos=Double.parseDouble(request.getParameter("lentejas500gramos"));
		espaguetis2Kg=Double.parseDouble(request.getParameter("espaguetis2Kg"));

   
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>\n"
				+ "<meta http-equiv=”Content-Type” content=”text/html; charset=UTF-8 />\n"
				+ "<title>Envio SuperMercado</title>\n"
				+ "<link href=carritoJSP/Estilos.css rel=stylesheet>"
				+ "</head>");
		out.println("<body>");
		
		out.println("<div>"
				+ "<p>Usuario: "+nUsuario+"</p>"
				+ "<div>");
		out.println("<div class=divPadre>");
		
		out.println("<form action=/ProyectoCarrito/factura method=post>");
		if (harina1Kg>0) {
				out.println("<div>"
				+ "<p>Uster a pedido "+ harina1Kg+" paquetes de harina 1Kg su precio es "+ harina1Kg*1.25+"</p>"
				+ "</div>");
		}
		if (macarrones1Kg>0) {
			out.println("<div>"
		+ "<p>Uster a pedido "+ macarrones1Kg+" paquetes de macarrones de 1Kg su precio es "+ macarrones1Kg*1.50+"</p>"
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
				+ "<label for=Ciudad>Ciudad:</label>"
				+ "<input type=text name=ciudad required>"
				+ "</div>");
		out.println("<div>"
				+ "<label for=Calle>Calle:</label>"
				+ "<input type=text name=calle required>"
				+ "</div>");
		out.println("<div>"
				+ "<label for=numero>Numero:</label>"
				+ "<input type=text name=numero required>"
				+ "</div>");
		out.println("<div>"
				+ "<label for=numeroTelefono>Numero de telefono:</label>"
				+ "<input type=number name=numeroTelefono min=600000000 max=999999999 required>"
				+ "</div>");
		out.println("<div>"
				+ "<label for=tipoEnv>Tipo envio:</label>"
				+ " Normal 12 dias <br>"
				+ " Rapido 7 dias <br>"
				+ " Expres 3 dias <br>"
				+ " Inmediato 1 dias <br>"
				+ " <select name=envio>"
				+ "  <option value=2.50>Normal 2,50 euros</option>"
				+ "  <option value=3>Rapido 3 euros</option>"
				+ "  <option value=5>Expres 5 euros</option>"
				+ "  <option value=7.50>Inmediato 7.50 euros</option>"
				+ "</select>"
				+ "</div>");
		out.println("<div>"
				+ "<input type=submit value=Enviar class=botonForm>"
				+ "</div>");
		
		//subir dator a la sesion
		if (harina1Kg<0) {
			harina1Kg=0;
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
		sesion.setAttribute("harina1Kg", harina1Kg);
		sesion.setAttribute("macarrones1Kg", macarrones1Kg);
		sesion.setAttribute("lentejas500gramos", lentejas500gramos);
		sesion.setAttribute("espaguetis2Kg", espaguetis2Kg);
		out.println("</form>");

		out.println("</body>");
		out.println("</html>");
		sesion.setMaxInactiveInterval(180);
    	    	}
}
