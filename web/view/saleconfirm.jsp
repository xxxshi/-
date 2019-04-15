<%@ page import="java.util.ArrayList" %>
<%@ page import="com.erp.bean.Saleconfirm" %>
<%--
  Created by IntelliJ IDEA.
  User: HUANG
  Date: 2018/12/18
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>销售确认书明细表</title>
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
        <h1>销售书详单</h1>
        <table cellspacing="0" cellpadding="0">
            <tr>
                <th>物料编码</th>
                <th>订单编号</th>
                <th>品牌</th>
                <th>纸种</th>
                <th>级别</th>
                <th>克重</th>
                <th>规格</th>
                <th>单位</th>
                <th>产品类型</th>
                <th>数量</th>
                <th>单价</th>
            </tr>
            <%
                Saleconfirm saleconfirm=new Saleconfirm();
                ArrayList<Saleconfirm> arrayList = (ArrayList) session.getAttribute("id");
                for(int i=0;i<arrayList.size();i++)
                {
                    saleconfirm=arrayList.get(i);
            %>
            <tr>
                <td><%=saleconfirm.getItemid()%></td>
                <td><%=saleconfirm.getOrdermasterid()%></td>
                <td><%=saleconfirm.getBrand()%></td>
                <td><%=saleconfirm.getPapertype()%></td>
                <td><%=saleconfirm.getRank()%></td>
                <td><%=saleconfirm.getGweight()%></td>
                <td><%=saleconfirm.getSpecification()%></td>
                <td><%=saleconfirm.getUnit()%></td>
                <td><%=saleconfirm.getProducttype()%></td>
                <td><%=saleconfirm.getQuantity()%></td>
                <td><%=saleconfirm.getPrice()%></td>
            </tr>
            <%}%>
        </table><br>
    </center>
</body>
</html>

