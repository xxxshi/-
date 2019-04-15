<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/tablecloth.css" rel="stylesheet" type="text/css">
<title>采购计划书总览</title>
<script src="../js/jquery.js"></script>
<script type="text/javascript">
    function addPurchasePlan(){
        window.open("/input_view/purchaseplanAdd.html","_self");
    }
    function generatePurchase(contract){
        var isGenerate=confirm("是否生成采购订单?");
        if(isGenerate){
            $.post("/purchaseGenerate",{"contractCode":contract.title},function (data, status, req) {
                if(status=="success"){
                    var isSuccess=req.responseText;
                    if(isSuccess=="success"){
                        alert("采购订单生成完毕");
                    }else{
                        alert("此订单已经生成，无需再次生成!");
                    }
                }
            })
        }
    }
</script>
</head>
<body>
<h1 align="center">采购计划书总览</h1>
<p><a href=""></a></p>
	 <table>
         <tr><th>框架合同号</th><th>合同日期</th><th>供应商</th><th>拟定合同总价</th><th>生成采购计划</th></tr>
         <c:forEach items="${purchaseplans}" var="purchaseplan">
             <tr>
                 <c:forEach items="${purchaseplan}" var="field" varStatus="status">
                     <c:if test="${status.index==0}">
                         <td ><a href="/showPurchasePlanDetail?contractCode=${field}" title="点击查看详单" target="_self">${field}</a></td>
                     </c:if>
                     <c:if test="${status.index>0}">
                         <td >${field}</td>
                     </c:if>
                 </c:forEach>
                 <td><button onclick="generatePurchase(this);" title="${purchaseplan[0]}">生成采购订单</button></td>
             </tr>
         </c:forEach>
    </table>
    <br>
	<button onclick="addPurchasePlan()">添加采购计划</button>
</body>
</html>