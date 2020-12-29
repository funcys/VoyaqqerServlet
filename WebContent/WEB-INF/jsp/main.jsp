<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "model.User,model.Tweet,java.util.List" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープからつぶやきリストを取得
List<Tweet> tweetList = (List<Tweet>) request.getAttribute("tweetList");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>voyaqqer</title>
</head>
<body>
<h1>voyaqqerメイン画面</h1>
<p>
<%= loginUser.getUserName() %>さん、ログイン中
<a href= "/Voyaqqer/Logout">ログアウト</a>
<a href="/Voyaqqer/UpdateUser">ユーザー情報の編集</a>
</p>
<p><a href= "/Voyaqqer/Main">更新</a></p>
<form action="/Voyaqqer/Main" method= "post">
<input type= "text" name="text">
<input type= "submit" value= "つぶやく">
</form>
<% if(errorMsg != null) { %>
	<p><%= errorMsg %></p>
	<% }%>
	<% for(Tweet tweet : tweetList){ %>
	<p><a href="/Voyaqqer/UserDetail?user_id=<%= tweet.getUserId() %>"><%= tweet.getUserName() %></a>←ユーザーID=<%= tweet.getUserId() %>(仮表示)<%= tweet.getTweetContent()  %>	<br><%= tweet.getTweetCreatedDate() %></p>

	<hr>
	<% } %>

</body>
</html>