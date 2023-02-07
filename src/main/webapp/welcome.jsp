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
	if (request.getSession().getAttribute("user") != null) {
		/* if (request.getSession().getAttribute("user").getClass()
		.equals(new User("saiful", "saiful@gmail.com").getClass())) */
		/* 
		if (request.getSession().getAttribute("user").equals(new User("saiful", "saiful@gmail.com"))) { */
	%>
	<h1>You are logged in</h1>
	<hr>
	<%
	/* }  *//* else {
			session.setAttribute("user", null);
			response.sendRedirect("index.jsp");
			} */
	} else {
	session.setAttribute("user", null);
	response.sendRedirect("index.jsp");
	}
	%>

	<form action="AppServlet" method=get>
		<input type="hidden" name="command" value="LOGOUT" />
		<input type="submit" value="logout" name="logout" />
	</form>
</body>
</html>