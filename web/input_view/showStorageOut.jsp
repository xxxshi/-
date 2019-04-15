<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/19 0019
  Time: 上午 8:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../css/tablecloth.css" rel="stylesheet" type="text/css">
    <title>退仓总览</title>
    <script src="../js/jquery.js"></script>
    <script type="text/javascript">

        function showStorageinDetail(storagein){
            window.open("/showStorageOutDetail?storageinid="+storagein.title,"_self")

        }


    </script>
</head>
<body>
<h1 align="center">退货进仓总览</h1>
<p><a href=""></a></p>
<div align="center">
    <table>
        <tr>
            <td>进仓单号</td><td>供应商</td><td>收货公司名</td><td>采购单号</td><td>进仓日期</td>
            <td>查看进仓详细信息</td>
        </tr>
        <c:forEach items="${storageins}" var="storagein">
            <tr>
                <c:forEach items="${storagein}" var="field" varStatus="status">
                    <c:if test="${status.index>=0}">
                        <td >${field}</td>
                    </c:if>
                </c:forEach>
                <td><button onclick="showStorageinDetail(this)"; title="${storagein[0]}">展示详细信息</button></td>

            </tr>
        </c:forEach>
    </table>
    <br>
</div>

</body>
</html>

