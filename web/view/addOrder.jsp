<%--
  Created by IntelliJ IDEA.
  User: HUANG
  Date: 2018/12/18
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>添加采购计划</title>
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
<script type="text/javascript" language="JavaScript">
    function judegAccept(){
        var itemids = document.getElementsByName("itemid");
        var quantities=document.getElementsByName("quantity");
        var ordermasterid=document.getElementsByName("ordermasterid")[0].value;
        var ordertime=document.getElementsByName("ordertime")[0].value;
        var suretime=document.getElementsByName("suretime")[0].value;
        var customer=document.getElementsByName("customer")[0].value;
        var state=document.getElementsByName("state")[0].value;
        var itemidArray="";
        var quantityArray="";
        for(var i=0;i<itemids.length;i++){
            var index=itemids[i].selectedIndex;
            itemidArray+=itemids[i].options[index].value;
            quantityArray+=quantities[i].value;
            if(i!=itemids.length-1){
                itemidArray+=",";
                quantityArray+=",";
            }
        }
        console.log(itemidArray,quantityArray);
        $.post("/judgeAccept",{"itemid":itemidArray,"quantity":quantityArray,
            "ordermasterid":ordermasterid,"ordertime":ordertime,"suretime":suretime,"customer":customer
            ,"state":state},function (data, status, req) {
            if(status=="success"){
                var isSuccess=req.responseText;
                if(isSuccess=="success"){
                    alert("可以生成订单");
                }else{
                    alert("库存数量不足，无法生成订单");
                }
            }
        })
    }
</script>
<body>
<h1 align="center">添加销售订单信息</h1>
<div align="center">
    <form id="ordermaster" action="/AddOreder" method="post">
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
        <h2>订单详情表添加</h2>
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
    <button style="background: yellow; width: 150px; height: 40px; margin-top:50px;" onclick="submitForm()">添加订单信息</button>
    <button style="background: yellow; width: 150px; height: 40px; margin-top:50px;" onClick="AddMaterial();">添加订单详情信息</button>
    <button style="background: yellow; width: 150px; height: 40px; margin-top:50px;" onclick="judegAccept();">确认是否接单
    </button>
</div>
</body>
</html>
