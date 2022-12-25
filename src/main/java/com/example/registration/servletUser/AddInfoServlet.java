package com.example.registration.servletUser;

import com.example.registration.sql.DBCPDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "AddInfoServlet", value = "/AddInfoServlet")
public class AddInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String login = session.getAttribute("name").toString();
        String password = request.getParameter("password");
        String nameAndSurname = request.getParameter("nameAndSurname");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");

        Connection con = null;
        Statement pst = null;
        if (session.getAttribute("name")!=null||request.getParameter("password")!=null||request.getParameter("nameAndSurname")!=null||
                request.getParameter("email")!=null||request.getParameter("telephone")!=null){
        try {
            con = DBCPDataSource.getConnection();
            pst = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String sql = "update mydb.user set password='" + password + "', name_and_surname = '" + nameAndSurname + "'" +
                    ",email='" + email + "',telephone='" + telephone + "' where login= '" + login + "'";

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
        response.sendRedirect("cabinet.jsp");
        }else {
            response.sendRedirect("error_data.jsp");
        }
    }
}
