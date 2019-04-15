<%--
  Created by IntelliJ IDEA.
  User: HUANG
  Date: 2018/12/18
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.erp.bean.Specialprice" %><%--
  Created by IntelliJ IDEA.
  User: HUANG
  Date: 2018/12/18
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        function addPurchasePlan(){
            window.open("/view/addspecialprice.jsp","_self");
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
    <table cellspacing="0" cellpadding="0">
        <tr>
            <th>客户名</th>
            <th>特价表号</th>
            <th>物料编码</th>
            <th>比准日期</th>
            <th>品牌</th>
            <th>纸种</th>
            <th>级别</th>
            <th>克重</th>
            <th>规格</th>
            <th>单位</th>
            <th>产品类型</th>
            <th>单价</th>
        </tr>
        <%
            Specialprice specialprice=null;
            ArrayList<Specialprice> arrayList=new ArrayList<Specialprice>();
            ArrayList<ArrayList<Object>> sumList= (ArrayList<ArrayList<Object>>) request.getAttribute("specialid");
            for (int i = 0; i <sumList.size() ; i++) {
                int j=0;
                specialprice=new Specialprice();
                specialprice.setCustomer(sumList.get(i).get(j++).toString());
                specialprice.setSpecialid(sumList.get(i).get(j++).toString());
                specialprice.setItemid(sumList.get(i).get(j++).toString());
                specialprice.setAffirmdate((Date)sumList.get(i).get(j++));
                specialprice.setBrand(sumList.get(i).get(j++).toString());
                specialprice.setPapertype(sumList.get(i).get(j++).toString());
                specialprice.setRank(sumList.get(i).get(j++).toString());
                specialprice.setGweight((BigDecimal) sumList.get(i).get(j++));
                specialprice.setSpecification(sumList.get(i).get(j++).toString());
                specialprice.setUnit(sumList.get(i).get(j++).toString());
                specialprice.setProducttype(sumList.get(i).get(j++).toString());
                specialprice.setPrice((BigDecimal) sumList.get(i).get(j++));
                arrayList.add(specialprice);
            }
            for(int i=0;i<arrayList.size();i++)
            {
                specialprice=arrayList.get(i);
        %>
        <tr>
            <td><%=specialprice.getCustomer()%></td>
            <td><%=specialprice.getSpecialid()%></td>
            <td><%=specialprice.getItemid()%></td>
            <td><%=specialprice.getAffirmdate()%></td>
            <td><%=specialprice.getBrand()%></td>
            <td><%=specialprice.getPapertype()%></td>
            <td><%=specialprice.getRank()%></td>
            <td><%=specialprice.getGweight()%></td>
            <td><%=specialprice.getSpecification()%></td>
            <td><%=specialprice.getUnit()%></td>
            <td><%=specialprice.getProducttype()%></td>
            <td><%=specialprice.getPrice()%></td>
        </tr>
        <%}%>
    </table><br>
    <button style="background:yellow;width: 150px;height: 50px;cursor: pointer;" onclick="addPurchasePlan()">添加特价表信息</button>
</center>
</body>
</html>
