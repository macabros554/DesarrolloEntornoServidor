<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage = "ErrorEdadNoValida.jsp" %>
<!DOCTYPE html>
<html>
	<body>
		<form action="result.jsp" method="POST">
			Nombre: <input type="text" name="nombres" required>
			<br/>
			Apellidos: <input type="text" name="apellidos" required>
			<br/>
			Edad: <input type="number" name="age" required>
			<input type="submit" value="Enviar">
		</form>
	</body>
</html>