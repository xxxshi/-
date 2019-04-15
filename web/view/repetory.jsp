<%@ page import="com.erp.util.DBUtil" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 14948
  Date: 2018/12/18
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>库存表</title>
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
<%
    String sql = "select * from repetory";
    DBUtil dbu = new DBUtil();
    ArrayList<ArrayList<Object>> Al=DBUtil.findByParamter(sql,null);

%>
<center>
    <h1>库存表</h1>
    <table cellspacing="0" cellpadding="0">
        <tr>
            <th>物料编码</th>
            <th>库号</th>
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
            if(Al!=null){
                for(int i=0; i<Al.size(); i++){
                    out.println("<tr>");
                    for(int j=0; j<Al.get(i).size();j++){
                        out.println("<td>");
                        out.println(Al.get(i).get(j));
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }}
        %>

    </table><br>
</center>
</body>
</html>
