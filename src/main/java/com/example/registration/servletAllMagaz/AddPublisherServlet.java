package com.example.registration.servletAllMagaz;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "AddPublisherServlet", value = "/AddPublisherServlet")
public class AddPublisherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String publisher_name = request.getParameter("publisher_name");


        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
        String dbUname = "root";
        String dbPassword = "011235813Steve";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
            Statement pst = con.createStatement();
            String sql2 = "INSERT INTO mydb.publishers (publisher_name) VALUES ('" + publisher_name + "')";

            pst.executeUpdate(sql2);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("add_magazine.jsp");

    }

}