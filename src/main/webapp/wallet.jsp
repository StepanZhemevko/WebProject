<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 15.10.2022
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value ="${language}" />
<fmt:setBundle basename="messages.wallet"/>
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
    <a id="my_cabinet" href="cabinet.jsp"><fmt:message key="wallet.label.my_cab"/></a>
</div>
<hr>
<div class="show_wallet">
    <form action="ShowWalletServlet" method="post">
        <input type="submit" name="wallet" value="<fmt:message key="wallet.label.show"/>">
    </form>

    <label>

<textarea name="area" rows="4" cols="40">
   <fmt:message key="wallet.label.id"/> <%=session.getAttribute("walletId")%>
   <fmt:message key="wallet.label.balance"/> <%=session.getAttribute("balance")%>
</textarea>
    </label>

    <form action="UpBalanceServlet" method="post">
        <input type="submit" name="replenish" value="<fmt:message key="wallet.label.replenish"/>">

        <input type="number" name="amount" required>
        <span></span>
        <label><fmt:message key="wallet.label.sum"/></label>
    </form>

</div>

</body>
</html>
