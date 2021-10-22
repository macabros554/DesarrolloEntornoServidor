<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>ejemplo de creación de bean con parámetros enviados desde un formulario</title>
	</head>
	<body>
		<jsp:useBean id="student" class="dwes.StudentBean" scope="session"/>
		<jsp:setProperty name="student" property="*"/>
		
		<p>Student First Name:
			<jsp:getProperty name="student" property="firstName"/>
		</p>
		
		<p>Student Last Name:
			<jsp:getProperty name="student" property="lastName"/>
		</p>
		
		<form action="result_adult.jsp" method="POST">
			Age: <input type="number" name="age">
			<br/>
			<input type="submit" value="submit">
		</form>
	</body>
</html>