<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage = "ErrorEdadNoValida.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="nuevoUsuario" class="prueba_formulario.Usuario" scope="session"/>
	<jsp:setProperty name="nuevoUsuario" property="*"/>
	
	<p>Nombre/s:
		<jsp:getProperty name="nuevoUsuario" property="nombreUsuario"/>
	</p>
	
	<p>Apellido/s:
		<jsp:getProperty name="nuevoUsuario" property="apellidosUsuario"/>
	</p>
	<p>Edad:
		<jsp:getProperty name="nuevoUsuario" property="edadUsuario"/>
	</p>
</body>
</html>