<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.source.com.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");

	if (username.equals("saiful") && password.equals("1234")) {

		User user = new User("saiful", "saiful@gmail.com");
		
		%>
		
		<% 
		request.getSession().setAttribute("user", user);
		response.sendRedirect("welcome.jsp");
		
	} else {
		response.sendRedirect("index.jsp?message=Not correct, check user or password");
	}
	
	%>

</body>
</html>