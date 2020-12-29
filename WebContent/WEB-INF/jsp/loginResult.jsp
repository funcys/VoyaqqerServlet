<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
//	セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>voyaqqerへようこそ</title>
</head>
<body>
<h1>voyaqqerログイン</h1>
<% if(loginUser != null) { %>
	<p>ログインに成功しました</p>
	<p>ようこそ<%= loginUser.getUserName() %>さん</p>
	<a href="/Voyaqqer/Main">つぶやき投稿・閲覧へ</a>
<%} else { %>
	<p>ログインに失敗しました</p>
	<a href="/Voyaqqer/">TOPへ</a>
<%} %>

</body>
</html>