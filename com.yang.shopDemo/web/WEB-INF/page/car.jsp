<%--
  Created by IntelliJ IDEA.
  User: yangshixiong
  Date: 2020/1/28
  Time: 下午9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${user.username}的购物车</title>
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
                <li><a href="${pageContext.request.contextPath}/car/${user.id}">购物车</a></li>
                <li class="dropdown">
                    <img src="${pageContext.request.contextPath}/${user.avatarUrl}" alt=""
                         style="    max-width: 30px; display: inline-block;">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false" style="display: inline-block; padding-left: 3px;">${user.username}<span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/userinfo/${user.id}">个人信息</a></li>
                        <li><a href="${pageContext.request.contextPath}/order/list/${user.id}">查看订单</a></li>
                        <li id="logout"><a>退出</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div style="max-width: 1440px; margin: 0 auto">
    <table class="table table-striped">
        <caption>${user.username}的购物车</caption>
        <thead>
        <tr>
            <th>照片</th>
            <th>商品</th>
            <th>描述</th>
            <th>价格</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${carList}" var="car">
            <tr>
                <td><img src="${pageContext.request.contextPath}/${productMap[car.id].pictureUrl}" alt=""
                         class="img-circle" style="max-width: 30px;"></td>
                <td>${productMap[car.id].name}</td>
                <td>${productMap[car.id].description}</td>
                <td>${productMap[car.id].price}</td>
                <td>
                    <button href="${pageContext.request.contextPath}/car/delete/${car.id}" type="button"
                            class="btn btn-danger delete">
                        删除
                    </button>
                    <button onclick="buy(${car.id})" type="button" class="btn btn-success">
                        单独结算
                    </button>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <div style="display: flex; justify-content: flex-end; align-items: center; border-top:1px solid #333333">
        <div style="color: red; font-size: 40px">总计：${totalPrice}</div>
        <div style="margin: 0 200px 0 20px;">
            <button type="button" class="btn btn-primary" onclick="buy(0)">结算</button>
        </div>
    </div>
</div>
<!-- Jquery -->
<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.min.js"></script>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script type="application/javascript">
    function buy(carId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/order/add/${user.id}/" + carId,
            success: function (data) {
                if(data !== 0){
                    window.location = "${pageContext.request.contextPath}/order/" + data + "/${user.id}";
                    return false;
                }
                alert("fail");
            },
            error: function (err) {
                console.log(err);
                alert("服务器异常！");
            }
        })
    };
    $(function () {
        let token = sessionStorage.getItem("token") || null;
        // 验证是否存在token，没有则返回登陆
        if (token == null) {
            alert("请先登陆！");
            window.location = "${pageContext.request.contextPath}/login";
            return false;
        }
        document.getElementById("backProduct").href = "${pageContext.request.contextPath}/product?token=" + token;
        $('.delete').click(function (e) {
            let url = e.toElement.getAttribute("href");
            $.ajax({
                type: "get",
                url: url,
                success: function (data) {
                    if (data === "ok") {
                        alert("删除成功！");
                        document.location.reload();
                        return false;
                    }
                    alert("删除失败，请刷新重试！")
                },
                error: function (err) {
                    console.log(err);
                    alert("服务器故障！")
                }
            })
        });
        $("#logout").click(function () {
            sessionStorage.removeItem("token");
            window.location = "${pageContext.request.contextPath}/login"
        });
    })
</script>
</body>
</html>
