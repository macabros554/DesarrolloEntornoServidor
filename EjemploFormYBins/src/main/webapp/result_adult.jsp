<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>ejemplo de uso de bean de sesión</title>
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
		
		<p>Student is adult:
			<jsp:getProperty name="student" property="adult"/>
		</p>
	</body>
</html>