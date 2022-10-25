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
<fmt:setBundle basename="messages.editMag"/>
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
  <title>Edit</title>
</head>
<body>
<div class="to_cabinet">
  <a id="my_cabinet" href="cabinet.jsp" ><fmt:message key="editMag.label.my_cabinet"/></a>
</div>
<hr>
<table border="1" width="60%">
  <tr>
    <td><b><fmt:message key="editMag.label.mag#"/></b></td>
    <td><b><fmt:message key="editMag.label.name"/></b></td>
    <td><b><fmt:message key="editMag.label.prise"/></b></td>
    <td><b><fmt:message key="editMag.label.descr"/></b></td>
    <td><b><fmt:message key="editMag.label.image"/></b></td>
    <td><b><fmt:message key="editMag.label.category"/></b></td>
    <td><b><fmt:message key="editMag.label.publisher"/></b></td>
  </tr>
  <%try{
    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","011235813Steve");
    Statement st = con.createStatement();
    ResultSet rs =st.executeQuery("SELECT * FROM mydb.magazines");
    while(rs.next()){
  %>
  <tr><form action="EditMagazineServlet" method="post">
    <td><label><%=rs.getInt("id") %></label><input type="hidden" name="magazineId" value="<%=rs.getInt("id") %>"/> </td>
    <td><input type="text" name="magazineName" value="<%= rs.getString("magazines_name")%>" /></td>
    <td><input type="number" name="magazinePrise" value="<%= rs.getInt("prise")%>" /></td>
    <td><input type="text" name="magazineDescription" value="<%=rs.getString("description")%>"/></td>
    <td><p><img src="<%=rs.getString("image_link") %>" width="100" height="100"></p>
      <p><input type="text" name="imageLink" value="<%=rs.getString("image_link")%>"/></p>
    </td>
    <td><input type="number" name="magazineCat" value="<%=rs.getInt("categories_id") %>"/></td>
    <td><input type="number" name="magazinePub" value="<%=rs.getInt("publishers_id") %>"/></td>
    <td>
      <input type="submit" value="<fmt:message key="editMag.submit"/>" name="edit">
    </td>
  </form>
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
