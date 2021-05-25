<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/25/2021
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Product</title>
    <style>
        .message{
            color: darkmagenta;
        }
    </style>
</head>
<body>
    <h1>Delete product</h1>
<p>
    <a href="/products">Quay lại danh sách sản phẩm</a>
</p>
    <h4>
        <c:if test='${requestScope["message"] != null}'>
            <span class="message">${requestScope["message"]}</span>
        </c:if>
    </h4>


    <form action="" method="post">
        <h3>Bạn chắc chắn xóa chưa ?? :D</h3>
        <fieldset>
        <legend>Thông tin sản phẩm</legend>
        <table>
            <tr>
                <td>Tên sản phẩm</td>
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
            <tr>
                <td></td>
                <td><input type="submit" value="Chắc đét :D"></td>
            </tr>
        </table>
        </fieldset>
    </form>
</body>
</html>
