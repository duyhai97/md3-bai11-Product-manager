<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/25/2021
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>
</head>
<body>
<h1>Thông tin sản phẩm</h1>
<fieldset>
    <legend>Thông tin sản phẩm</legend>
    <table>
        <tr>
            <td>Tên Sản phẩm</td>
            <td>${requestScope["product"].getName()}</td>
        </tr>
        <tr>
            <td>Giá bán</td>
            <td>${requestScope["product"].getPrice()}</td>
        </tr>
        <tr>
            <td>Số lượng</td>
            <td>${requestScope["product"].getQuantity()}</td>
        </tr>
    </table>
</fieldset>
</body>
</html>
