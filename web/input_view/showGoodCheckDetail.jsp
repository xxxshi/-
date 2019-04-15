<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>无标题文档</title>
    <link href="../css/tablecloth.css" rel="stylesheet" type="text/css">
</head>

<body>
<h1 align="center">客诉报告详情</h1>
<table>
    <tbody>
    <tr>
        <td>供应商:${supply}</td>
        <td>进仓日期:${inDate}</td>
    </tr>
    <tr>
        <td>采购单号:${purchaseid}</td>
        <td>进仓单号:${storageinid}</td>
    </tr>
    </tbody>
</table>
<br>
<table >
    <tbody>
    <tr>
        <td colspan="6" style="text-align: center;">抽检货品</td>
    </tr>
    <tr>
        <th>品牌</th>
        <th>纸种</th>
        <th>克重</th>
        <th>规格</th>
        <th>订货数量</th>
        <th>件号</th>
    </tr>
    <c:forEach items="${materialDetails}" var="materialDetail">
        <tr>
            <c:forEach items="${materialDetail}" var="field">
                <td>${field}</td>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<table>
    <tbody>
    <tr>
        <td colspan="9" style="text-align: center;">检测结果</td>
    </tr>
        <c:forEach items="${problemDetails}" var="problem">
            <tr>
                <td>存在问题</td>
                <c:forEach items="${problem}" var="field" varStatus="status">
                    <c:if test="${status.index<7}">
                        <td>${field}</td>
                    </c:if>
                    <c:if test="${status.index==7}">
                        <td>合计(kg)</td>
                        </tr>
                        <tr>
                        <td>损失重量</td>
                        <td>
                            ${field}
                        </td>
                    </c:if>
                    <c:if test="${status.index>7}">
                        <td>${field}</td>
                    </c:if>
                </c:forEach>
            </tr>
        </c:forEach>
    </tbody>
</table>

<h3>结论</h3>
<table width="100%" border="1">
    <tbody>
    <tr>
        <th>品牌</th>
        <th>纸种</th>
        <th>级别</th>
        <th>克重</th>
        <th>规格</th>
        <th>订货数量</th>
        <th>损失率</th>
        <th>损失重量</th>
        <th>入仓重量</th>
    </tr>
    <c:forEach items="${resultDetails}" var="resultDetail">
        <tr>
            <c:forEach items="${resultDetail}" var="field">
                <td>${field}</td>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
