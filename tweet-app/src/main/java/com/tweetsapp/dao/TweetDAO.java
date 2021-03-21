package com.tweetsapp.dao;

import java.io.IOException;
import java.util.List;

import com.tweetsapp.model.Tweets;

public interface TweetDAO {

	public void postTweet(int tweetUserId, String tweets);

	public List<String> viewMyTweets(int tweetUserId);

	public List<Tweets> viewAllTweets();
}  
