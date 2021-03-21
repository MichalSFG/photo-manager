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
<div class="uploadSection">
    <h3>Photo Upload:</h3>
    Select a photo to upload: <br>
    <form action="/add" method="post" enctype="multipart/form-data">
        Photo name: <input type="text" name="name"/><br>
        <input type="file" name="file" size="50"/><br>
        <input type="submit" value="Upload Photo"/>
    </form>
</div>
<div class="gallery">
    <c:forEach items="${photos}" var="item">
        <a href="${item.path}" data-lightbox="photoGallery" data-title="${item.name}">
            <img id="photo" src="${item.path}" title="${item.name}" alt=""></a>
        <a id="delButton" href="/del/${item.id}"
           onclick="if(confirm('Are you sure, you want to delete this photo?')===false) return false;"
           title="Delete ${item.name}">[x]</a>
    </c:forEach>
</div>
</body>
</html>