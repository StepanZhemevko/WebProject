package com.example.registration.servletAllMagaz;

import com.example.registration.sql.DBCPDataSource;
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
        String publisherName = request.getParameter("publisher_name");
        Connection con = null;
        Statement pst = null;
        if (publisherName!=null){
            try {
                con = DBCPDataSource.getConnection();
                pst = con.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                Class.forName("com.mysql.jdbc.Driver");

                String sql2 = "INSERT INTO mydb.publishers (publisher_name) VALUES ('" + publisherName + "')";

                pst.executeUpdate(sql2);

            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    pst.close();
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            response.sendRedirect("add_magazine.jsp");
        }else {
            response.sendRedirect("error_data.jsp");
        }


    }

}
