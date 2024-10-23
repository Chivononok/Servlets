<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
    <h1>Список продуктов:</h1>
    <c:forEach var="product" items="${requestScope.productBase}">
        <ul>
            <li>Товар: <c:out value="${product.productName}"/> </li>
            <li>Цена: <c:out value="${product.productPrice}"/> </li>
            <form method="post" action="/delete">
                <input type="number" hidden name="id" value="${product.id}" />
                <input type="submit" name="delete" value="Удалить"/>
            </form>

            <form method="get" action="/update">
                <input type="number" hidden name="id" value="${product.id}" />
                <input type="submit" value="Редактированть"/>
            </form>
        </ul>
    </c:forEach>

    <h2>Добавление нового продукта</h2>
    <form method="post" action="/addProduct">
        <label><b>Name</b></label>
        <input type="text" placeholder="productName" name="productName">

        <label><b>Price</b></label>
        <input type="text" placeholder="productPrice" name="productPrice">
        <input type="submit" name="add" value="Добавить"/>
    </form>
    <form method="post" action="/showUsers">
        <input class="button" type="submit" value="Show users">
    </form>
<a href="/logout">Logout</a>
</body>
</html>