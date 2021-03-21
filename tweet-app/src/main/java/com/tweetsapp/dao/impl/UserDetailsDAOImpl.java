package com.tweetsapp.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.tweetsapp.TestConnection.TestConnection;
import com.tweetsapp.dao.UserDetailsDAO;
import com.tweetsapp.model.UserDetails;

public class UserDetailsDAOImpl implements UserDetailsDAO {

	@Override
	public List<UserDetails> viewAllUsers() {

		List<UserDetails> usersList = new ArrayList<UserDetails>();

		String sql_select = "Select * From iiht.user_details";
		try (Connection conn = TestConnection.testConnection()) {

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql_select);
			while (results.next()) {

				UserDetails users = new UserDetails();

				users.setFirstName(results.getString("firstName"));

				users.setLastName(results.getString("lastName"));
				users.setGender(results.getString("gender"));
				users.setDateOfBirth(results.getDate("date_of_birth"));
				users.setEmail(results.getString("email"));
				users.setPassword(results.getString("password"));
				users.setStatus(results.getBoolean("user_status"));

				usersList.add(users);

			}
			return usersList;
		} catch (SQLException | IOException e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public void register(UserDetails userDetails) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(userDetails.getDateOfBirth());

		String sql_insert = "Insert into iiht.user_details (firstname,lastname,gender,date_of_birth,email,password,user_status) values("
				+ "'" + userDetails.getFirstName() + "'" + "," + "'" + userDetails.getLastName() + "'" + "," + "'"
				+ userDetails.getGender() + "'" + "," + "'" + strDate + "'" + "," + "'" + userDetails.getEmail() + "'"
				+ "," + "'" + userDetails.getPassword() + "'" + "," + "false" + ")";
		try (Connection conn = TestConnection.testConnection()) {

			Statement stmt = conn.createStatement();
			boolean results = stmt.execute(sql_insert);

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean login(String userName, String password) {
		String login = "select * from iiht.user_details where email=" + "'" + userName + "'" + " and password = " + "'"
				+ password + "'";

		try (Connection conn = TestConnection.testConnection()) {

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(login);

			return results.next();

		} catch (SQLException | IOException e) {
			e.printStackTrace();

		}
		return false;
	}

	@Override
	public void resetPassword(String email, String newpassword, String oldPassword) {
		String sql_update = "update iiht.user_details set password = '" + newpassword + "' where email = '" + email
				+ "' and password = '" + oldPassword + "'";

		try (Connection conn = TestConnection.testConnection()) {

			Statement stmt = conn.createStatement();
			boolean results = stmt.execute(sql_update);// executing query

		} catch (SQLException | IOException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void forgotPassword(String email, String newPassword) {
		String sql_update = "update iiht.user_details set password = '" + newPassword + "' where email = '" + email
				+ "'";

		try (Connection conn = TestConnection.testConnection()) {

			Statement stmt = conn.createStatement();
			boolean results = stmt.execute(sql_update);// executing query

		} catch (SQLException | IOException e) {
			e.printStackTrace();

		}
	}

	@Override
	public boolean changeLogInStatus(String email, boolean status) {
		String sql_update = "update iiht.user_details set user_status = " + status + " where email = '" + email + "'";

		try (Connection conn = TestConnection.testConnection()) {

			Statement stmt = conn.createStatement();
			boolean results = stmt.execute(sql_update);// executing query

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String getLoggedInUser() {
		String loginUser = "select email from iiht.user_details where user_status = true LIMIT 1";
		try (Connection conn = TestConnection.testConnection()) {

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(loginUser);// executing query

			if (results.next()) {
				return results.getString("email");
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();

		}

		return null;
	}

	public int getLoggedInUsserForTweet() {
		String loginUser = "select user_id from iiht.user_details where user_status = true LIMIT 1";
		try (Connection conn = TestConnection.testConnection()) {
			
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(loginUser);
			
			if (results.next()) {
				return results.getInt("user_id");
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public boolean checkIfUserExists(String email) {
		String userValidation = "select email from iiht.user_details where email = '" + email + "'";
		try (Connection conn = TestConnection.testConnection()) {

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(userValidation);// executing query
			return results.next();

		} catch (SQLException | IOException e) {
			e.printStackTrace();

		}
		return false;
	}

}
