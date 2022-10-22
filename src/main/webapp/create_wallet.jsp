<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 16.10.2022
  Time: 20:50
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
        .to_cabinet{
            text-align: center;
            font-size: 100%;
            color: #e9f4fb;
        }
        #my_cabinet{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 35px;
        }
    </style>
    <title>Create Wallet</title>
</head>
<body>
<div class="to_cabinet">
    <a id="my_cabinet" href="cabinet.jsp">My cabinet</a>
</div>
<hr>
<div class="show_wallet">
    <form action="CreateWalletServlet" method="post">
        <input type="submit" name="wallet" value="Create wallet">
    </form>

</div>
</body>
</html>
