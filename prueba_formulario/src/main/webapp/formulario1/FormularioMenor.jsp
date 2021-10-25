<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage = "ErrorEdadNoValida.jsp" %>
<!DOCTYPE html>
<html>
	<body>
		<%! private int accesos = 0; %>
		<h1>Usuario Nº: <%= ++accesos %></h1>
		<form action="SaludoUsuario.jsp" method="POST">
			Nombre: <input type="text" name="nombreUsuario" required>
			<br/>
			Apellidos: <input type="text" name="apellidosUsuario" required>
			<br/>
			Edad: <input type="number" name="edadUsuario" required>

			<input type="submit" value="Enviar">
		</form>
	</body>
</html>

