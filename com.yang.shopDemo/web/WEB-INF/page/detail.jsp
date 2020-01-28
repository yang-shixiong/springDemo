<%--
  Created by IntelliJ IDEA.
  User: yangshixiong
  Date: 2020/1/28
  Time: 下午5:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${product.name}详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="yang">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .product-detail {
            display: flex;
            height: 350px;
        }

        .detail-img {
            width: 350px;
            height: auto;
            align-items: center;
            margin-right: 30px;
        }

        .detail-img img {
            width: 350px;
            height: auto;
        }

        .detail-right {

        }

        .detail-name {
            font: 700 16px Arial, "microsoft yahei";
            color: #666;
            padding-top: 10px;
            line-height: 40px;
            margin-bottom: 5px;
        }

        .detail-price {
            color: #E4393C;
            margin-right: 10px;
            height: 50px;
        }

        .detail-description {
            height: 180px;
        }

        .detail-btn {
            display: flex;
            justify-content: space-between;
            width: 400px;
        }

        .detail-btn div {
            height: 46px;
            line-height: 46px;
            padding: 0 26px;
            font-size: 18px;
            font-weight: 700;
            background-color: #df3033;
            color: #fff;
            cursor: pointer;
        }

        .detail-btn div:hover {
            text-decoration: none;
            background-color: orange;
        }
    </style>
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
                        <li><a href="${pageContext.request.contextPath}/order/${user.id}">查看订单</a></li>
                        <li id="logout"><a>退出</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div style="max-width: 1440px; margin: 0 auto">
    <div style="height: 20px; line-height: 20px"> < 返回商品列表</div>
    <div class="product-detail">
        <div class="detail-img">
            <img src="${pageContext.request.contextPath}/${product.pictureUrl}" alt="${product.name}照片">
        </div>
        <div class="detail-right">
            <div class="detail-name">${product.name}</div>
            <p class="detail-price">价格：${product.price}</p>
            <div class="detail-description">${product.description}</div>
            <div class="detail-btn">
                <div id="car">加入购物车</div>
                <div id="buy">立即购买</div>
            </div>
        </div>
    </div>
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
        $("#car").click(function () {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/car/add",
                data: {"userId":${user.id}, "productId":${product.id}, "price":${product.price}},
                success: function (res) {
                    if (res === "exist") {
                        alert("购物车已存在，请先结算！");
                        return false;
                    }
                    if (res === "fail") {
                        alert("添加购物车失败，请重试！");
                        return false;
                    }
                    alert("添加成功！")
                },
                error: function (err) {
                    console.log(err);
                    alert("服务器异常，添加失败！")
                }
            })
        });
        $("#buy").click(function () {

        });
        $("#logout").click(function () {
            sessionStorage.removeItem("token");
            window.location = "${pageContext.request.contextPath}/login"
        })
    })
</script>
</body>
</html>
