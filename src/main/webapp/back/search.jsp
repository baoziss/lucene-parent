<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/24
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
<title>searche</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/search/find" method="post">
    <input name="str" title="关键字" value="关键字" onfocus="if(this.value=='关键字') this.value=''"/>&nbsp;&nbsp;<input type="submit" value="搜索"/>
</form>

</body>
</html>
