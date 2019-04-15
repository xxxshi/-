<%@ page import="com.erp.util.DBUtil" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 14948
  Date: 2018/12/17
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>分切成品出仓表</title>
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
    <script>
        function out_cut() {
            self.location("respetory.jsp");
        }
    </script>
</head>
<body>
<%
    String sql = "select * from cut_outstoragemaster";
    DBUtil dbu = new DBUtil();
    ArrayList<ArrayList<Object>> Al=DBUtil.findByParamter(sql,null);

%>

    <center>
    <h1>分切成品出仓主表</h1>
        <a href="/cutOutServlet"><input type="button" value="分切出仓"></a>
    <table cellspacing="0" cellpadding="0">
        <tr>
            <th>出仓单号</th>
            <th>订单单号</th>
            <th>客户</th>
            <th>出仓日期</th>
            <th>客户下单时间</th>
            <th>总价</th>
            <th>总张数</th>
            <th>总数量</th>
        </tr>

        <%
            if(Al!=null){
            for(int i=0; i<Al.size(); i++){
                out.println("<tr>");
                for(int j=0; j<Al.get(i).size();j++){
                    out.println("<td>");
                    if(j==1)
                        out.println("<a href='cut_outstorage.jsp?oid="+Al.get(i).get(j)+"'>"+Al.get(i).get(j)+"</a>");
                    else
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
