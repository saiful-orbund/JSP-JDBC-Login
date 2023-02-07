<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h2>Login Page</h2>
	<hr>

	<%
	String msg = "";
	msg = request.getParameter("message");
	if (msg == null) {
		msg = "";
	}
	%>
	<p style="background-color: Tomato;"><%=msg%></p>

	<form action="AppServlet" method="get">
		<input type="hidden" name="command" value="CHECK" />
		 Enter User Name: <input
			type="text" name="username" required /> <br> Enter password: <input
			type="password" name="password" required /> <br> <input
			type="submit" value="Login" />
	</form>

</body>
</html>