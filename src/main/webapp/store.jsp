<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 11.10.2022
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value ="${language}" />
<fmt:setBundle basename="messages.store"/>
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
    .sort_link {
      margin: auto;
      text-align: center;
      font-size: 10px;
      color: #e9f4fb;
      display: flex;
      justify-content: space-between;
      padding: 0;
      width: 50%;

    }
    a{
      font-size: 25px;
      color: azure;
      text-decoration: none;
    }
    #my_cabinet{
      text-align: center;
      color: azure;
      text-decoration: none;
      font-size: 35px;
    }
  </style>
    <title>Store</title>
</head>
<body>
<div class="to_cabinet">
  <a id="my_cabinet" href="cabinet.jsp" ><fmt:message key="store.label.my_cabinet"/></a>
</div>
<hr>
<div class ="sort_link"><a id="byT" href="by_title.jsp" ><fmt:message key="store.label.sort_name"/></a>

  <a id="byP" href="by_prise.jsp" ><fmt:message key="store.label.sort_prise"/></a>
</div>
<hr>
<table border="1" width="60%">
<tr>
  <td><b><fmt:message key="store.label.mag#"/></b></td>
  <td><b><fmt:message key="store.label.name"/></b></td>
  <td><b><fmt:message key="store.label.prise"/></b></td>
  <td><b><fmt:message key="store.label.descr"/></b></td>
  <td><b><fmt:message key="store.label.image"/></b></td>
  <td><b><fmt:message key="store.label.category"/></b></td>
  <td><b><fmt:message key="store.label.publisher"/></b></td>
</tr>
<%try{
  Class.forName("com.mysql.jdbc.Driver");
  java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","011235813Steve");
  Statement st = con.createStatement();
  ResultSet rs =st.executeQuery("SELECT * FROM mydb.magazines");
  while(rs.next()){
%>
<tr>
  <td><%=rs.getInt("id") %></td>
  <td><%=rs.getString("magazines_name") %></td>
  <td><%=rs.getInt("prise") %></td>
  <td><%=rs.getString("description") %></td>
  <td><img src="<%=rs.getString("image_link") %>" width="100" height="100"></td>
  <td><%=rs.getInt("categories_id") %></td>
  <td><%=rs.getInt("publishers_id") %></td>
  <td> <form action="MakeSubscriptionServlet" method="post">
    <input type="hidden" name="magazineId" value="<%= rs.getInt("id")%>" />
    <input type="hidden" name="magazineName" value="<%= rs.getString("magazines_name")%>" />
    <input type="hidden" name="image" value="<%= rs.getString("image_link")%>" />
    <input type="hidden" name="magazinePrise" value="<%= rs.getInt("prise")%>" />

    <input type="submit" value="<fmt:message key="store.submit.order"/>" name="order">
  </form></td>
</tr>
<%
    }
  } catch (Exception e) {
    e.printStackTrace();
  }
%>
</table>
<%try{
  Class.forName("com.mysql.jdbc.Driver");
  java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","011235813Steve");
  Statement st = con.createStatement();
  ResultSet rs2 =st.executeQuery("SELECT * FROM mydb.categories");
  while(rs2.next()){
%>
  <form action="GetByCategoryServlet" method="post">
    <label><%= rs2.getString("category_name")%></label>
    <input type="checkbox" name="selected" value="<%= rs2.getString("category_name")%> ">

    <input type="submit" value="<fmt:message key="store.submit.submit"/>" name="from_category" >
  </form>
<%
    }
  } catch (Exception e) {
    e.printStackTrace();
  }
%>

<form action="SearchByNameServlet" method="post">
    <input type="text" name="select_name" >
  <input type="submit" value="<fmt:message key="store.submit.search"/>" name="search" >
</form>

</body>
</html>
