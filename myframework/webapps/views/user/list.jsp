<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>list</title>
</head>
<body>
<table>
    <tr>
        <th>#</th>
        <td>userId</td>
        <td>name</td>
    </tr>
    <c:forEach items="${users}" var="user" varStatus="status">
        <tr>
            <th scope="row">${status.count}</th>
            <td>${user.userId}</td>
            <td>${user.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>