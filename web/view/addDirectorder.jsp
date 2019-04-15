<%--
  Created by IntelliJ IDEA.
  User: HUANG
  Date: 2018/12/19
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>添加直送订单信息</title>
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
<script type="text/javascript" src="/view/purchaseplanAdd.js"></script>
<body>
<h1 align="center">添加直送订单信息</h1>
<div align="center">
    <form id="ordermaster" action="/AddDriectOrder" method="post">
        <table style="border: 1px;border-collapse: collapse;">
            <tr>
                <td  style="background: lightgray;">订单编号</td>
                <td><input type="text" name="ordermasterid"/></td>
                <td  style="background: lightgray;">订单日期</td>
                <td><input type="date" name="ordertime"/></td>
                <td style="background: lightgray;">交货日期</td>
                <td><input type="date" name="suretime"/></td>
                <td style="background: lightgray;">客户名</td>
                <td><input type="text" name="customer"/></td>
                <td style="background: lightgray;">是否处理</td>
                <td><input type="text" name="state"/></td>
            </tr>
        </table>
        <h2>直送订单详情表添加</h2>
        <table style="border: 1px;border-collapse: collapse;" id="materialContainer">
            <tr>
                <td style="background: lightgray;">物料编码</td>
                <td style="background: lightgray;">数量</td>
                <td style="background: lightgray;">删除</td>
            </tr>
            <tr>
                <td><select onclick="getMaterialCodes(this)" name="itemid"></select></td>
                <td><input type="text" name="quantity"></td>
                <td><a href="#" onclick="RemoveMaterial(this);">删除</a></td>
            </tr>
        </table>
    </form>
    <button style="background: yellow; width: 150px; height: 40px; margin-top:50px;" onclick="submitForm()">添加直送订单信息</button>
    <button style="background: yellow; width: 150px; height: 40px; margin-top:50px;" onClick="AddMaterial();">添加直送订单详情信息</button>
</div>
</body>
</html>
