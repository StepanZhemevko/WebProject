package com.example.registration.servletUser;

import com.example.registration.sql.DBCPDataSource;
import com.example.registration.sql.Member;
import com.example.registration.sql.RegisterDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "RegistrServlet", value = "/RegistrServlet")
public class RegistrServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String nameAndSurname = request.getParameter("name_and_surname");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        Connection con = null;
        PreparedStatement stat = null;
        ResultSet rst = null;
        if (login!=null|| password!=null|| nameAndSurname!=null|| email!=null|| telephone!=null) {

            String query = "SELECT login FROM mydb.user where login = ?";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DBCPDataSource.getConnection();
                stat = con.prepareStatement(query);
                stat.setString(1, login);
                rst = stat.executeQuery();
                if (rst.next()) {
                    response.sendRedirect("login.jsp");
                } else {
                    assert password != null;
                    if (password.length() < 8 || telephone.length() < 10) {
                        response.sendRedirect("registration.jsp");
                    } else {
                        Member member = new Member(login, password, nameAndSurname, email, telephone);
                        RegisterDao registerDao = new RegisterDao();
                        registerDao.insert(member);
                        response.sendRedirect("login.jsp");
                    }
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (rst != null || stat != null || con != null) {
                    try {
                        assert rst != null;
                        rst.close();
                        stat.close();
                        con.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }else {
            response.sendRedirect("error_data.jsp");
        }
    }
}
