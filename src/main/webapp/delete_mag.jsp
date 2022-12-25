<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 21.10.2022
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value ="${language}" />
<fmt:setBundle basename="messages.deleteMag"/>
<html lang="${language}">
<head>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(120deg, #2980b9, #8e44ad);
            height: 70vh;
        }
        .center{
            text-align: center;
            font-size: 16px;
            padding: 4em;
        }

    </style>
    <title>Delete</title>
</head>
<body>
<div class="center">
<form action="DeleteMagazineServlet" method="post">

    <label style="color: antiquewhite" ><fmt:message key="deleteMag.label.q"/></label>
    <p><input type="number" name="delete" min="1" required></p>
    <span></span>
    <p><input type="submit" value="<fmt:message key="deleteMag.submit" />"></p>

</form>
</div>
</body>
</html>
