package org.source.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/AppServlet")
public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/test2")
	DataSource dataSource;

	UserDbUtil userDbUtil = null;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			userDbUtil = new UserDbUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String theCommand = request.getParameter("command");

		if (theCommand == null) {
			theCommand = "LOGIN";
		}

		switch (theCommand) {
		case "LOGIN":
			try {
				loginUser(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "CHECK":
			try {
				checkUser(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "LOGOUT":
			try {
				logOutUser(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				loginUser(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}

		/*
		 * UserDbUtil userDb = new UserDbUtil(dataSource);
		 * 
		 * try { out.print(userDb.getUsers().toString()); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */

		/*
		 * Connection conn = null; Statement myStmnt = null; ResultSet res = null;
		 * 
		 * try {
		 * 
		 * conn = dataSource.getConnection();
		 * 
		 * String sql = "select * from mytable"; myStmnt = conn.createStatement(); res =
		 * myStmnt.executeQuery(sql);
		 * 
		 * while (res.next()) { String email = res.getString("username");
		 * out.println(email); }
		 * 
		 * // Class.forName("com.mysql.jdbc.Driver"); // conn =
		 * DriverManager.getConnection("jdbc:mysql://localhost:3306/test2", "root",
		 * "5336");
		 * 
		 * } catch (Exception e) { out.print(e); }
		 */
	}

	private void logOutUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().setAttribute("user", null);
		loginUser(request, response);
		
	}

	private void checkUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// take param ==> request.getParameter
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		User user = null;
		if (username != null || pass != null)
			user = userDbUtil.getUser(username, pass);

		// check
		// set session
		// dispatch to welcome
		if (user != null) {
			request.getSession().setAttribute("user", user);
			RequestDispatcher dispatch = request.getRequestDispatcher("/welcome.jsp");
			dispatch.forward(request, response);
		} else { 
			loginUser(request, response);
		}

	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// dispatch to welcome
		RequestDispatcher dispatch = request.getRequestDispatcher("/index.jsp");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
