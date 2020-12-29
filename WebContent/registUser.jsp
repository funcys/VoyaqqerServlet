<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>voyaqqer 新規登録</h1>
<% if(errorMsg != null) { %>
	<p style="color:red;font-weight:bold"><%= errorMsg %></p>
<% }%>
<form action="/Voyaqqer/RegistUser" method="post">
<p>ユーザー情報を入力してください</p>
ユーザー名：<input type="text" name="user_name"><br>
パスワード：<input type="password" name="password"><br>
プロフィール：<input type="text" name="profile"><br>
ユーザーアイコンのパス：<input type="text" name="user_icon"><br>

<input type="submit" value="登録する">
<p><a href="index.jsp">ログイン画面に戻る</a></p>
</form>
</body>
</html>