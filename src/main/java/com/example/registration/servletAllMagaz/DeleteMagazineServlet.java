package com.example.registration.servletAllMagaz;

import com.example.registration.sql.DBCPDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "DeleteMagazineServlet", value = "/DeleteMagazineServlet")
public class DeleteMagazineServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete = request.getParameter("delete");
        Connection con = null;
        Statement pst = null;
        if (delete != null) {
            try {
                con = DBCPDataSource.getConnection();
                pst = con.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                Class.forName("com.mysql.jdbc.Driver");

                String sql = "delete from mydb.magazines where id= '" + delete + "'";
                pst.executeUpdate(sql);

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
            response.sendRedirect("store.jsp?page=1");
        } else {
            response.sendRedirect("error_data.jsp");
        }

    }
}
