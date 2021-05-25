<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/25/2021
  Time: 3:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
</head>
<body>
<h1>List product</h1>
<p>
    <a href="products?action=create">Thêm mới sản phẩm</a>
</p>

<table border="1px">
    <tr>
        <td>Tên SP</td>
        <td>Giá bán</td>
        <td>Số lượng</td>
        <td></td>
        <td></td>
    </tr>
    <c:forEach items='${requestScope["products"]}' var="p">
        <tr>
            <td>
                <a href="/products?action=view&id=${p.getId()}">${p.getName()}</a>
            </td>
            <td>${p.getPrice()}</td>
            <td>${p.getQuantity()}</td>
            <td><a href="/products?action=edit&id=${p.getId()}">Chỉnh sửa</a></td>
            <td><a href="/products?action=delete&id=${p.getId()}">Xóa</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
