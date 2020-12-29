package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.GetUserDetailLogic;


@WebServlet("/UserDetail")
public class UserDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータの取得
		int user_id = Integer.parseInt(request.getParameter("user_id"));

		//ユーザー詳細情報取得の実行
		GetUserDetailLogic getUserDetailLogic = new GetUserDetailLogic();
		User userDetail = new User();

		userDetail = getUserDetailLogic.execute(user_id);
		request.setAttribute("userDetail",userDetail);

		//変数の設定
		//String forwardUrl;	//フォワード先URL

		//	ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDetail.jsp");
		dispatcher.forward(request,response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
