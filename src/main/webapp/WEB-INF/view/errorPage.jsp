<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ошибка</title>
</head>
<body>
    <h1>В процессе работы возникла ошибка</h1>
    <LABEL>"${requestScope.errorMessage}"</LABEL>
</body>
</html>