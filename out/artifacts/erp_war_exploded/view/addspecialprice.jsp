<%--
  Created by IntelliJ IDEA.
  User: HUANG
  Date: 2018/12/18
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单信息输入</title>
    <link href="/view/formCss.css" rel="stylesheet">
</head>
<body>
<h1 align="center" 特价表信息输入></h1>
<div class="materiel" >
    <form method="post" action="/AddSpecialPrice">

        <div class="customer"> <!--暂登录-->
            <label>客户名：</label>
            <input type="text" name="customer" /> <!--aut功能-->
        </div>

        <div class="specialid"> <!--暂登录-->
            <label>特价表号：</label>
            <input type="text" name="specialid" /> <!--aut功能-->
        </div>

        <div class="itemid">
            <label>物料编码：</label>
            <input type="text" name="itemid" /><!--ta的链接 可用tab键进行顺序切换-->
        </div>

        <div class="affirmdate">
            <label>批准日期：</label>
            <input type="text" name="affirmdate" /><!--键顺序的链接 可用tab键进行顺序切换-->
        </div>

        <div class="brand">
            <label>品牌：</label>
            <input type="text" name="brand" /><!--tabinde 可用tab键进-->
        </div>

        <div class="papertype">
            <label>纸种:</label>
            <input type="text" name="papertype" /><!--tabindex键进行顺序切换-->
        </div>

        <div class="rank">
            <label>级别:</label>
            <input type="text" name="rank" /><!--ta的链接 可用tab键进行顺序切换-->
        </div>

        <div class="gweight">
            <label>克重:</label>
            <input type="text" name="gweight"/><!--键顺序的链接 可用tab键进行顺序切换-->
        </div>

        <div class="specification">
            <label>规格:</label>
            <input type="text" name="specification" /><!--tabindex:带有指定 tab -->
        </div>
        <div class="unit">
            <label>单位:</label>
            <input type="text" name="unit"  /><!--tabindex:带有指定-->
        </div>
        <div class="producttype">
            <label>产品类型:</label>
            <input type="text" name="producttype" /><!--tabindex:带有指定 tab-->
        </div>
        <div class="price">
            <label>单价：</label>
            <input type="text" name="price" /><!--tabindex:带有指定 tab-->
        </div>

        <div class="materielAdd">
            <label></label>
            <button type="submit" tabindex="5" >添加订单信息</button>
        </div>
    </form>
</div>
</body>
</html>

