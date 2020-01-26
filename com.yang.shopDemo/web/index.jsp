<%--
  Created by IntelliJ IDEA.
  User: yangshixiong
  Date: 2020/1/19
  Time: 上午9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
  <h1>欢迎光临!</h1>
  <a href="${pageContext.request.contextPath}/login">已有账号？请先登录！</a>
  <a href="${pageContext.request.contextPath}/register">还没有账号，立即注册</a>
  ${pageContext.request.contextPath}
  </body>
</html>
