package com.example.registration.servletAllMagaz;

import com.example.registration.sql.DBCPDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "GetByCategoryServlet", value = "/GetByCategoryServlet")
public class GetByCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        Connection con;
        if (category != null) {
            try {
                con = DBCPDataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                PreparedStatement pst = con.prepareStatement("select id as cat_id from mydb.categories where category_name='" + category + "'");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {

                    session.setAttribute("cat_id", rs.getString("cat_id"));

                    dispatcher = request.getRequestDispatcher("by_category.jsp");
                } else {

                    request.setAttribute("status", "failed");
                    dispatcher = request.getRequestDispatcher("store.jsp");
                }
                dispatcher.forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            response.sendRedirect("error_data.jsp");
        }

    }
}
