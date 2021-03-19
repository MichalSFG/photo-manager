<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Photo Gallery</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/lightbox.min.css"/>">
    <script src="<c:url value="/resources/js/lightbox-plus-jquery.min.js"/> "></script>
</head>
<body>
<div class="gallery">
    <c:forEach items="${photos}" var="item">
        <a href="${item.path}" data-lightbox="photoGallery" data-title="${item.name}">
            <img src="${item.path}" title="${item.name}" alt=""></a>
    </c:forEach>
</div>
</body>
</html>