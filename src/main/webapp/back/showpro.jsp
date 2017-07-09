<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/24
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product</title>
</head>
<body>

    <c:forEach items="${requestScope.products}" var="pro">
        <table>
            <tr>
                <td rowspan="4"><img src="${pageContext.request.contextPath}/${pro.path}" width="200px" height="120px"></td>
            </tr>
            <tr>
                <td>${pro.name}&nbsp;&nbsp;(相似度:${pro.score})</td>
            </tr>
            <tr>
                <td>${pro.price}</td>
            </tr>
            <tr>
                <td>${pro.descript}&nbsp;&nbsp;<a href="">查看详情</a></td>
            </tr>
        </table>
        <hr/>
    </c:forEach>
</body>
</html>
