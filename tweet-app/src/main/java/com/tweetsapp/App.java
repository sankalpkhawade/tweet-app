package com.tweetsapp;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import com.tweetsapp.TestConnection.TestConnection;
import com.tweetsapp.dao.impl.UserDetailsDAOImpl;
import com.tweetsapp.service.impl.TweetServiceImpl;
import com.tweetsapp.service.impl.UserServiceImpl;

public class App {
	public static void main(String[] args) throws IOException, ParseException {

		int choice;
		boolean status = false;
		TestConnection test = new TestConnection();
		test.testConnection();
		Scanner sc = new Scanner(System.in);
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		TweetServiceImpl tweetServiceImpl = new TweetServiceImpl();
		System.out.println("-------------------------------------------------------------");
		System.out.println("\t\tWelcome to Tweet Application");
		System.out.println("-------------------------------------------------------------\n");
		do {
			System.out.print("\n*************************");
			System.out.print("\n*\tMenu\t\t*");
			System.out.print("\n*************************");
			if (!status) {
				System.out.println("\n*  1.Register\t\t*" + "\n*  2.Login\t\t*" + "\n*  3.Forgot Password\t*"
						+ "\n* -1.Exit\t\t*\n*************************");
			} else {
				System.out.println("\n* 1.Post a tweet\t*" + "\n* 2.View my tweets\t*" + "\n* 3.View all tweets\t*"
						+ "\n* 4.View all Users\t*" + "\n* 5.Reset Password\t*"
						+ "\n* 6.Logout\t\t*\n*************************");

			}
			System.out.println("\nEnter your choice : ");
			choice = sc.nextInt();

			if (!status) {
				switch (choice) {
				case 1:
					userServiceImpl.getRegister();
					break;
				case 2:
					status = userServiceImpl.getLogin();
					break;
				case 3:
					userServiceImpl.forgotPassword();
					break;
				case -1:
					System.out.println("Thank you for using our services! =:)");
					System.exit(0);
				default:
					System.out.println("Invalid entry");
					break;
				}
			} else {
				switch (choice) {
				case 1:
					tweetServiceImpl.postTweet();
					break;
				case 2:
					tweetServiceImpl.viewMyTweets();
					break;
				case 3:
					tweetServiceImpl.viewAllTweets();
					break;
				case 4:
					userServiceImpl.viewAllUsers();
					break;
				case 5:
					userServiceImpl.resetPassword();
					status = userServiceImpl.logOut();
					break;
				case 6:
					status = userServiceImpl.logOut();
					break;
				default:
					System.out.println("Invalid entry");
					break;
				}
			}
		} while (choice != -1);
		sc.close();
	}
}
