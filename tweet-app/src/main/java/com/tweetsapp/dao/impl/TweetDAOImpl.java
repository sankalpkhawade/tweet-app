package com.tweetsapp.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tweetsapp.TestConnection.TestConnection;
import com.tweetsapp.dao.TweetDAO;
import com.tweetsapp.model.Tweets;

public class TweetDAOImpl implements TweetDAO {

	@Override
	public void postTweet(int tweetUserId, String tweets) {
		String post = "Insert into iiht.tweets(tweets_user_id,tweet_details) values(" + "'" + tweetUserId + "'" + "," + "'" + tweets + "')";

		try (Connection conn = TestConnection.testConnection()) {

			Statement stmt = conn.createStatement();
			boolean results = stmt.execute(post);

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> viewMyTweets(int tweetUserId) {
		String selectMytweets = "select tweet_details from iiht.tweets where tweets_user_id =" + "'" + tweetUserId + "'";

		try (Connection conn = TestConnection.testConnection()) {

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(selectMytweets);
			List<String> mytweets = new ArrayList<>();

			while (results.next()) {
				mytweets.add(results.getString("tweet_details"));
			}
			return mytweets;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Tweets> viewAllTweets() {
		String sql_select_alltweets = "select * from iiht.tweets";
		try (Connection conn = TestConnection.testConnection()) {

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql_select_alltweets);// executing query
			List<Tweets> alltweets = new ArrayList<>();

			while (results.next()) {
				Tweets modelObj = new Tweets();
				modelObj.setTweetsUserId(results.getInt("tweets_user_id"));
				modelObj.setTweetDetails(results.getString("tweet_details"));
				alltweets.add(modelObj);

			}
			return alltweets;

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
