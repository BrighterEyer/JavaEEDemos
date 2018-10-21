<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath %>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户注册</title>
</head>
<body>
<center>
    <form action="user/doRegister.html" method="post">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="userCode" value="${requestScope.userCode}"/></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="password" name="userPassword" value="${requestScope.userPassword}"/></td>
            </tr>
            <tr colspan="2">
                <td><input type="submit" value="确定"/></td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
