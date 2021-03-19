<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Photo Gallery</title>
    <link href="<c:url value=""/> " rel="stylesheet"/>
</head>
<body>
<div class="gallery">
    <c:forEach items="${photos}" var="item">
        <img src="${item.path}" height="200" width="400" alt="no pic available">
    </c:forEach>
</div>
</body>
</html>