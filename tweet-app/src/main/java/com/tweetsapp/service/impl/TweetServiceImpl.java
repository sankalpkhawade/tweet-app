package com.tweetsapp.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.tweetsapp.dao.impl.TweetDAOImpl;
import com.tweetsapp.dao.impl.UserDetailsDAOImpl;
import com.tweetsapp.model.Tweets;
import com.tweetsapp.model.UserDetails;
import com.tweetsapp.service.TweetService;

public class TweetServiceImpl implements TweetService {

	UserDetailsDAOImpl userDetailsDAOImpl = new UserDetailsDAOImpl();
	TweetDAOImpl tweetDAOImpl = new TweetDAOImpl();
	Scanner sc = new Scanner(System.in);

	@Override
	public void postTweet() {

		System.out.println("Post your tweet :");
		String tweets = sc.nextLine();
		tweetDAOImpl.postTweet(userDetailsDAOImpl.getLoggedInUsserForTweet(), tweets);
		System.out.println("Tweet Posted Succsfully!!");
	}

	@Override
	public void viewMyTweets() {

		List<String> myTweets = tweetDAOImpl.viewMyTweets(userDetailsDAOImpl.getLoggedInUsserForTweet());

		System.out.println("--------------------------------------------------------------");
		System.out.println("Below is the list of your tweets :\t\t");
		System.out.println("--------------------------------------------------------------");
		for (String myTweet : myTweets) {
			System.out.println(myTweet);
		}
		System.out.println("--------------------------------------------------------------");
	}

	@Override
	public void viewAllTweets() {

		List<Tweets> allTweets = tweetDAOImpl.viewAllTweets();

		System.out.println("--------------------------------------------------------------");
		System.out.println("Below is the list of all tweets :\t\t");
		System.out.println("--------------------------------------------------------------");
		System.out.println("UserId\tTweets");
		System.out.println("****************************************************************");
		for (Tweets allTweet : allTweets) {
			System.out.println(allTweet.getTweetsUserId() + "\t" + allTweet.getTweetDetails());
		}
		System.out.println("--------------------------------------------------------------");
	}

}
