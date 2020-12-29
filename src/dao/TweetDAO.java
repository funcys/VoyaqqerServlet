package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Tweet;

public class TweetDAO {

	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";

	public List<Tweet> findAll() {
		Connection conn = null;
		List<Tweet> tweetList = new ArrayList<Tweet>();
		try {
			//JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);

			//データベースへ接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/voyaqqer?" + "user=root&password=root");


			//select文の準備
			String sql =
					"select tweet.tweet_id,tweet.user_id,user_name,tweet_content,tweet.created_date from tweet,user where user.user_id = tweet.user_id order by tweet_id desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//select文を実行
			ResultSet rs = pStmt.executeQuery();

			//select文の結果をArrayListに格納
			while(rs.next()) {
				int tweet_id = rs.getInt("TWEET_ID");
				int user_id = rs.getInt("USER_ID");
				String user_name = rs.getString("USER_NAME");
				String tweet_content = rs.getString("TWEET_CONTENT");
				String tweet_created_date = rs.getString("CREATED_DATE");
				Tweet tweet = new Tweet(tweet_id,user_id,user_name,tweet_content,tweet_created_date);
				tweetList.add(tweet);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}finally {
			//データベース切断
			if(conn!= null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return tweetList;
	}

	public boolean create(Tweet tweet) {
		Connection conn  = null;
		try {
			//データベースへ接続

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/voyaqqer?" + "user=root&password=root");

			//insert文の準備(idは自動連番なので指定しなくてよい)
			String sql = "insert into tweet(user_id,tweet_content,created_date,update_date) values((select user_id from user where user_name =?),?,now(),now());";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//insert文中の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1,tweet.getUserName());
			pStmt.setString(2,tweet.getTweetContent());

			//insert文を実行
			int result = pStmt.executeUpdate();

			if(result != 1) {
				return false;
				}
			}catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				//データベース切断
				if(conn != null) {
					try {
						conn.close();
					} catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
		return true;
	}
}
