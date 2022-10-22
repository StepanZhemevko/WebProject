<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 12.09.2022
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
    <link rel="stylesheet" href="css/formStyle.css">
</head>
<body>
<div class="center">
    <h1>Registration</h1>
    <form action="RegistrServlet" method="post">

        <div class="txt_field">
            <input type="text" name="login" required>
            <span></span>
            <label>Login</label>
        </div>
        <div class="txt_field">
            <input type="password" name="password" required>
            <span></span>
            <label>Password</label>
        </div>

        <div class="txt_field">
            <input type="text" name="name_and_surname" required>
            <span></span>
            <label>Name and Surname</label>
        </div>

        <div class="txt_field">
            <input type="email" name="email" required>
            <span></span>
            <label>Email</label>
        </div>
        <div class="txt_field">
            <input type="tel" name="telephone" required>
            <span></span>
            <label>Telephone</label>
        </div>
        <input type="submit" value="Register">
        <div class="signup_link">
            Already have account?<a href="login.jsp"> Login</a>
        </div>
    </form>
</div>

</body>
</html>
