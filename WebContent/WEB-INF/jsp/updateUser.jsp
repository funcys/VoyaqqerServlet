<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "model.User,java.util.List" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User loginUser = (User)session.getAttribute("loginUser");

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー編集画面</title>
</head>
<body>
ユーザー編集画面　<a href= "/Voyaqqer/Main">[voyaqqerメイン画面へ戻る]<a/><br>
<%= loginUser.getUserName() %>さんのユーザー情報を編集します
<form action= "/Voyaqqer/UpdateUser" method= "post">
ユーザー名：<input type="text" name="user_name"><br>
パスワード：<input type="password" name="password"><br>
プロフィール：<input type="text" name="profile"><br>
ユーザーアイコンのパス：<input type="text" name="user_icon"><br>
<input type="submit" value= "変更する">
</form>
</body>
</html>