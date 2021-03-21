package com.tweetsapp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.tweetsapp.dao.impl.TweetDAOImpl;
import com.tweetsapp.dao.impl.UserDetailsDAOImpl;
import com.tweetsapp.model.UserDetails;
import com.tweetsapp.service.UserService;

public class UserServiceImpl implements UserService {

	UserDetailsDAOImpl userDetailsDAOImpl = new UserDetailsDAOImpl();
	TweetDAOImpl tweetDAOImpl = new TweetDAOImpl();
	Scanner sc = new Scanner(System.in);

	@Override
	public void getRegister() {

		UserDetails userDetails = new UserDetails();
		System.out.println("Enter your Firstname : ");
		String firstName = this.takeRequiredInput("First Name");
		userDetails.setFirstName(firstName);

		System.out.println("Enter your Lastname : ");
		String lastName = sc.nextLine();
		boolean isLastNameNotValid = false;
		do {
			if (lastName != null) {
				isLastNameNotValid = false;
			} else {
				isLastNameNotValid = true;
				break;
			}
		} while (isLastNameNotValid);
		userDetails.setLastName(lastName);
		System.out.println("Enter your  Gender 'M' for Male or 'F' for Female :");
		String gender = "";
		boolean isGenderNotValid = false;
		do {
			gender = this.takeRequiredInput(gender);
			if (gender.equals("M") || gender.equals("F") || gender.equals("m") || gender.equals("f")) {
				isGenderNotValid = false;

			} else {
				isGenderNotValid = true;
				System.out.println("You have Entered Wrong Entry :( please Enter 'M' for Male or 'F' for Female");
			}

		} while (isGenderNotValid);
		userDetails.setGender(gender);
		System.out.println("Enter your DOB(YYYY-MM-DD) : ");
		boolean isDateOfBirthNotValid = false;
		Date format = null;
		String date = sc.nextLine();
		do {
			if (date != null && !date.isEmpty()) {
				try {
					format = new SimpleDateFormat("yyy-mm-dd").parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				isDateOfBirthNotValid = false;
			} else {
				isDateOfBirthNotValid = true;
				break;
			}
		} while (isDateOfBirthNotValid);
		userDetails.setDateOfBirth(format);

		System.out.println("Enter your Email/Username : ");
		String email = "";
		boolean isEmailNotValid = false;

		do {
			email = this.takeRequiredInput("Email");

			String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{​​​​​​​​|}​​​​​​​​~^.-]+@[a-zA-Z0-9.-]+$";

			if (email.matches(regex)) {

				isEmailNotValid = false;
			} else {
				isEmailNotValid = true;
				System.out.println("Entered Wrong Entry :( please Enter Valid Email");
			}

		} while (isEmailNotValid);
		userDetails.setEmail(email);
		System.out.println("Enter your Password : ");
		String password = this.takeRequiredInput("Password");
		userDetails.setPassword(password);
		System.out.println("Enter Y to Submit or Any Other key to GoBack");
		String submit = sc.nextLine();

		if (submit.equals("Y") || submit.equals("y")) {
			boolean isEmailRegistered = false;

			do {
				isEmailRegistered = userDetailsDAOImpl.checkIfUserExists(email);
				if (isEmailRegistered) {
					System.out.println("User Already Registered :( Please enter another email ");
					System.out.println("Please Enter New email below");
					email = sc.nextLine();
					userDetails.setEmail(email);

				} else {
					userDetailsDAOImpl.register(userDetails);
					System.out.println("Registered Succesfully!! Now you can login");
				}
			} while (isEmailRegistered);
		} else {
			System.out.println("Unsuccessful Registration!!\n Please try again");
		}
	}

	@Override
	public boolean getLogin() {

		System.out.println("Enter your Email :");
		String email = "";
		boolean isEmailNotValid = false;

		do {
			email = this.takeRequiredInput("Email");

			String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{​​​​​​​​|}​​​​​​​​~^.-]+@[a-zA-Z0-9.-]+$";

			if (email.matches(regex)) {

				isEmailNotValid = false;
			} else {
				isEmailNotValid = true;
				System.out.println("Entered Wrong Entry :( please Enter Valid Email");
			}

		} while (isEmailNotValid);

		System.out.println("Enter you Password :");
		String password = this.takeRequiredInput("Password");

		boolean status = userDetailsDAOImpl.login(email, password);
		if (status) {
			System.out.println("LogedIn Succesfully!!\nPlease choose service from below :");
			userDetailsDAOImpl.changeLogInStatus(email, status);
			return status;
		} else {
			System.out.println("You have entered Invalid Credentials!!");
			return status;
		}
	}

	@Override
	public void viewAllUsers() {

		List<UserDetails> allUsers = userDetailsDAOImpl.viewAllUsers();

		System.out.println("------------------------------------------------------------------------------");
		System.out.println("List of Registered Users : \t\t");
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("FirstName" + "\tLastName" + "\tEmail\t" + "\tDate Of Birth" + "\tGender");
		System.out.println("*******************************************************************************");
		for (UserDetails allUser : allUsers) {

			System.out.println("" + allUser.getFirstName() + "\t\t" + allUser.getLastName() + "\t\t"
					+ allUser.getEmail() + "\t" + allUser.getDateOfBirth() + "\t" + allUser.getGender());
		}
		System.out.println("------------------------------------------------------------------------------");
	}

	@Override
	public void resetPassword() {

		System.out.println("Enter Your Email :");
		String email = sc.nextLine();
		System.out.println("Enter Your Old Password :");
		String oldPassword = sc.nextLine();
		System.out.println("Enter Your New Password :");
		String newpassword = sc.nextLine();
		userDetailsDAOImpl.resetPassword(email, newpassword, oldPassword);
		System.out.println("Password Changed Successfully !! :) Now you can Login with new password");
	}

	@Override
	public void forgotPassword() {

		System.out.println("Enter Your Email :");
		String email = sc.nextLine();
		System.out.println("Enter Your New Password :");
		String newPassword = sc.nextLine();
		userDetailsDAOImpl.forgotPassword(email, newPassword);
		System.out.println("Password Set Succesfully !! :) Now you can Login");
	}

	@Override
	public boolean logOut() {

		System.out.println("Logged out successfully!!\n");
		return userDetailsDAOImpl.changeLogInStatus(userDetailsDAOImpl.getLoggedInUser(), false);
	}

	public String takeRequiredInput(String input) {

		boolean isInputNotValid = false;
		String value = "";
		do {
			value = sc.nextLine();
			if (value.isEmpty()) {
				isInputNotValid = true;
				System.out.println(input + " cannot be Blank :( ");
				System.out.println("Please Enter your " + input);
			} else {
				isInputNotValid = false;
			}
		} while (isInputNotValid);
		return value;
	}

}
