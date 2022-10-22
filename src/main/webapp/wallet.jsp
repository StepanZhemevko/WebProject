<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 15.10.2022
  Time: 22:57
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
        .show_wallet {
            margin: auto;
            text-align: center;
            font-size: 16px;
            color: #e9f4fb;
            display: flex;
            justify-content: space-between;
            padding: 13em;
            width: 60%;

        }
    </style>
    <title>Wallet</title>
</head>
<body>
<div class="to_cabinet">
    <a id="my_cabinet" href="cabinet.jsp">My cabinet</a>
</div>
<hr>
<div class="show_wallet">
    <form action="ShowWalletServlet" method="post">
        <input type="submit" name="wallet" value="Show my wallet">
    </form>

    <label>

<textarea name="area" rows="4" cols="40">
    Your wallet id is: <%=session.getAttribute("wallet_id")%>
    Your balance is: <%=session.getAttribute("balance")%>
</textarea>
    </label>

    <form action="UpBalanceServlet" method="post">
        <input type="submit" name="replenish" value="Replenish">

        <input type="number" name="amount" required>
        <span></span>
        <label>Sum to replenish</label>
    </form>

</div>

</body>
</html>
