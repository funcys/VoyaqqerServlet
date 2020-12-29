package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//	リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");

		//	Userインスタンス（ユーザー情報）の生成
		User inputUser = new User(user_name,password);

		//ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		User loginUser = loginLogic.execute(inputUser);

		//ログインが成功したか判定
		if(loginUser != null) {
		//ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
		}
		//	ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request,response);


	}

}
