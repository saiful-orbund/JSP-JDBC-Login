package org.source.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class UserDbUtil {
	private DataSource dataSource;

	public UserDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<User> getUsers() throws Exception {
		List<User> users = new ArrayList<>();

		Connection myConn = null;
		Statement myStmnt = null;
		ResultSet myRes = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "select * from mytable";
			myStmnt = myConn.createStatement();

			myRes = myStmnt.executeQuery(sql);

			while (myRes.next()) {
				// int id = myRes.getInt("id");
				String name = myRes.getString("username");
				String email = myRes.getString("email");

				User user = new User(name, email);

				users.add(user);
			}
			return users;
		} finally {
			close(myConn, myStmnt, myRes);
		}

	}

	private void close(Connection myConn, Statement myStmnt, ResultSet myRes) {
		try {
			if (myRes != null) {
				myRes.close();
			}
			if (myStmnt != null) {
				myStmnt.close();
			}
			if (myConn != null) {
				myConn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public User getUser(String username, String password) throws Exception {
		User theUser = null;

		Connection myCon = null;
		PreparedStatement myStmnt = null;
		ResultSet myRes = null;

		try {

			myCon = dataSource.getConnection();
			String sql = "Select * from mytable where username = ?;";

			myStmnt = myCon.prepareStatement(sql);
			myStmnt.setString(1, username);
			//myStmnt.setString(2, password);

			myRes = myStmnt.executeQuery();
			if (myRes.next()) {
				// int id = myRes.getInt("id");
				String name = myRes.getString("username");
				String email = myRes.getString("email");

				theUser = new User(name, email);

			} else {
				return null;
				//throw new Exception("Could not find username: " + username);
			}

			return theUser;

		} finally {
			close(myCon, myStmnt, myRes);
		}

	}
}