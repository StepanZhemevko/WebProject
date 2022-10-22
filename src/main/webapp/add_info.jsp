<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 13.10.2022
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
        }
        #my_cabinet{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 35px;
        }
        .to_cabinet{
            text-align: center;
            font-size: 100%;
            color: #e9f4fb;
        }
    </style>
    <title>Change user info</title>
</head>
<body>
<div class="to_cabinet">
<a id="my_cabinet" href="cabinet.jsp" ><%= session.getAttribute("name") %></a>
</div>
<hr>
<div class="change">
    <form action="AddInfoServlet" method="post">
        <div class="txt_field">
            <input type="text" name="password" value="<%= session.getAttribute("password") %>" required>
            <span></span>
            <label>Password</label>
        </div>
        <div class="txt_field">
            <input type="text" name="name_and_surname" value="<%= session.getAttribute("name_and_surname") %>" required>
            <span></span>
            <label>Name and Surname</label>
        </div>

        <div class="txt_field">
            <input type="email" name="email" value="<%= session.getAttribute("email") %>" required>
            <span></span>
            <label>Email</label>
        </div>
        <div class="txt_field">
            <input type="tel" name="telephone" value="<%= session.getAttribute("telephone") %>" required>
            <span></span>
            <label>Telephone</label>
        </div>

        <input type="submit" value="submit">
    </form>
</div>

<hr>
</body>
</html>
