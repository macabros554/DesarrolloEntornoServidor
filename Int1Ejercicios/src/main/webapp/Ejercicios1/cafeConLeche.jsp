<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage = "ErrorValorNoSoportado.jsp" %>
<!DOCTYPE html>

	
      <jsp:useBean id = "cafeCL1" class = "Ejercicios1.CafeConLeche">
         <jsp:setProperty name = "cafeCL1" property = "pedidos" value = "2"/>
      </jsp:useBean>
      
        <%
		   if (cafeCL1.getPedidos() > cafeCL1.getCantidadDisponible()) {
		      throw new RuntimeException("No hay suficiente cafe");
		   }else if(cafeCL1.getPedidos() < 0){
			   throw new RuntimeException("No se puede meter cafe negativo");
		   }
		%>
      
      <p>El Precio del cafe con ceche es:
      	<jsp:getProperty name = "cafeCL1" property = "precio"/>€
      </p>
      <p>La cantidad pedida de cafe con leche es:
      	<jsp:getProperty name = "cafeCL1" property = "pedidos"/>
      </p>
      <p>La cantidad disponible actualmente de cafe con leche es:
      	<jsp:getProperty name = "cafeCL1" property = "cantidadDisponible"/>
      </p>
      <p>El precio total es:
      	<%= cafeCL1.calcularPrecio() %>€
      </p>
      