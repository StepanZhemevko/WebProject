package com.example.registration.servletUser;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "AddInfoServlet", value = "/AddInfoServlet")
public class AddInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String login = session.getAttribute("name").toString();
        String password = request.getParameter("password");
        String name_and_surname = request.getParameter("name_and_surname");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");

        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
        String dbUname = "root";
        String dbPassword = "011235813Steve";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
            Statement pst = con.createStatement();
            String sql="update mydb.user set password='"+password+"', name_and_surname = '"+name_and_surname+"'" +
                    ",email='"+email+"',telephone='"+telephone+"' where login= '"+login+"'";

            pst.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("cabinet.jsp");

    }
}
