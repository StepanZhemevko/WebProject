package com.example.registration.servletUser;

import com.example.registration.sql.DBCPDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "UnblockUserServlet", value = "/UnblockUserServlet")
public class UnblockUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (request.getParameter("id")!=null){
        Connection con = null;
        Statement pst = null;
        try {
            con = DBCPDataSource.getConnection();
            pst = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String sql = "update mydb.user set block = '" + 0 + "' where id= '" + id + "'";

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
        response.sendRedirect("admin_page.jsp");
        }else{
            response.sendRedirect("error_data.jsp");
        }
    }
}
