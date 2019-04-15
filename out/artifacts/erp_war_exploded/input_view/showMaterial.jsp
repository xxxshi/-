<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/tablecloth.css" rel="stylesheet" type="text/css">
<title>采购计划书总览</title>
<script type="text/javascript">
    function addMaterial(){
        window.open("/input_view/material.html","_self");
    }
</script>
</head>
<body>
<h1 align="center">物料信息总览</h1>
<p><a href=""></a></p>
<div align="center">
	 <table>
         <tr><td>物料编码</td><td>品牌</td><td>纸种</td><td>级别</td><td>克重</td><td>规格</td><td>单位</td><td>产品类型</td><td>成品对应的原纸编码</td></tr>
         <c:forEach items="${materials}" var="material">
             <tr>
                 <c:forEach items="${material}" var="field" varStatus="status">
                     <c:if test="${status.index==0}">
                         <td >${field}</td>
                     </c:if>
                     <c:if test="${status.index>0}">
                         <td >${field}</td>
                     </c:if>
                 </c:forEach>
             </tr>
         </c:forEach>
    </table>
    <br>
	<button style="background:yellow;width: 150px;height: 50px;cursor: pointer;" onclick="addMaterial()">添加物料信息</button>
</div>

</body>
</html>