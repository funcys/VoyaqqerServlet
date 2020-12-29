package service;

import dao.TweetDAO;
import model.Tweet;

public class PostTweetLogic {
	public void execute(Tweet tweet) {	//引数を1つに変更
		TweetDAO dao = new TweetDAO();
		dao.create(tweet);

	}

}
