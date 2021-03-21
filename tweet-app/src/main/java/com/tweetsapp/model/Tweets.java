package com.tweetsapp.model;

public class Tweets {

	private int tweetsUserId;
	private int tweetId;
	private String tweetDetails;

	public int getTweetsUserId() {
		return tweetsUserId;
	}

	public void setTweetsUserId(int tweetsUserId) {
		this.tweetsUserId = tweetsUserId;
	}

	public int getTweetId() {
		return tweetId;
	}

	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}

	public String getTweetDetails() {
		return tweetDetails;
	}

	public void setTweetDetails(String tweetDetails) {
		this.tweetDetails = tweetDetails;
	}

	@Override
	public String toString() {
		return "Tweets [tweetsUserId=" + tweetsUserId + ", tweetId=" + tweetId + ", tweetDetails=" + tweetDetails + "]";
	}

}
