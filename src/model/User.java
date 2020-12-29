package model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private int user_id;	//ユーザーID
	private String user_name; //	ユーザー名
	private String password;	//パスワード
	private String profile;		//プロフィール
	private String user_icon;	//ユーザーアイコンのパス
	private Date created_date;	//voyaqqer登録日時
	private Date update_date;	//更新日時

	public User() {}
	public User(String user_name,String password) {
		this.user_name = user_name;
		this.password = password;
	}

	//ログイン用コンストラクタ？
	public User(int user_id,String user_name,String password) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
	}

	//すぺてのフィールドを引数にしたコンストラクタ
	public User(int user_id,
			String user_name,
			String password,
			String profile,
			String user_icon,
			Date created_date,
			Date update_date) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.profile = profile;
		this.user_icon = user_icon;
		this.created_date = created_date;
		this.update_date = update_date;
	}

	//getter
	public int getUserId() {return user_id;}
	public String getUserName() {return user_name;}
	public String getPassword() {return password;}
	public String getProfile() {return profile;}
	public String getUserIcon() {return user_icon;}
	public Date getCreatedDate() {return created_date;}
	public Date getUpdateDate() {return update_date;}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	public void setUserName(String user_name) {
		this.user_name = user_name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public void setUserIcon(String user_icon) {
		this.user_icon = user_icon;
	}
	public void setCreatedDate(Date created_date) {
		this.created_date = created_date;
	}
	public void setUpdateDate(Date update_date) {
		this.update_date = update_date;
	}

}
