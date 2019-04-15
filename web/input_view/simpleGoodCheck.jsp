<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String paramters=request.getParameter("paramters");
	request.getSession().setAttribute("paramters",paramters.split(","));
	String pieceid=paramters.split(",")[0];
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>添加客诉报告</title>
</head>
	<style>
		tr,th,td{
			border: 1px solid gray;
			text-align: center;
			width: 180px;
			height: 45px;
		}
		input[type="text"],select,input[type="date"]{
			width: 180px;
			height: 45px;
			text-align: center;
			font-size: 18px;
		}
		a{
			text-decoration: none;
		}
	</style>
	<script type="text/javascript" src="../js/problemAdd.js"></script>
<body>
	<h1 align="center">添加客诉报告详情</h1>
	<div align="center">
		<form id="problemForm" action="/addSimpleGoodCheck" method="post">
		<table style="border: 1px;border-collapse: collapse;">
			<tr>
				<td  style="background: lightgray;">件号</td>
				<td><%=pieceid%></td>
			</tr>
		</table>
		<h2>问题添加</h2>
		<table style="border: 1px;border-collapse: collapse;" id="problemContainer">
			<tr>
			<td style="background: lightgray;">问题</td><td style="background: lightgray;" >输入结果</td><td style="background: lightgray;">问题描述</td>
				<td style="background: lightgray;">删除</td>
			</tr>
			<tr>
				<td><select name="problem"><option value="coreweighe">筒芯重量</option><option value="shortweight">短重</option><option value="gramweight">克重</option><option value="width">幅宽</option><option value="outerpackagingloss">外包装损耗</option><option value="paperdamage">纸张破损</option><option value="Moisture">水分</option></select></td><td><input type="text" name="answer"></td>
				<td><input type="text" name="problemStatement"></td>
				<td><a href="#" onclick="RemoveProblem(this);">删除</a></td>
			</tr>
		</table>
		</form>
		<button style="background: yellow; width: 150px; height: 40px; margin-top:50px;" onclick="submitForm()">添加客诉报告</button>
		<button style="background: yellow; width: 150px; height: 40px; margin-top:50px;" onClick="AddProblem();">添加问题</button>
	</div>
</body>
</html>
