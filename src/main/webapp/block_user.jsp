<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 21.10.2022
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value ="${language}" />
<fmt:setBundle basename="messages.blockUser"/>
<html lang="${language}">
<head>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(120deg, #2980b9, #8e44ad);
            height: 70vh;
        }
        table{
            text-align: center;
        }
        .center{
            padding: 2em;
            text-align: center;

        }
    </style>
    <title>Block page</title>
</head>
<body>
<div class="center">
<table border="1" width="60%" >
    <tr>
        <td><b><fmt:message key="blockUser.label.id"/></b></td>
        <td><b><fmt:message key="blockUser.label.name"/></b></td>
        <td><b><fmt:message key="blockUser.label.bl_un"/></b></td>

    </tr>
        <%try{
  Class.forName("com.mysql.jdbc.Driver");
  java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","011235813Steve");
  Statement st = con.createStatement();
  ResultSet rs =st.executeQuery("SELECT * FROM mydb.user");
  while(rs.next()){
%>
    <tr>
        <td><%=rs.getInt("id") %></td>
        <td><%=rs.getString("name_and_surname") %></td>
        <td> <form action="BlockUserServlet" method="post">
            <input type="hidden" name="id" value="<%= rs.getInt("id")%>" />
            <input type="submit" value="<fmt:message key="blockUser.submit.block"/>" name="block">
        </form>
            <p><form action="UnblockUserServlet" method="post">
                <input type="hidden" name="id" value="<%= rs.getInt("id")%>" />
                <input type="submit" value="<fmt:message key="blockUser.submit.unblock"/>" name="unblock">
            </form></p>
        </td>
    </tr>
        <%
    }
  } catch (Exception e) {
    e.printStackTrace();
  }
%>
</div>
</body>
</html>
