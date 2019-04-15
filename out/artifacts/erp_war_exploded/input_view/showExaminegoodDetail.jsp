<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/18 0018
  Time: 下午 20:59
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

        function submitExaminegoodDetail(paramters){
            var isGenerate=confirm("此件纸品是否合格?");
            //合格,此件纸品进行进仓
            if(isGenerate){
                $.post("/storageinAndDetailAddServlet",{"paramters":paramters.title},function (data, status, req) {
                    if(status=="success"){
                        var isSuccess=req.responseText;
                        if(isSuccess=="success"){
                            alert("此件纸品进仓完毕");
                        }else{
                            alert("此件纸品已经进仓完毕，无需再次进仓!");
                        }
                    }
                })
            }else{
                //不合格，进退货进仓单
                $.post("/storageinAndDetailOutAddServlet",{"paramters":paramters.title},function (data, status, req) {
                    if(status=="success"){
                        var isSuccess=req.responseText;
                        if(isSuccess=="success"){
                            alert("此件纸品进仓完毕");
                        }else{
                            alert("此件纸品已经进仓完毕，无需再次进仓!");
                        }
                    }
                })

            }
        }
    </script>
</head>
<body>
<h1 align="center">进仓验货详情</h1>
    <table>
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
        <td>提交验货信息</td>
        </tr>
        <c:forEach items="${examinegoodDetails}" var="examinegoodDetail">
            <tr>
                <c:forEach items="${examinegoodDetail}" var="field" varStatus="status">
                    <c:if test="${status.index<7}">
                        <td >${field}</td>
                    </c:if>
                </c:forEach>
                <td><button onclick="submitExaminegoodDetail(this)";
                            title="${examinegoodDetail[7]}">提交验货信息</button></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
