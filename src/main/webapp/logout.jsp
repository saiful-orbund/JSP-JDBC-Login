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
	String logout = request.getParameter("logout");
	if (logout.equals("logout")) {
		session.setAttribute("user", null);
		response.sendRedirect("index.jsp?message=Logout successfully");
	}
	%>

</body>
</html>