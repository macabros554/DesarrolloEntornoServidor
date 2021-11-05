<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page errorPage = "ErrorSesionNoIniciada.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Login SuperMercado</title>

</head>
<body>
	<form action="/ProyectoCarrito/login" method="post">
		Nombre: <input type="text" name="nombreUsuario" required>
		<br/>
		Contraseña: <input type="text" name="contrasenia" required>
		<br>
		<input type="submit" value="Enviar">
	</form>
</body>
</html>