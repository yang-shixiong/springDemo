<html>
<head>
    <title>用户认证</title>
</head>
<body>
<h1>${message}</h1>
<form action="/authenticate" method="post">
    用户名：<input type="text" name="userName"><br/>
    密码：<input type="text" name="password"><br/>
    <input type="submit" value="提  交">
</form>
</body>
</html>
