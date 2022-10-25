package com.example.registration.servletAllMagaz;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "AddMagazineServlet", value = "/AddMagazineServlet")
public class AddMagazineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String magazineName = request.getParameter("magazine_name");
        double prise = Double.parseDouble(request.getParameter("prise"));
        String description = request.getParameter("description");
        String imageLink = request.getParameter("image_link");
        int categoryId = 0;
        int publisherId = 0;
        String category = request.getParameter("category");
        String publisher = request.getParameter("publisher");

        HttpSession session = request.getSession();

        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
        String dbUname = "root";
        String dbPassword = "011235813Steve";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
            Statement pst = con.createStatement();
            String sqlForCategory =  "SELECT id as cat_id FROM mydb.categories where category_name='"+category+"' ";
            ResultSet rs = pst.executeQuery(sqlForCategory);
            while(rs.next()) {
                categoryId = Integer.parseInt(rs.getString("cat_id"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
            Statement pst = con.createStatement();

             String sqlForPublisher =  "SELECT id as pub_id FROM mydb.publishers where publisher_name ='"+publisher+"' ";

            ResultSet rs2 = pst.executeQuery(sqlForPublisher);
            while(rs2.next()) {
                publisherId = Integer.parseInt(rs2.getString("pub_id"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
            String sql="insert into mydb.magazines (magazines_name, prise, description, image_link, categories_id, publishers_id) " +
                    "values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, magazineName);
            preparedStatement.setDouble(2, prise);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, imageLink);
            preparedStatement.setInt(5, categoryId);
            preparedStatement.setInt(6, publisherId);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("store.jsp");
    }
}
