<%@ page import="com.erp.util.DBUtil" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 14948
  Date: 2018/12/18
  Time: 0:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分切成品出仓明细表</title>
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
<%
    String oid = request.getParameter("oid");
    String[] oids = {oid};
    String sql = "select * from cut_outstorage where ordermasterid = ?";
    String msql="select * from cut_outstoragemaster where ordermasterid =?";
    ArrayList<ArrayList<Object>> Al=DBUtil.findByParamter(sql,oids);
    ArrayList<ArrayList<Object>> Sl=DBUtil.findByParamter(msql,oids);
%>
    <center>
        <h1>分切成品出仓明细表表</h1>
        <%
            out.println("<table align=\"center\">");
            for(int i=0;i< Sl.size();i++){
                out.println("<tr>");
                out.println("<td colspan=\"2\">"+"出仓单号："+Sl.get(i).get(0)+"</td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td>"+"订单编号："+Sl.get(i).get(1)+"</td>");
                out.println("<td>"+"客户："+Sl.get(i).get(2)+"</td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td>"+"出仓日期："+Sl.get(i).get(3)+"</td>");
                out.println("<td>"+"客户下单日期："+Sl.get(i).get(4)+"</td>");
                out.println("</tr>");

            }
            out.println();
            out.println("</table>");
        %>
        <table cellspacing="0" cellpadding="0">
            <tr>
                <th>订单编号</th>
                <th>品牌</th>
                <th>纸种</th>
                <th>级别</th>
                <th>克重</th>
                <th>规格</th>
                <th>单位</th>
                <th>数量</th>
                <th>张数</th>
                <th>单价</th>
                <th>金额</th>
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

            <%
                out.println("<table align=\"center\">");
                for(int i=0;i< Sl.size();i++){
                    out.println("<tr>");
                    out.println("<td colspan=\"2\">"+"总价："+Sl.get(i).get(5)+"</td>");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td>"+"张数："+Sl.get(i).get(6)+"</td>");
                    out.println("<td>"+"总数："+Sl.get(i).get(7)+"</td>");
                    out.println("</tr>");
                }
                out.println();
                out.println("</table>");
            %>

        </table><br>
    </center>
</body>
</html>
