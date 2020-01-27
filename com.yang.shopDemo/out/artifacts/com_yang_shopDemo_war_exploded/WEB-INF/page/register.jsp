<%--
  Created by IntelliJ IDEA.
  User: yangshixiong
  Date: 2020/1/23
  Time: 下午10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Simplify Admin</title>
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
                <i class="fa fa-database m-right-xs"></i> 用户 <strong class="text-skin">注册中心</strong>
            </div>

            <form>
                <div class="form-group m-bottom-md">
                    <input type="text" id="username" class="form-control" placeholder="账号">
                </div>
                <div class="form-group">
                    <input type="password" id="password" class="form-control" placeholder="密码">
                </div>
                <div class="form-group">
                    <input type="password" id="password2" class="form-control" placeholder="确认密码">
                </div>
                <div class="form-group">
                    <div class="custom-checkbox">
                        <input type="checkbox" id="chkAccept">
                        <label for="chkAccept"></label>
                    </div>
                    我同意全部条款
                </div>

                <div class="m-top-md p-top-sm">
                    <div class="btn btn-success block" id="register">创建账号</div>
                </div>

                <div class="m-top-md p-top-sm">
                    <div class="font-12 text-center m-bottom-xs">已有账号?</div>
                    <a href="${pageContext.request.contextPath}/login" class="btn btn-default block">登陆</a>
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
        $('#register').click(function () {
            let username = $('#username').val();
            let password = $('#password').val();
            let password2 = $('#password2').val();
            let chkAccept = $('#chkAccept').val();
            if(username === "" || password === ""){
                alert("用户名或密码不能为空！");
                return false;
            }
            if(password !== password2){
                alert("两次密码不一致！");
                return false;
            }
            if(password.length < 6){
                alert("密码最小长度为六位！");
                return false;
            }
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/register",
                data:{username,password},
                success:function (data) {
                    if(data === "exit"){
                        alert("用户名已存在，请修改！");
                        return false;
                    }else if(data === "fail"){
                        alert("服务器异常，请重试！");
                        return false;
                    }
                    window.assign("${pageContext.request.contextPath}/login")
                },
                error:function (err) {
                    console.log(err);
                    alert("服务器异常，请稍后再试！")
                }
            })


        })
    })
</script>
</body>
</html>
