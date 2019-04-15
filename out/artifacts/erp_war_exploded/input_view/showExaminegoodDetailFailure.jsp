<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/19 0019
  Time: 上午 2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>展示进仓验货详情</title>
    <link href="../css/tablecloth.css" rel="stylesheet" type="text/css">
    <script src="../js/jquery.js"></script>
    <script type="text/javascript">

        function submitGoodCheck(paramters){
            //执行填写客诉报告的操作
            window.open("/input_view/simpleGoodCheck.jsp?paramters="+paramters.title,"_self");
        }
    </script>
</head>
<body>
<table>
    <h1 align="center">填写客诉报告</h1>
    <tbody>
    <tr>
        <td colspan="2">供应商简称:<%=request.getAttribute("supply")%></td>
    </tr>
    <tr>
        <td>进仓单号:<%=request.getAttribute("storageinid")%></td>
        <td>进仓日期:<%=request.getAttribute("inDate")%></td>
    </tr>
    <tr>
        <td colspan="2">采购单号:<%=request.getAttribute("purchaseid")%></td>
    </tr>
    </tbody>
</table>
<table>
    <tr>
        <td>品牌</td><td>纸种</td><td>级别</td><td>克重</td><td>规格</td><td>单位</td><td>件号</td>
        <td>填写客诉报告</td>
    </tr>
    <c:forEach items="${examinegoodDetails}" var="examinegoodDetail">
        <tr>
            <c:forEach items="${examinegoodDetail}" var="field" varStatus="status">
                <c:if test="${status.index<7}">
                    <td >${field}</td>
                </c:if>
            </c:forEach>
            <td><button onclick="submitGoodCheck(this)";
                        title="${examinegoodDetail[7]}">填写客诉报告</button></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

