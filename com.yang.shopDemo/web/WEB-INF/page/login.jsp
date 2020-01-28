<%--
  Created by IntelliJ IDEA.
  User: yangshixiong
  Date: 2020/1/27
  Time: 下午09:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>用户登陆中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet">

    <!-- ionicons -->
    <link href="${pageContext.request.contextPath}/static/css/ionicons.min.css" rel="stylesheet">

    <!-- Simplify -->
    <link href="${pageContext.request.contextPath}/static/css/simplify.min.css" rel="stylesheet">

</head>

<body class="overflow-hidden light-background">
<div class="wrapper no-navigation preload">
    <div class="sign-in-wrapper">
        <div class="sign-in-inner">
            <div class="login-brand text-center">
                <i class="fa fa-database m-right-xs"></i> 用户 <strong class="text-skin">登陆</strong>
            </div>

            <form>
                <div class="form-group m-bottom-md">
                    <input type="text" id="username" class="form-control" placeholder="用户名">
                </div>
                <div class="form-group">
                    <input type="password" id="password" class="form-control" placeholder="密码">
                </div>

                <div class="m-top-md p-top-sm">
                    <div href="#" id="login" class="btn btn-success block">登陆</div>
                </div>

                <div class="m-top-md p-top-sm">
                    <div class="font-12 text-center m-bottom-xs">没有账号?</div>
                    <a href="${pageContext.request.contextPath}/register" class="btn btn-default block">创建账号</a>
                </div>
            </form>
        </div><!-- ./sign-in-inner -->
    </div><!-- ./sign-in-wrapper -->
</div><!-- /wrapper -->

<a href="" id="scroll-to-top" class="hidden-print"><i class="icon-chevron-up"></i></a>

<!-- Jquery -->
<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.min.js"></script>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

<!-- Slimscroll -->
<script src='${pageContext.request.contextPath}/static/js/jquery.slimscroll.min.js'></script>

<!-- Popup Overlay -->
<script src='${pageContext.request.contextPath}/static/js/jquery.popupoverlay.min.js'></script>

<!-- Modernizr -->
<script src='${pageContext.request.contextPath}/static/js/modernizr.min.js'></script>

<!-- Simplify -->
<script src="${pageContext.request.contextPath}/static/js/simplify/simplify.js"></script>
<script type="application/javascript">
    $(function () {
        $('#login').click(function () {
            let username = $('#username').val();
            let password = $('#password').val();
            console.log(username, password)
            if (username === "" || password === "") {
                alert("用户名或密码不能为空！");
                return false;
            }
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/login",
                data: {username, password},
                success: function (data) {
                    if (data === "fail") {
                        alert("用户名或密码有误，请修改！");
                        return false;
                    }
                    sessionStorage.setItem("token", data);
                    window.location = "${pageContext.request.contextPath}/product?token=" + data;

                },
                error: function (err) {
                    console.log(err);
                    alert("服务器异常，请稍后再试！")
                }
            })


        })
    })
</script>
</body>
</html>
