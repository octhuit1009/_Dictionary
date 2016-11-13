<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/13
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sign up page</title>
</head>
<body>
<h1>sign up</h1>
<form action="/user" method="post">
    <input type="hidden" name="action" value="register">
    <input type="text" name="email" placeholder="EMAIL" value="tester@test.com"><br>
    <input type="password" name="password" placeholder="PASSWORD" value="123"><br>
    <input type="submit" value="SIGN UP">
</form>
</body>
</html>
