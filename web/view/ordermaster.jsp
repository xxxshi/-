
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.erp.bean.Ordermaster" %><%--
  Created by IntelliJ IDEA.
  User: HUANG
  Date: 2018/12/18
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="/view/checkRepetory.js"></script>
    <script type="text/javascript">
        function addPurchasePlan(){
            window.open("/view/addOrder.jsp","_self");
        }
    </script>

    <title>订单总表</title>
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
    <h1>订单总表</h1>
    <a href="/createSaleConfirmServlet"><input type="button" value="生成销售确认书"></a>
    <table cellspacing="0" cellpadding="0">
        <tr>
            <th>订单编号</th>
            <th>订单日期</th>
            <th>交货日期</th>
            <th>客户名</th>
            <th>是否处理</th>
        </tr>
        <%
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Ordermaster ordermaster=null;
            ArrayList<Ordermaster> arrayList=new ArrayList<Ordermaster>();
            ArrayList<ArrayList<Object>> sumList= (ArrayList<ArrayList<Object>>) request.getAttribute("ordermaster");
            for (int i = 0; i <sumList.size() ; i++) {
                int j=0;
                ordermaster=new Ordermaster();
                ordermaster.setOrdermasterid(sumList.get(i).get(j++).toString());
                ordermaster.setOrdertime((Date) sumList.get(i).get(j++));
                ordermaster.setSuretime((Date) sumList.get(i).get(j++));
                ordermaster.setCustomer(sumList.get(i).get(j++).toString());
                ordermaster.setState(Integer.valueOf(sumList.get(i).get(j++).toString()));
                arrayList.add(ordermaster);
            }
            for(int i=0;i<arrayList.size();i++)
            {
                ordermaster=arrayList.get(i);
        %>
        <tr>
            <td><a href="/ToViewOrderDetail?masterId=<%=ordermaster.getOrdermasterid()%>" title=""><%=ordermaster.getOrdermasterid()%></a> </td>
            <td><%=ordermaster.getOrdertime()%></td>
            <td><%=ordermaster.getSuretime()%></td>
            <td><%=ordermaster.getCustomer()%></td>
            <td><%=ordermaster.getState()%></td>
        </tr>
        <%}%>
    </table><br>
    <button style="background:yellow;width: 150px;height: 50px;cursor: pointer;" onclick="addPurchasePlan()">添加订单表信息</button>
    <br>
    <button style="background:yellow;width: 150px;height: 50px;cursor: pointer;" onclick="checkRepetoryNum();">检查库存
    </button>
</center>
</body>
</html>