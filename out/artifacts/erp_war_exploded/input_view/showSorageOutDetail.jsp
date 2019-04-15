<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/19 0019
  Time: 下午 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>展示进仓验货详情</title>
    <link href="../css/tablecloth.css" rel="stylesheet" type="text/css">
    <script src="../js/jquery.js"></script>

</head>
<body>
<table>
    <h1 align="center">退货进仓详情</h1>
    <tbody>
    <tr>
        <td colspan="2">供应商简称:<%=request.getAttribute("supply")%></td>
    </tr>
    <tr>
        <td>进仓单号:<%=request.getAttribute("storageinid")%></td>
        <td>采购单号:<%=request.getAttribute("purchaseid")%></td>
    </tr>
    <tr>
        <td colspan="2">进仓日期:<%=request.getAttribute("inDate")%></td>
    </tr>
    </tbody>
</table>
<table>
    <tr>
        <th>品牌</th><th>纸种</th><th>级别</th><th>克重</th><th>规格</th><th>单位</th><th>件数</th><th>库位</th><th>备注</th>
    </tr>
    <c:forEach items="${examinegoodDetails}" var="examinegoodDetail">
        <tr>
            <c:forEach items="${examinegoodDetail}" var="field" varStatus="status">
                <td >${field}</td>
            </c:forEach>
            <td>A</td>
            <td></td>
            <%--<td><button onclick="submitGoodCheck(this)"; title="${examinegoodDetail[7]}">填写客诉报告</button></td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
