<%--
  Created by IntelliJ IDEA.
  User: yangshixiong
  Date: 2020/1/15
  Time: 下午8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户认证</title>
</head>
<body>
${message}
<form action="/authenticate" method="post">
    用户名：<input type="text" name="userName"><br/>
    密码：<input type="text" name="password"><br/>
    <input type="submit" value="提  交">
</form>
</body>
</html>
