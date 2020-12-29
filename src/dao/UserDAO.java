package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.User;

public class UserDAO {

	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";

	public List<User> findAll(){
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		try {
			//JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);

			//データベースへ接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/voyaqqer?" + "user=root&password=root");

			//select文の準備
			String sql =
					"select user.user_id, user.user_name, user.password from user order by user.user_id desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//select文を実行
			ResultSet rs = pStmt.executeQuery();

			//select文の結果をArrayListに格納
			while(rs.next()) {
				int user_id = rs.getInt("USER_ID");
				String user_name = rs.getString("USER_NAME");
				String password = rs.getString("PASSWORD");
				User user = new User(user_id,user_name,password);
				userList.add(user);
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
		return userList;
	}

	public User getDetail(int userId){
		Connection conn = null;
		User userDetail = new User();
		try {
			//JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);

			//データベースへ接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/voyaqqer?" + "user=root&password=root");

			//select文の準備
			String sql =
					"select user_id,user_name,password,profile,user_icon,created_date,update_date from user where user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//insert文中の「？」に使用する値を設定しSQLを完成
			pStmt.setInt(1, userId);


			//select文を実行
			ResultSet rs = pStmt.executeQuery();

			//select文の結果をArrayListに格納
			while(rs.next()) {
				int user_id = rs.getInt("USER_ID");
				String user_name = rs.getString("USER_NAME");
				String password = rs.getString("PASSWORD");
				String profile = rs.getString("PROFILE");
				String user_icon = rs.getString("USER_ICON");
				Date created_date = rs.getDate("CREATED_DATE");
				Date update_date = rs.getDate("UPDATE_DATE");

				//User userDetail = new User(user_id,user_name,password,profile,user_icon,created_date,update_date);
				userDetail.setUserId(user_id);
				userDetail.setUserName(user_name);
				userDetail.setPassword(password);
				userDetail.setProfile(profile);
				userDetail.setUserIcon(user_icon);
				userDetail.setCreatedDate(created_date);
				userDetail.setUpdateDate(update_date);
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
		return userDetail;
	}
	public boolean create(User user) {
		Connection conn = null;
		try {

			//JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
			//データベースへ接続

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/voyaqqer?" + "user=root&password=root");

			//insert文の準備
			String sql = "insert user(user_id,user_name,password,profile,user_icon,created_date,update_date)values(null,?,?,?,?,now(),now());";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//insert文中の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1, user.getUserName());
			pStmt.setString(2, user.getPassword());
			pStmt.setString(3, user.getProfile());
			pStmt.setString(4, user.getUserIcon());

			//insert文を実行
			int result = pStmt.executeUpdate();

			if(result != 1) {
				return false;
			}
			}catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			} finally {
				//データベース切断
				if(conn != null) {
					try {
						conn.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
		return true;
	}
	public boolean update(User user) {
		Connection conn = null;
		try {

			//JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
			//データベースへ接続

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/voyaqqer?" + "user=root&password=root");

			//update文の準備
			String sql = "update user set user_name = ?,password = ?,profile = ?,user_icon = ?,created_date = now(),update_date = now() where user_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//update文中の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1, user.getUserName());
			pStmt.setString(2, user.getPassword());
			pStmt.setString(3, user.getProfile());
			pStmt.setString(4, user.getUserIcon());
			pStmt.setInt(5, user.getUserId());

			//update文を実行
			int result = pStmt.executeUpdate();

			if(result != 1) {
				return false;
			}
			}catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			} finally {
				//データベース切断
				if(conn != null) {
					try {
						conn.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
		return true;
	}
}
