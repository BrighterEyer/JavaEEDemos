<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="base.jsp"%>
<%@ page import="com.code.web.utils.Constants" %>
<%@ page import="com.code.web.pojo.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath %>" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
</head>
<body>
<center>
    <form action="user/dologin.html" method="post">
    </form>
    <%
        User u = (User)session.getAttribute(Constants.USER_SESSION);
    %>
    <%=u.getUserPassword()%>
</center>
</body>
</html>
