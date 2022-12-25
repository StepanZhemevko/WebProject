package com.example.registration.servletAllMagaz;

import com.example.registration.sql.DBCPDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
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
        Connection con = null;
        Statement pst = null;


        if (magazineName!=null|| imageLink!= null||request.getParameter("prise")!=null||category!=null||publisher!=null) {
            try {
                con = DBCPDataSource.getConnection();
                pst = con.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                Class.forName("com.mysql.jdbc.Driver");

                String sqlForCategory = "SELECT id as cat_id FROM mydb.categories where category_name='" + category + "' ";
                ResultSet rs = pst.executeQuery(sqlForCategory);
                while (rs.next()) {
                    categoryId = Integer.parseInt(rs.getString("cat_id"));
                }

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
            Connection con2;
            Statement pst2;
            try {
                con2 = DBCPDataSource.getConnection();
                pst2 = con2.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                Class.forName("com.mysql.jdbc.Driver");
                String sqlForPublisher = "SELECT id as pub_id FROM mydb.publishers where publisher_name ='" + publisher + "' ";

                ResultSet rs2 = pst2.executeQuery(sqlForPublisher);
                while (rs2.next()) {
                    publisherId = Integer.parseInt(rs2.getString("pub_id"));
                }

            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    pst2.close();
                    con2.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            Connection con3 = null;
            try {
                con3 = DBCPDataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");

                String sql = "insert into mydb.magazines (magazines_name, prise, description, image_link, categories_id, publishers_id) " +
                        "values (?,?,?,?,?,?)";
                PreparedStatement preparedStatement = con3.prepareStatement(sql);
                preparedStatement.setString(1, magazineName);
                preparedStatement.setDouble(2, prise);
                preparedStatement.setString(3, description);
                preparedStatement.setString(4, imageLink);
                preparedStatement.setInt(5, categoryId);
                preparedStatement.setInt(6, publisherId);
                preparedStatement.executeUpdate();

            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    con3.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                response.sendRedirect("store.jsp");
            }
        } else {
            response.sendRedirect("error_data.jsp");
        }

    }
}
