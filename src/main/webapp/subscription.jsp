<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 19.10.2022
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value ="${language}" />
<fmt:setBundle basename="messages.subscription"/>
<html lang="${language}">
<head>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(120deg, #2980b9, #8e44ad);
            height: 70vh;
        }
        .to_cabinet{
            text-align: center;
            font-size: 100%;
            color: #e9f4fb;
        }
        .form{
            text-align: center;
        }
        #my_cabinet{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 35px;
        }
    </style>
    <title>Subscription</title>
</head>
<body>
<div class="to_cabinet">
    <a id="my_cabinet" href="cabinet.jsp" ><fmt:message key="subscription.label.my_cab"/></a>
</div>
<hr>

<div class="form">

    <label><img src="<%= session.getAttribute("image") %>" width="100" height="100"></label>
    <p><label><fmt:message key="subscription.label.name"/> <%=session.getAttribute("magazineName")%></label></p>
    <p><label><fmt:message key="subscription.label.stdate"/> <%=session.getAttribute("beginTime")%></label></p>
    <p><label><fmt:message key="subscription.label.id"/> <%=session.getAttribute("id")%></label></p>
    <p><label><fmt:message key="subscription.label.balance"/> <%=session.getAttribute("balance")%></label></p>
    <form action="MakeOrderServlet" method="post">
        <input type="date" name="end_date" required>
        <span></span>
        <label>And date</label>
        <input type="submit" value="<fmt:message key="subscription.submit"/>">
    </form>
</div>
</body>
</html>
