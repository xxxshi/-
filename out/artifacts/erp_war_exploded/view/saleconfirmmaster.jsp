
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.erp.bean.Saleconfirmmaster" %>
<%@ page import="com.erp.util.DBUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: HUANG
  Date: 2018/12/17
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售确认书主表</title>
    <link href="tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
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
        <h1>销售确认书</h1>
        <table cellspacing="0" cellpadding="0">
            <tr>
                <th>订单编号</th>
                <th>订单日期</th>
                <th>确认日期</th>
                <th>买方</th>
                <th>卖方</th>
                <th>分切费</th>
                <th>总价</th>
                <th>确认与否</th>
                <th></th>
            </tr>
            <%
                Saleconfirmmaster saleconfirmmaster=null;
                ArrayList<Saleconfirmmaster> arrayList=new ArrayList<Saleconfirmmaster>();
                ArrayList<ArrayList<Object>> sumList= DBUtil.findByParamter("select * from saleconfirmmaster",null);
                for (int i = 0; i <sumList.size() ; i++) {
                    int j=0;
                    saleconfirmmaster=new Saleconfirmmaster();
                    saleconfirmmaster.setOrdermasterid(sumList.get(i).get(j++).toString());
                    saleconfirmmaster.setSaledate((Date) sumList.get(i).get(j++));
                    saleconfirmmaster.setSuredate((Date) sumList.get(i).get(j++));
                    saleconfirmmaster.setBuy(sumList.get(i).get(j++).toString());
                    saleconfirmmaster.setSale(sumList.get(i).get(j++).toString());
                    saleconfirmmaster.setPriceCut((BigDecimal)sumList.get(i).get(j++));
                    saleconfirmmaster.setTotal((BigDecimal)sumList.get(i).get(j++));
                    saleconfirmmaster.setState(Integer.parseInt(sumList.get(i).get(j++).toString()));
                    saleconfirmmaster.setState_ok(Integer.parseInt(sumList.get(i).get(j++).toString()));
                    arrayList.add(saleconfirmmaster);
                }
                for(int i=0;i<arrayList.size();i++)
                {
                    saleconfirmmaster=arrayList.get(i);
            %>
            <tr>
                <td><a href="/ToViewSaleconfirm?masterId=<%=saleconfirmmaster.getOrdermasterid()%>" title=""><%=saleconfirmmaster.getOrdermasterid()%></a> </td>
                <td><%=saleconfirmmaster.getSaledate()%></td>
                <td><%=saleconfirmmaster.getSuredate()%></td>
                <td><%=saleconfirmmaster.getBuy()%></td>
                <td><%=saleconfirmmaster.getSale()%></td>
                <td><%=saleconfirmmaster.getPriceCut()%></td>
                <td><%=saleconfirmmaster.getTotal()%></td>
                <td><%=saleconfirmmaster.getState()%></td>
                <td><a href="/saleConfirm?oid=<%=saleconfirmmaster.getOrdermasterid()%>"> <input type="button" value="确认"></a></td>
            </tr>
            <%}%>
        </table><br>
    </center>
</body>
</html>