<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "model.User,model.Tweet,java.util.List" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//リクエストスコープに保存されたユーザー詳細を取得
User userDetail = (User)request.getAttribute("userDetail");

 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>voyaqqer</title>
</head>
<body>
<h1>voyaqqerユーザー詳細画面</h1>
<p><%= loginUser.getUserName() %>さん、ログイン中<br><a href="/Voyaqqer/Main">メイン画面に戻る</a></p>
名前：<%= userDetail.getUserName() %><br>
プロフィール：<%= userDetail.getProfile() %><br>
voyaqqer登録日：<%= userDetail.getCreatedDate() %>

</body>
</html>