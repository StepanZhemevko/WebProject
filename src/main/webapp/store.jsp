<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.registration.sql.Magazine" %>
<%@ page import="com.example.registration.sql.MagazineDao" %><%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 11.10.2022
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
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

        .to_cabinet {
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

        a {
            font-size: 25px;
            color: azure;
            text-decoration: none;
        }

        #pagination{
            text-align: center;
        }
       .center{
           margin-left: auto;
           margin-right: auto;
        }
        #search{
            text-align: center;
        }
        #search2{
            text-align: center;
        }
        #magazines{
            text-align: center;
        }
        #my_cabinet {

            color: azure;
            text-decoration: none;
            font-size: 35px;
        }

        form {
            margin: 1em;
        }
    </style>
    <title>Store</title>
</head>
<body>
<div class="to_cabinet">
    <a id="my_cabinet" href="cabinet.jsp"><fmt:message key="store.label.my_cabinet"/></a>
</div>
<hr>
<div class="sort_link"><a id="byT" href="by_title.jsp"><fmt:message key="store.label.sort_name"/></a>

    <a id="byP" href="by_prise.jsp"><fmt:message key="store.label.sort_prise"/></a>
</div>
<hr>
<table class="center" border="1" width="60%" >
    <tr>
        <td><b><fmt:message key="store.label.mag#"/></b></td>
        <td><b><fmt:message key="store.label.name"/></b></td>
        <td><b><fmt:message key="store.label.prise"/></b></td>
        <td><b><fmt:message key="store.label.descr"/></b></td>
        <td><b><fmt:message key="store.label.image"/></b></td>
        <td><b><fmt:message key="store.label.category"/></b></td>
        <td><b><fmt:message key="store.label.publisher"/></b></td>
    </tr>
    <%
        String spageid = request.getParameter("page");
        int pageid = Integer.parseInt(spageid);
        int total = 3;
        if (pageid == 1) {
        } else {
            pageid = pageid - 1;
            pageid = pageid * total + 1;
        }
        List<Magazine> list = MagazineDao.getRecords(pageid, total);


        for (Magazine e : list) {
    %>
    <tr>
        <td><%=e.getId()%>
        </td>
        <td><%=e.getName() %>
        </td>
        <td><%=e.getPrise()%>
        </td>
        <td><%=e.getDescription()%>
        </td>
        <td><img src="<%=e.getImageLink()%>" width="100" height="100"></td>
        <td><%=e.getCatId()%>
        </td>
        <td><%=e.getPubId() %>
        </td>
        <td>
            <form action="MakeSubscriptionServlet" method="post">
                <input type="hidden" name="magazineId" value="<%= e.getId()%>"/>
                <input type="hidden" name="magazineName" value="<%=e.getName()%>"/>
                <input type="hidden" name="image" value="<%= e.getImageLink()%>"/>
                <input type="hidden" name="magazinePrise" value="<%= e.getPrise()%>"/>

                <input type="submit" value="<fmt:message key="store.submit.order"/>" name="order">
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<p id="pagination">
    <a href="store.jsp?page=1">1</a>
    <a href="store.jsp?page=2">2</a>
    <a href="store.jsp?page=3">3</a>
</p>
<hr>
<h5 style="text-align: center">search by category</h5>
<form action="GetByCategoryServlet" method="post" id="search2">


    <%! String driverName = "com.mysql.jdbc.Driver";%>
    <%!String url = "jdbc:mysql://localhost:3306/mydb";%>
    <%!String user = "root";%>
    <%!String psw = "011235813Steve";%>
    <%
        Connection con2 = null;
        PreparedStatement ps = null;
        try {
            Class.forName(driverName);
            con2 = DriverManager.getConnection(url, user, psw);
            String sql = "SELECT * FROM mydb.categories";
            ps = con2.prepareStatement(sql);
            ResultSet rs2 = ps.executeQuery();
    %>
    <p>
        <select name="category">
            <%
                while (rs2.next()) {
                    String fname = rs2.getString("category_name");
            %>
            <option value="<%=fname%>"><%=fname%>
            </option>
            <%
                }
            %>
        </select>
    </p>
    <%
        } catch (SQLException sqe) {
            out.println(sqe);
        }
    %>
    <input type="submit" value="<fmt:message key="store.submit.submit"/>" name="from_category">
</form>
<hr>
<h5 style="text-align: center">search by name</h5>
<form action="SearchByNameServlet" method="post" id="search">
    <input type="text" name="select_name" required>
    <input type="submit" value="<fmt:message key="store.submit.search"/>" name="search">
</form>

</body>
</html>
