<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/tablecloth.css" type="text/css">
<script language="JavaScript" type="text/javascript">
	function returnPurchase() {
		window.open("/showPurchase","_self");
    }
</script>
<title>采购订单详细</title>
</head>
<body>
<p>
	<span>买方:</span>${username}&nbsp;&nbsp;
	<span>卖方:</span>${supply}&nbsp;&nbsp;
	<span>采购单号:</span>${purchaseCode}&nbsp;&nbsp;
	<span>订单日期:</span>${orderDate}
</p>
	 <table >
		 <tr><th>品牌</th><th>纸种</th><th>级别</th><th>克重</th><th>规格</th><th>单位</th>
			 <th>数量</th><th>件数</th><th>单价</th><th>总价</th></tr>
 <c:forEach items="${details }" var="detail">   <!--获取查询结果集result 类型：List   在此存放销售单集合-->
          <tr>
			  <c:forEach items="${detail}" var="field">
				  <td>${field}</td>
			  </c:forEach>
          </tr>
 </c:forEach>
</table><br>
<button onclick="returnPurchase()">返回采购订单主页面</button>
</body>
</html>