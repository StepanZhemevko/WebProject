<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 07.10.2022
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value ="${language}" />
<fmt:setBundle basename="messages.cabinet"/>
<html lang="${language}">
<head>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(120deg, #2980b9, #8e44ad);
            height: 70vh;
        }

        .author{
            font-family: "Times New Roman", serif;
            color: #e9f4fb;
            text-align: center;
            font-size:120%;
            font-style: oblique;
        }

        a{
            text-decoration:none;
            color: #e9f4fb;
        }
        .store{
            margin: auto;
            padding: 1em;
            display: flex;
            color: #e9f4fb;
            text-align: center;
            justify-content: space-between;
        }
        #link{
            text-align: left;
            font-size:170%;
        }
        .admin{
            margin: auto;
            font-size: 140%;
            padding: 0.5em;
            text-align: left;

        }
        .wallet{
            margin: auto;
            text-align: right;
            padding: 0.5em;
            font-size:150%;
        }
    </style>
    <title>Personal Cabinet</title>
</head>
<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="uk" ${language == 'uk' ? 'selected' : ''}>Ukraine</option>
    </select>
</form>
<div class="author" >
    <form action="CabinetServlet" method="post">
        <input type="submit" name="login" value="<%= session.getAttribute("name") %>">
    </form>
</div>
<div class="store">
    <a id="link" href="login.jsp"><fmt:message key="cabinet.label.logout"/></a>
    <form action="StoreServlet" method="post">
        <input id="lin" type="submit" name="store" value="<fmt:message key="cabinet.label.store"/>">
    </form>
</div>
<div class="admin">
    <% if ((boolean) session.getAttribute("admin")) { %>
    <a href="AdminServlet"><fmt:message key="cabinet.label.admin"/></a>
    <%}%>
</div>
<div class="wallet">
    <form action="WalletServlet" method="post" >
        <input type="submit" name="wallet" value="<fmt:message key="cabinet.label.wallet"/>">
    </form>
</div>
<hr>

<table border="1" width="70%" >
    <tr>
        <td><b><fmt:message key="cabinet.label.magazine#"/></b></td>
        <td><b><fmt:message key="cabinet.label.status"/></b></td>
        <td><b><fmt:message key="cabinet.label.stdate"/></b></td>
        <td><b><fmt:message key="cabinet.label.fidate"/></b></td>
    </tr>

    <%try{
        Class.forName("com.mysql.jdbc.Driver");
        java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","011235813Steve");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM mydb.subscription where user_id = '"+session.getAttribute("id")+"' ");
        while(rs.next()){
    %>
    <tr>
        <td><%=rs.getString("magazines_id") %></td>
        <td><%=rs.getString("status") %></td>
        <td><%=rs.getString("start_date") %></td>
        <td><%=rs.getString("finish_date") %></td>
    </tr>
    <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</table>
</body>
</html>