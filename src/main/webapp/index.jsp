<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value ="${language}" />
<fmt:setBundle basename="messages.messages"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(120deg, #2980b9, #8e44ad);
            height: 70vh;
            overflow: hidden;
        }

        .signup_link {
            margin: auto;
            text-align: center;
            font-size: 16px;
            color: #e9f4fb;
            display: flex;
            justify-content: space-between;
            padding: 13em;
            width: 60%;

        }
        a{
            font-size: 25px;
            color: azure;
            text-decoration: none;
        }
        h1 {
            text-align: center;
            font-size: 200%;
            font-family: Arial;
        }

    </style>
    <title>Welcome!</title>

</head>
<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="uk" ${language == 'uk' ? 'selected' : ''}>Ukraine</option>
    </select>
</form>
<h1>

        <label><fmt:message key="index.label.welcome"/></label>
</h1>
<hr>
<div class="signup_link">

    <a href="${pageContext.request.contextPath}/registration.jsp" style="float: left;"> <label><fmt:message key="index.label.register"/></label></a>
    <a href="${pageContext.request.contextPath}/login.jsp" style="float: right;"><label><fmt:message key="index.label.login"/></label> </a>

</div>
</body>

</html>
