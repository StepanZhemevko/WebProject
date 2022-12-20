package com.example.registration.servletUser;

import com.example.registration.sql.DBCPDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "BlockUserServlet", value = "/BlockUserServlet")
public class BlockUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Connection con;
        Statement pst;
        try {
            con = DBCPDataSource.getConnection();
            pst = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sql="update mydb.user set block = '"+1+"' where id= '"+id+"'";
            pst.executeUpdate(sql);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        response.sendRedirect("admin_page.jsp");
    }
}
