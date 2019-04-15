<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/tablecloth.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript">
	function returnPlan(){
	    window.open("/showpurchasePlan","_self");
	}
</script>
<title>采购计划详情</title>
</head>
<body>
<p>
	<span>供应商:</span>${supply}&nbsp;&nbsp;
	<span>框架合同号:</span>${contractCode}&nbsp;&nbsp;
	<span>合同日期:</span>${contractDate}<br>
</p>
<table >
<tr><th>品牌</th><th>纸种</th><th>级别</th><th>克重</th><th>规格</th><th>出厂价</th><th>运费</th><th>总价</th><th>售价</th></tr>
 <c:forEach items="${details }" var="detail">   <!--获取查询结果集result 类型：List   在此存放销售单集合-->
	 <tr>
		 <c:forEach items="${detail}" var="field">
			 <td>${field}</td>
		 </c:forEach>
	 </tr>
 </c:forEach>
</table><br>
		<button onclick="returnPlan()">返回计划页</button>
</body>
</html>