<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value ="${language}" />
<fmt:setBundle basename="messages.login"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
  <meta charset="utf-8">
  <title>Login</title>
  <link rel = "stylesheet" href="css/formStyle.css">
</head>
<body>
<div class="center">
  <form action="LoginServlet" method="post">
    <div class="txt_field">
      <input type="text" name="login" required>
      <span></span>
      <label><fmt:message key="login.label.login"/></label>
    </div>
    <div class="txt_field">
      <input type="password" name="password" required>
      <span></span>
      <label><fmt:message key="login.label.password"/></label>
    </div>
    <input type="submit" value="<fmt:message key="login.label.submit"/>">
    <div class="signup_link">
      <fmt:message key="login.label.not"/> <a href="${pageContext.request.contextPath}/registration.jsp"><fmt:message key="login.label.signup"/></a>
    </div>
  </form>
</div>
</body>
</html>

