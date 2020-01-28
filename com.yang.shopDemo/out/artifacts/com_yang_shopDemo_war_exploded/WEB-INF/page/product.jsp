<%--
  Created by IntelliJ IDEA.
  User: yangshixiong
  Date: 2020/1/28
  Time: 上午9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>网上商城</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="yang">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">杨的购物广场</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/">回欢迎页</a></li>
                <li><a href="#" id="backProduct">返回商品列表</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">购物车</a></li>
                <li class="dropdown">
                    <img src="${pageContext.request.contextPath}/${user.avatarUrl}" alt=""
                         style="    max-width: 30px; display: inline-block;">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false" style="display: inline-block; padding-left: 3px;">${user.username}<span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/userinfo/${user.id}">个人信息</a></li>
                        <li><a href="${pageContext.request.contextPath}/order/${user.id}">查看订单</a></li>
                        <li id="logout"><a>退出</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="row" style="max-width: 1440px; margin: 0 auto">
    <c:forEach items="${productList}" var="product">

        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="${pageContext.request.contextPath}/${product.pictureUrl}" alt="商品照片">
                <div class="caption">
                    <a href="${pageContext.request.contextPath}/detail/${product.id}/${user.id}">
                        <h3>${product.name}</h3>
                        <h4 style="color: orange">价格：${product.price}</h4>
                        <p>${product.description}</p>
                    </a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<!-- Jquery -->
<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.min.js"></script>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script type="application/javascript">
    $(function () {
        let token = sessionStorage.getItem("token") || null;
        // 验证是否存在token，没有则返回登陆
        if (token == null) {
            alert("请先登陆！");
            window.location = "${pageContext.request.contextPath}/login";
            return false;
        }
        document.getElementById("backProduct").href = "${pageContext.request.contextPath}/product?token=" + token;

        $("#logout").click(function () {
            sessionStorage.removeItem("token");
            window.location = "${pageContext.request.contextPath}/login"
        })
    })
</script>
</body>
</html>
