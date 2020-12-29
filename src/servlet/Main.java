package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Tweet;
import model.User;
import service.GetTweetListLogic;
import service.PostTweetLogic;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;


	//GETメソッド。
	protected  void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		/* メインツイート画面表示処理 */

		//つぶやきリストを取得して、リスエストスコープに保存
		GetTweetListLogic getTweetListLogic = new GetTweetListLogic();
		List<Tweet> tweetList = getTweetListLogic.execute();
		request.setAttribute("tweetList",tweetList);


		//ログインしているか確認
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null) {
			//ログインしていない場合
			//リダイレクトする
			response.sendRedirect("/Voyaqqer/");

		} else { //ログイン済みの場合
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request,response);

		}
	}


	//POSTメソッド
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException {

		/* ツイートを実行する処理 */

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		//入力値チェック
		if(text != null && text.length() != 0) {

			//セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			//つぶやきをつぶやきリストに追加
			Tweet tweet = new Tweet(loginUser.getUserName(),text);
			PostTweetLogic postTweetLogic = new PostTweetLogic();
			postTweetLogic.execute(tweet);

		} else {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg","つぶやきが入力されていません");
		}
		//つぶやきリストを取得して、リクエストスコープに保存
		GetTweetListLogic getTweetListLogic = new GetTweetListLogic();
		List<Tweet> tweetList = getTweetListLogic.execute();
		request.setAttribute("tweetList",tweetList);

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}