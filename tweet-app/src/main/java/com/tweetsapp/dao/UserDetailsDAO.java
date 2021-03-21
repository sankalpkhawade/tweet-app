package com.tweetsapp.dao;

import java.util.List;

import com.tweetsapp.model.UserDetails;

public interface UserDetailsDAO {
	public List<UserDetails> viewAllUsers();

	public void register(UserDetails userDetails);

	public boolean login(String userName, String password);

	public void resetPassword(String email, String newpassword, String oldPassword);

	public void forgotPassword(String email, String newPassword);

	public boolean changeLogInStatus(String email, boolean status);

	public String getLoggedInUser();

	public boolean checkIfUserExists(String email);

}
