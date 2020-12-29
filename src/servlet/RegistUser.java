package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.RegistUserLogic;

@WebServlet("/RegistUser")
public class RegistUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String profile = request.getParameter("profile");
		String user_icon = request.getParameter("user_icon");
		Date registDate = new Date();


		//変数の設定
		String forwardUrl;	//フォワード先URL


		////入力チェックの実行////
		//名前の未入力チェック
		boolean validationFlg = true;
		String errorMsg = "";
		if(user_name.equals("")) {
			errorMsg = "名前が入力されていません<br>";
			validationFlg = false;
		}
		//その他もろもろのﾁｪｯｸをしていく。チェック不適合なら、validationFlgをfalseにする。
		if (password.equals("")) {
			errorMsg = errorMsg + "パスワードが入力されていません<br>";
			validationFlg = false;
		}
		if (profile.equals("")) {
			errorMsg = errorMsg + "プロフィールが入力されていません<br>";
			validationFlg = false;
		}


		request.setAttribute("errorMsg",errorMsg);
		//フォワード先のURLをセットする。
		if(validationFlg == false) {
			forwardUrl = "/registUser.jsp";
		}else {
			forwardUrl = "/WEB-INF/jsp/registUserResult.jsp";
		}




		//新規登録処理　　validationFlgがtrueの場合。
		if(validationFlg == true) {
			//	Userインスタンス（ユーザー情報）の生成
			User registUser = new User(0,user_name,password,profile,user_icon,registDate,registDate);

			//Serviceのユーザー登録クラスの、登録実行
			RegistUserLogic registUserLogic = new RegistUserLogic();
			registUserLogic.execute(registUser);
		}

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardUrl);
		dispatcher.forward(request, response);
	}

}
