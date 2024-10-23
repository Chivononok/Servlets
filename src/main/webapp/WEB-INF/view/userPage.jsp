<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>

<c:forEach var="product" items="${requestScope.productBase}">
    <ul>
        <li>Товар: <c:out value="${product.productName}"/> </li>
        <li>Цена: <c:out value="${product.productPrice}"/> </li>
    </ul>
</c:forEach>
<form method="post" action="/showUsers">
    <input class="button" type="submit" value="Show users">
</form>
    <a href="/logout">Logout</a>
</body>
</html>