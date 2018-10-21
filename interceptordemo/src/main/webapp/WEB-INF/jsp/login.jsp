<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath %>" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录</title>
</head>
<body>
<center>
    <form action="user/dologin.html" method="post">
        <span>用户名：</span><input type="text" name="userCode"/><br>
        <span>密码:</span><input type="password" name="userPassword"/>
        <input type="submit" value="提交"/>
    </form>
</center>
</body>
</html>
