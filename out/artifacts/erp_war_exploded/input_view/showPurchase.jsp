<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/18 0018
  Time: 上午 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../css/tablecloth.css" rel="stylesheet" type="text/css">
    <title>采购订单总览</title>
    <script src="../js/jquery.js"></script>
    <script type="text/javascript">

        function addExaminegood(purchase){
            var isGenerate=confirm("是否生成进仓验货单?");
            if(isGenerate){
                $.post("/examinegoodServlet",{"purchaseid":purchase.title},function (data, status, req) {
                    if(status=="success"){
                        var isSuccess=req.responseText;
                        if(isSuccess=="success"){
                            alert("进仓验货单生成完毕");
                        }else{
                            alert("此进仓验货单已经生成，无需再次生成!");
                        }
                    }
                })
            }
        }


    </script>
</head>
<body>
<h1 align="center">采购订单总览</h1>
<p><a href=""></a></p>
<div align="center">
    <table>
        <tr><th>采购单号</th><th>订单日期</th><th>采购计划合同号</th><th>订单总价</th><th>订单总重</th><th>物料总数量</th><th>选择进仓</th></tr>
        <c:forEach items="${purchases}" var="purchase">
            <tr>
                <c:forEach items="${purchase}" var="field" varStatus="status">
                    <c:if test="${status.index==0}">
                        <td ><a href="/showPurchaseDetail?contractCode=${purchase[2]}" title="展示订单详情">${field}</a></td>
                    </c:if>
                    <c:if test="${status.index>0}">
                        <td >${field}</td>
                    </c:if>
                </c:forEach>
                <td><button onclick="addExaminegood(this)"; title="${purchase[0]}">生成进仓验货单</button></td>

                <%--<td ><a href="/examinegoodServlet?purchaseid=${purchase[0]}" title="进仓" target="_self">${purchase[0]}</a></td>--%>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
