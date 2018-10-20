<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ page contentType="text/html;charset=GB2312" %>
<html>
<head>
    <title>第一个 JSF 程序</title>
</head>
<body>
    <f:view>
         <h:form>
              <h3>请输入您的名称</h3>
              <h:outputText value="#{user.errMessage}"/><p>
        名称: <h:inputText value="#{user.name}"/><p>
        密码: <h:inputSecret value="#{user.password}"/><p>
              <h:commandButton value="送出" action="#{user.verify}"/>
        </h:form>
    </f:view>
</body>
</html>
