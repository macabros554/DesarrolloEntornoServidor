<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page errorPage = "ErrorSesionNoIniciada.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Login SuperMercado</title>
<link href="Estilos.css" rel="stylesheet">

</head>
<body>
	<div class="divPadre">
		<form action="/ProyectoCarrito/login" method="post">
			<label for="login">Nombre de usuario:</label>
			<input type="text" name="nombreUsuario" required>
			<br/>
			<label for="contra">Contraseña:</label>
			<input type="password" name="contrasenia" required> 
			<br>
			<input type="submit" value="Enviar" class="botonForm">
		</form>
	</div>

</body>
</html>