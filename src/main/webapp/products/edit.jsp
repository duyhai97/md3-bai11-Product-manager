<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Edit product</title>
    <style>
        .message{
            color: darkmagenta;
        }
    </style>
</head>
<body>
<h1>Sửa thông tin sản phẩm</h1>
<c:if test='${requestScope["message"] != null}'>
    <span class="message">${requestScope["message"]}</span>
</c:if>
<p>
    <a href="/products">Quay lại danh sách sản phẩm</a>
</p>
<form action="" method="post">
    <table>
        <tr>
            <td>Tên sản phẩm:</td>
            <td><input type="text" name="name" id="name" value="${requestScope["product"].getName()}"></td>
        </tr>
        <tr>
            <td>Giá bán:</td>
            <td><input type="text" name="price" id=" price" value="${requestScope["product"].getPrice()}"></td>
        </tr>
        <tr>
            <td>Số lượng:</td>
            <td><input type="text" name="quantity" id=" quantity" value="${requestScope["product"].getQuantity()}"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Nhấn đê :v"></td>
        </tr>
    </table>

</form>
</body>
</html>
