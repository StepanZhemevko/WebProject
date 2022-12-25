package com.example.registration.servletUser;

import com.example.registration.sql.DBCPDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean block;
        HttpSession session = request.getSession();
        Connection con = null;
        if (login!=null||password!=null){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DBCPDataSource.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {

            PreparedStatement pst = con.prepareStatement("select * from mydb.user where login= ? and password = ?");
            pst.setString(1, login);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                session.setAttribute("name", rs.getString("login"));
                session.setAttribute("admin", rs.getBoolean("admin"));
                block = rs.getBoolean("block");
                session.setAttribute("block", block);
                session.setAttribute("id", rs.getInt("id"));

                request.setAttribute("status", "success");
                response.sendRedirect("cabinet.jsp");
            } else {
                request.setAttribute("status", "failed");
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        }else {
            response.sendRedirect("error_data.jsp");
        }
    }
}
