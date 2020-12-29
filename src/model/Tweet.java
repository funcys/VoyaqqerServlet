package model;

import java.io.Serializable;

public class Tweet implements Serializable {
	private int tweet_id;	//ツイートID
	private int user_id;	//ユーザーID
	private String user_name;	//ユーザー名
	private String tweet_content;	//つぶやき内容
	private String tweet_created_date;	//ツイート日時

	public Tweet() {};
	public Tweet(String user_name, String tweet_content) {
		this.user_name = user_name;
		this.tweet_content = tweet_content;
	}
	public Tweet(int tweet_id,int user_id,String user_name, String tweet_content, String tweet_created_date) {
		this.tweet_id = tweet_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.tweet_content = tweet_content;
		this.tweet_created_date = tweet_created_date;
	}


	public int getTweetId() {return tweet_id;}
	public int getUserId() {return user_id;}
	public String getUserName() {return user_name;}
	public String getTweetContent() {return tweet_content;}
	public String getTweetCreatedDate() {return tweet_created_date;}
}
