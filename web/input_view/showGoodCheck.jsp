<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/19
  Time: 1:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>客诉报告信息</title>
    <link href="../css/tablecloth.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1 align="center">客诉报告总览</h1>
    <table>
        <tr><th>客诉报告编号</th><th>进仓单号</th><th>采购单号</th><th>供应商</th></tr>
        <c:forEach items="${goodchecks}" var="goodcheck">
            <tr>
                <c:forEach items="${goodcheck}" var="field" varStatus="status">
                    <c:if test="${status.index==0}">
                        <td><a title="点击查看客诉报告详情"
                               href="/showGoodCheckDetail?goodcheckid=${field}&storageinid=${goodcheck[1]}&supply=${goodcheck[3]}">${field}</a></td>
                    </c:if>
                    <c:if test="${status.index>0}">
                        <td>${field}</td>
                    </c:if>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
