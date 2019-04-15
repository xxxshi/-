
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.erp.bean.DirectOrderDetail" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>订单详情表</title>
    <link href="/view/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
    <style>
        body{
            margin:0;
            padding:0;
            background:#f1f1f1;
            font:70% Arial, Helvetica, sans-serif;
            color:#555;
            line-height:150%;
            text-align:left;
        }
        a{
            text-decoration:none;
            color:#057fac;
        }
        a:hover{
            text-decoration:none;
            color:#999;
        }
        h1{
            font-size:140%;
            margin:0 20px;
            line-height:80px;
        }
        h2{
            font-size:120%;
        }
        #container{
            margin:0 auto;
            width:680px;
            background:#fff;
            padding-bottom:20px;
        }
        #content{margin:0 20px;}
        p.sig{
            margin:0 auto;
            width:680px;
            padding:1em 0;
        }
        form{
            margin:1em 0;
            padding:.2em 20px;
            background:#eee;
        }
    </style>
</head>
<body>
<center>
    <h1>订单详情表</h1>
    <table align="center">
        <tr>
            <td colspan="2">订单编号:<%=session.getAttribute("ordermasterid")%></td>
        </tr>
        <tr>
            <td>订单日期:<%=session.getAttribute("ordertime")%></td>
            <td>交货日期:<%=session.getAttribute("suretime")%></td>
        </tr>
        <tr>
            <td>客户名:<%=session.getAttribute("customer")%></td>
            <td>状态:<%=session.getAttribute("state")%></td>
        </tr>
    </table>
    <table cellspacing="0" cellpadding="0">
        <tr>
            <th>物料编号</th>
            <th>品牌</th>
            <th>纸种</th>
            <th>级别</th>
            <th>克重</th>
            <th>规格</th>
            <th>单位</th>
            <th>产品类型</th>
            <th>数量</th>
        </tr>
        <%
            DirectOrderDetail orderdetail=new DirectOrderDetail();
            ArrayList<DirectOrderDetail> arrayList = (ArrayList)session.getAttribute("id");
            for(int i=0;i<arrayList.size();i++)
            {
                orderdetail=arrayList.get(i);
        %>
        <tr>
            <td><%=orderdetail.getItemid()%></td>
            <td><%=orderdetail.getBrand()%></td>
            <td><%=orderdetail.getPapertype()%></td>
            <td><%=orderdetail.getRank()%></td>
            <td><%=orderdetail.getGweight()%></td>
            <td><%=orderdetail.getSpecification()%></td>
            <td><%=orderdetail.getUnit()%></td>
            <td><%=orderdetail.getPapertype()%></td>
            <td><%=orderdetail.getQuantity()%></td>
        </tr>
        <%}%>
    </table><br>
</center>
</body>
</html>