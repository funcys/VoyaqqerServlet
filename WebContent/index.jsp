<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>voyaqqerへようこそ</title>
</head>
<body>

<h1>voyaqqerへようこそ</h1>
<form action="/Voyaqqer/Login" method="post">

ユーザー名：<input type="text" name="user_name"><br>
パスワード：<input type="password" name="password"><br>
<input type="submit" value="ログイン">
<p><a href="registUser.jsp">新規ユーザー登録</a></p>
</form>
</body>
</html>