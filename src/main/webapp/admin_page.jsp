<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 14.10.2022
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value ="${language}" />
<fmt:setBundle basename="messages.adminPage"/>
<html lang="${language}">
<head>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(120deg, #2980b9, #8e44ad);
            height: 70vh;
        }
        .change{
            font-family: "Times New Roman", serif;
            color: #e9f4fb;
            text-align: center;
            font-size:120%;
            font-style: oblique;
            padding: 0;
        }
        .form{
            font-family: "Times New Roman", serif;
            color: #e9f4fb;
            text-align: center;
            font-size:120%;
            font-style: oblique;
            padding: 1em;
        }
        .to_cabinet{
            text-align: center;
            font-size: 100%;
            color: #e9f4fb;
        }
        .center{
            text-align: center;
            font-size: 90%;
            color: #e9f4fb;
        }
        #my_cabinet{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 30px;
        }
        #to_create_magazine{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 20px;
        }
        #to_delete_magazine{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 20px;
        }
        #to_edit_magazine{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 20px;
        }
        #block_user{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 20px;
        }


    </style>
    <title>Admin</title>
</head>
<body>
<div class="to_cabinet">
<a id="my_cabinet" href="cabinet.jsp"><fmt:message key="adminPage.label.my_cabinet"/></a>
</div>
<hr>
<div class="change">
    <form action="AddCategoryServlet" method="post">
        <input type="text" name="category_name"  required>
        <span></span>
        <label><fmt:message key="adminPage.label.catname"/></label>

        <p><input type="submit" value="<fmt:message key="adminPage.submit.catname"/>"></p>
    </form>
</div>
<div class="form">
    <form action="AddPublisherServlet" method="post">
        <input type="text" name="publisher_name" required>
        <span></span>
        <label><fmt:message key="adminPage.label.pubname"/></label>

        <p><input type="submit" value="<fmt:message key="adminPage.submit.pubname"/>"></p>
    </form>
</div>
<hr>
<div class="center">
<h3><fmt:message key="adminPage.label.q1"/> </h3>
<a id="to_create_magazine" href="add_magazine.jsp"><fmt:message key="adminPage.label.create"/></a>
<hr>
<h3><fmt:message key="adminPage.label.q2"/></h3>
<a id="to_delete_magazine" href="delete_mag.jsp"><fmt:message key="adminPage.label.delete"/></a>
<hr>
<h3><fmt:message key="adminPage.label.q3"/></h3>
<a id="to_edit_magazine" href="edit_mag.jsp"><fmt:message key="adminPage.label.edit"/></a>
<hr>
<h3><fmt:message key="adminPage.label.q4"/></h3>
<a id="block_user" href="block_user.jsp"><fmt:message key="adminPage.label.block"/></a>
</div>
</body>
</html>
