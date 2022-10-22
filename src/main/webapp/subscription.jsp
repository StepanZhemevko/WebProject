<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 19.10.2022
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
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
        .form{
            text-align: center;
        }
        #my_cabinet{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 35px;
        }
    </style>
    <title>Subscription</title>
</head>
<body>
<div class="to_cabinet">
    <a id="my_cabinet" href="cabinet.jsp" >My Cabinet</a>
</div>
<hr>

<div class="form">

    <label><img src="<%= session.getAttribute("image") %>" width="100" height="100"></label>
    <p><label>Magazine name: <%=session.getAttribute("magazine_name")%></label></p>
    <p><label>Start order day: <%=session.getAttribute("begin_time")%></label></p>
    <p><label>Your id is: <%=session.getAttribute("id")%></label></p>
    <p><label>Your Balance: <%=session.getAttribute("balance")%></label></p>
    <form action="MakeOrderServlet" method="post">
        <input type="date" name="end_date" required>
        <span></span>
        <label>And date</label>
        <input type="submit" value="Submit">
    </form>

</div>
</body>
</html>
