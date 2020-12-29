package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.UpdateUserLogic;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		/*とりあえず、セッションスコープから、ログイン中ユーザー情報を受け取りたい*/
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");


		//ユーザー情報編集画面に遷移する

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateUser.jsp");
		dispatcher.forward(request,response);




		//response.getWriter().append("Served at: " + loginUser.getUser_name()).append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*とりあえず、セッションスコープから、ログイン中ユーザー情報を受け取りたい*/
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String profile = request.getParameter("profile");
		String user_icon = request.getParameter("user_icon");
		Date updateDate = new Date();

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
		if (user_icon.equals("")) {
			errorMsg = errorMsg + "ユーザーアイコンが入力されていません<br>";
			validationFlg = false;
		}

		request.setAttribute("errorMsg",errorMsg);
		//フォワード先のURLをセットする。
		if(validationFlg == false) {
			forwardUrl = "/registUser.jsp";
		}else {
			forwardUrl = "/WEB-INF/jsp/updateUserResult.jsp";
		}




		//ユーザー情報変更処理　　validationFlgがtrueの場合。
		if(validationFlg == true) {
			//	Userインスタンス（ユーザー情報）の生成
			User updateUser = new User(loginUser.getUserId(),user_name,password,profile,user_icon,updateDate,updateDate);

			//Serviceのユーザー登録クラスの、登録実行
			UpdateUserLogic updateUserLogic = new UpdateUserLogic();
			updateUserLogic.execute(updateUser);
		}

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardUrl);
		dispatcher.forward(request, response);

	}

}
