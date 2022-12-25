<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 12.09.2022
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value ="${language}" />
<fmt:setBundle basename="messages.registration"/>

<html lang="${language}">
<head>
    <meta charset="utf-8">
    <title>Registration</title>
    <link rel="stylesheet" href="css/formStyle.css">
</head>
<body>
<div class="center">
    <h1><fmt:message key="registration.label.h"/></h1>
    <form action="RegistrServlet" method="post">

        <div class="txt_field">
            <input type="text" name="login" required>
            <span></span>
            <label><fmt:message key="registration.label.login"/></label>
        </div>
        <div class="txt_field">
            <input type="password" name="password" minlength="8" required>
            <span></span>
            <label><fmt:message key="registration.label.password"/></label>
        </div>

        <div class="txt_field">
            <input type="text" name="name_and_surname" required>
            <span></span>
            <label><fmt:message key="registration.label.name_and_surname"/></label>
        </div>

        <div class="txt_field">
            <input type="email" name="email" required>
            <span></span>
            <label><fmt:message key="registration.label.email"/></label>
        </div>
        <div class="txt_field">
            <input type="tel" name="telephone" minlength=10 pattern="[0-9]{3}[0-9]{3}[0-9]{4}" required >
            <span></span>
            <label><fmt:message key="registration.label.telephone"/></label>
        </div>
        <input type="submit" value="<fmt:message key="registration.label.submit"/>">
        <div class="signup_link">
            <fmt:message key="registration.label.question"/><a href="login.jsp"> <fmt:message key="registration.label.to_login"/></a>
        </div>
    </form>
</div>

</body>
</html>
