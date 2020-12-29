package service;

import java.util.List;

import dao.TweetDAO;
import model.Tweet;

public class GetTweetListLogic {

	public List<Tweet> execute() {
		TweetDAO dao = new TweetDAO();
		List<Tweet> tweetList = dao.findAll();
		return tweetList;
	}

}
