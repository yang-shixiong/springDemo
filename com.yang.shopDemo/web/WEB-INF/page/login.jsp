<%--
  Created by IntelliJ IDEA.
  User: yangshixiong
  Date: 2020/1/23
  Time: 下午10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
<h1>请先登陆！</h1>
<form action="${pageContext.request.contextPath}/login" method="post">
    账号：<input type="text" name="username" placeholder="请输入用户名">
    密码：<input type="password" name="password" placeholder="请输入密码">
    <input type="submit" value="登陆">
</form>
</body>
</html>
