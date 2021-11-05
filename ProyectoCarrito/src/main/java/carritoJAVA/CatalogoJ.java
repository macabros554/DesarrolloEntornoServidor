package carritoJAVA;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		name="catalogo", 
		urlPatterns="/catalogo"
)

public class CatalogoJ extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		
    		out.println("<!DOCTYPE html>");
    		out.println("<html>");
    		out.println("<head>\n"
    				+ "	<meta charset=\"UTF-8\">\n"
    				+ "	<title>Catalogo SuperMercado</title>\n"
    				+ "	</head>");
    		out.println("	<body>\n"
    				+ "		<div>");
    		out.println("<div>\n"
    				+ "	Arina 1Kg <br> \n"
    				+ "	1,25€ <br> \n"
    				+ "	<button>Añadir al carro</button>\n"
    				+ "</div>");
    		out.println("<div>\n"
    				+ "	Macarrones 1Kg <br> \n"
    				+ "	1,50€ <br> \n"
    				+ "	<button>Añadir al carro</button>\n"
    				+ "	</div>");
    		out.println("<div>\n"
    				+ "	Lentejas 500 gramos <br> \n"
    				+ "	0,75€ <br> \n"
    				+ "	<button>Añadir al carro</button>\n"
    				+ "	</div>");
    		out.println("<div>\n"
    				+ "	Espaguetis 2Kg <br> \n"
    				+ "	2,00€ <br> \n"
    				+ "	<button>Añadir al carro</button>\n"
    				+ "	</div>");
    		//out.println("/div>");
    		//veo incoerencias en el codigo
    		out.println("</body>");
    		out.println("</html>");
    		
    	    }
    	    
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    	       throws ServletException, IOException {

    				
    	    	}
	
}
