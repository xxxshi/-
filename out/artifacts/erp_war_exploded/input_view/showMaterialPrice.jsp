<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/18
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>物料价格总览</title>
    <link href="../css/tablecloth.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" language="JavaScript">
        function AddMaterialPrice(){
            window.open("/input_view/value.html","_self");
        }
    </script>
</head>
<body>
<h1 align="center">物料价格总览</h1>
<table>
    <tr><th>物料编码</th><th>采购价格</th><th>销售价格</th></tr>
    <c:forEach items="${materialPrices}" var="materialPrice">
        <tr>
            <c:forEach items="${materialPrice}" var="field">
                <td>${field}</td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
<button onclick="AddMaterialPrice()">添加价格信息</button>
</body>
</html>
