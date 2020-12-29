package model;

import java.util.List;

import service.GetUserListLogic;

public class LoginLogic {

	//引数でユーザークラスを受け取り、戻り値でユーザークラス（ユーザーID、ユーザーネーム、パスワード）を返却する
	public User execute(User inputUser) {

		//インプット情報の取得
		String inputUserName = inputUser.getUserName();
		String inputPassword = inputUser.getPassword();

		//DBアクセスクラスを呼び出し、ユーザーテーブルから登録済みユーザー情報（ユーザーID、ユーザー名、パスワード）を取得する
		GetUserListLogic getUserListLogic = new GetUserListLogic();
		List<User> userList = getUserListLogic.execute();

		//ログイン可否フラグを宣言する
		Boolean loginFlg = false;

		//返却する戻り値を設定する
		User loginUser = new User();

		//拡張for文で、DBから取得したユーザーテーブルの情報を一つずつ出力し、インプットのユーザー情報と比較する
		for(User user : userList) {
			if((inputUserName.equals(user.getUserName())) && inputPassword.equals(user.getPassword()) ) {	//ユーザー名と、パスワードがどちらも等しいか比較
				loginFlg = true;
				int login_user_id = user.getUserId();	//DBのユーザーIDを出力しておく
				loginUser = new User(login_user_id,inputUserName,inputPassword );
				break;
			} else {}
		}
		//loginFlgがtrueなら、戻り値にログイン成功ユーザー情報（ID,名前,パスワード）をセット、trueでなければnullをセット
		if(loginFlg == true) {
			return loginUser;
		} else {
			return null;
		}
	}

}
