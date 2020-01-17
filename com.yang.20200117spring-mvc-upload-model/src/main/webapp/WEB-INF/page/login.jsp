<%--
  Created by IntelliJ IDEA.
  User: yangshixiong
  Date: 2020/1/17
  Time: 上午11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${message}</title>
</head>
<body>
<h1>${message}</h1>
<form action="/authenticate">
    <label>
        username: <input type="text" name="username">
    </label>
    <label>
        password: <input type="password" name="password">
    </label>
    <input type="submit" value="提交">
</form>
</body>
</html>
