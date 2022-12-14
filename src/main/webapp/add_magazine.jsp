<%@ page import="com.example.registration.sql.DBCPDataSource" %>
<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 18.10.2022
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value ="${language}" />
<fmt:setBundle basename="messages.addMagazine"/>
<html lang="${language}">
<head>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(120deg, #2980b9, #8e44ad);
            height: 70vh;
            text-align: center;
        }
        .change{
            font-family: "Times New Roman", serif;
            color: #e9f4fb;
            text-align: center;
            font-size:120%;
            font-style: oblique;
            padding: 2em;
        }

        .txt_field input{
            width: 100%;
            padding: 0 5px;
            height: 40px;
            font-size: 16px;
            border: none;
            background: none;
            outline: none;
        }
        .txt_field label{
            position: absolute;
            top: 50%;
            left: 5px;
            color: #adadad;
            transform: translateY(-50%);
            font-size: 16px;
            pointer-events: none;
            transition: .5s;
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
    <title>Add Magazines</title>
</head>
<div class="to_cabinet">
<a id="my_cabinet" href="cabinet.jsp" ><fmt:message key="addMagazine.label.my_cabinet"/></a>
<hr>
</div>
<body>
<div class="change">
    <form action="AddMagazineServlet" method="post">
        <label><fmt:message key="addMagazine.label.name"/></label>
        <p><input type="text" name="magazine_name"  required></p>
        <span></span>

        <label><fmt:message key="addMagazine.label.prise"/></label>
        <p><input type="number" name="prise" min="0" required></p>
        <span></span>

        <label><fmt:message key="addMagazine.label.descr"/></label>
        <p><input type="text" name="description"></p>
        <span></span>

        <label><fmt:message key="addMagazine.label.image"/></label>
        <p><input type="text" name="image_link" maxlength="145" required></p>
        <span></span>

        <label><fmt:message key="addMagazine.label.categories"/></label>
                <%! String driverName = "com.mysql.jdbc.Driver";%>
                <%!String url = "jdbc:mysql://localhost:3306/mydb";%>
                <%!String user = "root";%>
                <%!String psw = "011235813Steve";%>
            <%
                Connection con = null;
                PreparedStatement ps = null;
                try
                {
                    Class.forName(driverName);
                    con = DriverManager.getConnection(url,user,psw);
                    String sql = "SELECT * FROM mydb.categories";
                    ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
            %>
            <p>
                <select name="category">
                    <%
                        while(rs.next())
                        {
                            String fname = rs.getString("category_name");
                    %>
                    <option  value="<%=fname%>"><%=fname%> </option>
                    <%
                        }
                    %>
                </select>
            </p>
            <%
                }
                catch(SQLException sqe)
                {
                    out.println(sqe);
                }
            %>

        <span></span>
        <label><fmt:message key="addMagazine.label.publisher"/></label>

        <%
            Connection con2 = null;
            PreparedStatement ps2 = null;
            try
            {
                Class.forName(driverName);
                con2 = DriverManager.getConnection(url,user,psw);
                String sql2 = "SELECT * FROM mydb.publishers";
                ps2 = con2.prepareStatement(sql2);
                ResultSet rs2 = ps2.executeQuery();
        %>
        <p>
            <select name="publisher">
                <%
                    while(rs2.next())
                    {
                        String fname = rs2.getString("publisher_name");
                %>
                <option  value="<%=fname%>"><%=fname%> </option>
                <%
                    }
                %>
            </select>
        </p>
        <%
            }
            catch(SQLException sqe)
            {
                out.println(sqe);
            }
        %>
        <span></span>

        <p><input type="submit" value="<fmt:message key="addMagazine.submit"/>"></p>
    </form>
</div>
</body>
</html>
