<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<c:forEach var="user" items="${requestScope.usersBase}">
    <ul>
        <li>Имя: <c:out value="${user.login}"/> </li>
        <li>Роль: <c:out value="${user.role}"/> </li>
    </ul>
</c:forEach>

<a href="/logout">Logout</a>
</body>
</html>