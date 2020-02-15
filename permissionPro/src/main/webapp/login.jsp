<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户权限管理系统</title>
    <link href="${pageContext.request.contextPath}/static/css/base.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/easyui/jquery.min.js"></script>
</head>
<body class="white">
<div class="login-hd">
    <div class="left-bg"></div>
    <div class="right-bg"></div>
    <div class="hd-inner">
        <span class="logo"></span>
        <span class="split"></span>
        <span class="sys-name">用户权限管理系统</span>
    </div>
</div>
<div class="login-bd">
    <div class="bd-inner">
        <div class="inner-wrap">
            <div class="lg-zone">
                <div class="lg-box">
                    <div class="lg-label"><h4>用户登录</h4></div>

                    <form>
                        <div class="lg-username input-item clearfix">
                            <i class="iconfont"></i>
                            <input type="text" name="username" placeholder="请用户名">
                        </div>
                        <div class="lg-password input-item clearfix">
                            <i class="iconfont"></i>
                            <input type="password" name="password"  placeholder="请输入密码">
                        </div>

                        <div class="enter">
                            <a href="javascript:;" class="purchaser" id="loginBtn">点击登录</a>
                        </div>

                    </form>
                    <div class="line line-y"></div>
                    <div class="line line-g"></div>
                </div>
            </div>
            <div class="lg-poster"></div>
        </div>
    </div>
</div>
<div class="login-ft">
    <div class="ft-inner">
        <div class="about-us">
            <a href="javascript:;">关于我们</a>
            <a href="https://www.cnblogs.com/yangshixiong/">我的博客</a>
            <a href="javascript:;">服务条款</a>
            <a href="javascript:;">联系方式</a>
        </div>
        <div class="address"> 版权均归 杨世雄 所有 &nbsp&nbsp;&nbsp;Copyright&nbsp;©&nbsp;2020&nbsp;-&nbsp;2021&nbsp;杨世雄&nbsp;版权所有</div>
        <div class="other-info">建议使用IE8及以上版本浏览器;E-mail：yangshixiongh@126.com</div>
    </div>
</div>


<script type="text/javascript">
    $(function () {
        // 表单发送请求
        $('#loginBtn').click(function () {
            // 发送ajax请求做登陆认证，使用form的序列化表格
            $.post("/login", $('form').serialize(),function (data) {
                // post请求需要序列化响应
                data = $.parseJSON(data);
                if(data.success){
                    // 跳转到首页
                    window.location.href = "/index.jsp"
                }else{
                    $.messager("温馨提示", data.msg)
                }
            })
        })
    })
</script>
</body>
</html>
