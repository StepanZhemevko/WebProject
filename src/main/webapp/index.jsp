<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(120deg, #2980b9, #8e44ad);
            height: 70vh;
            overflow: hidden;
        }

        .signup_link {
            margin: auto;
            text-align: center;
            font-size: 16px;
            color: #e9f4fb;
            display: flex;
            justify-content: space-between;
            padding: 13em;
            width: 60%;

        }
        a{
            font-size: 25px;
            color: azure;
            text-decoration: none;
        }   
        h1 {
            text-align: center;
            font-size: 200%;
            font-family: Arial;
        }
        .unique {
            font-size:200%;
        }
        .firs_leter {
            font-size:170%;
            font-style: oblique;
        }
    </style>
    <title>Welcome!</title>

</head>
<body>
<h1>
    <div class="unique">
        <span class="firs_leter">W</span>elcome!</div>
</h1>
<hr>
<div class="signup_link">
    <a href="${pageContext.request.contextPath}/registration.jsp" style="float: left;">Registration </a>

    <a href="${pageContext.request.contextPath}/login.jsp" style="float: right;">Log in </a>

</div>

</body>

</html>
