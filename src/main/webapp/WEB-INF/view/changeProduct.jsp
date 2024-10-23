<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Старые данные по продукту:</h1>
<div>Name: <c:out value="${requestScope.product.productName}"/> </div>
<div>Price: <c:out value="${requestScope.product.productPrice}"/> </div>

<br />

<form method="post" action="/update">

    <label>Новое название: <input type="text" name="name" placeholder="${requestScope.product.productName}" /></label><br>
    <label>Новая цена: <input type="text" name="price" placeholder="${requestScope.product.productPrice}" /></label><br>

    <input type="number" hidden name="id" value="${requestScope.product.id}"/>

    <input type="submit" value="Сохранить" name="Ok"><br>
</form>
</body>
</html>