<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/18 0018
  Time: 下午 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../css/tablecloth.css" rel="stylesheet" type="text/css">
    <title>进仓验货总览</title>
    <script src="../js/jquery.js"></script>
    <script type="text/javascript">

        function addExaminegoodDetail(storagein){
            var isGenerate=confirm("是否生成进仓验货详情单?");
            if(isGenerate){
                $.post("/examinegoodDetailAddServlet",{"storageinid":storagein.title},function (data, status, req) {
                    if(status=="success"){
                        var isSuccess=req.responseText;
                        if(isSuccess=="success"){
                            alert("进仓验货详单生成完毕");
                        }else{
                            alert("此进仓验货详情已经生成，无需再次生成!");
                        }
                    }
                })
            }
        }

        function showExaminegoodDetail(storagein){
            window.open("/showExaminegoodDetail?storageinid="+storagein.title,"_self")

        }

        function submitGoodCheck(storagein){
            window.open("/showExaminegoodDetailFailure?storageinid="+storagein.title,"_self")

        }

    </script>
</head>
<body>
<h1 align="center">进仓验货总览</h1>
<p><a href=""></a></p>
<div align="center">
    <table>
        <tr>
            <th>进仓单号</th><th>供应商</th><th>进仓日期</th><th>采购单号</th><th>签收时间</th><th>仓管员姓名</th><th>财务</th><th>物流控制员
            </th><th>生成进仓验货详单</th><th>展示详单信息</th><th>点击填写</th>
        </tr>
        <c:forEach items="${examinegoods}" var="examinegood">
            <tr>
                <c:forEach items="${examinegood}" var="field" varStatus="status">
                    <c:if test="${status.index==0}">
                        <td >${field}</td>
                    </c:if>
                    <c:if test="${status.index>0}">
                        <td >${field}</td>
                    </c:if>
                </c:forEach>
                <td><button onclick="addExaminegoodDetail(this)"; title="${examinegood[0]}">生成详单</button></td>
                <td><button onclick="showExaminegoodDetail(this)"; title="${examinegood[0]}">展示详单</button></td>
                <td><button onclick="submitGoodCheck(this)"; title="${examinegood[0]}">填写客诉报告</button></td>

            </tr>
        </c:forEach>
    </table>
    <br>
</div>

</body>
</html>
