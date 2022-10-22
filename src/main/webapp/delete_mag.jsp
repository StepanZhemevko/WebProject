<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 21.10.2022
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(120deg, #2980b9, #8e44ad);
            height: 70vh;
        }
    </style>
    <title>Delete</title>
</head>
<body>
<form action="DeleteMagazineServlet" method="post">
    <label>Enter number of magazine you want to delete</label>
    <p><input type="number" name="delete"></p>
    <span></span>
    <p><input type="submit" value="submit"></p>
</form>
</body>
</html>
