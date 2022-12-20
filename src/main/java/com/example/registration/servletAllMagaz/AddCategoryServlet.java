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

@WebServlet(name = "AddCategoryServlet", value = "/AddCategoryServlet")
public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String categoryName = request.getParameter("category_mame");


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DBCPDataSource.getConnection();
            Statement pst = con.createStatement();

            String sql = "INSERT INTO mydb.categories (category_name) VALUES ('" + categoryName + "')";

            pst.executeUpdate(sql);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("add_magazine.jsp");

    }
}
