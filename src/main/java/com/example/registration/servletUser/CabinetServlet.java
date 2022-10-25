package com.example.registration.servletUser;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "CabinetServlet", value = "/CabinetServlet")
public class CabinetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/cabinet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password=null;
        String id=null;
        String nameAndSurname = null;
        String email = null;
        String telephone = null;
        boolean block;

        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
        String dbUname = "root";
        String dbPassword = "011235813Steve";
        HttpSession session = request.getSession();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
            Statement pst = con.createStatement();
            String sql ="select id,password,name_and_surname,email,telephone,block from mydb.user where login= '"+login+"'";
            ResultSet rs = pst.executeQuery(sql);

            while(rs.next())
            {
                id=rs.getString("id");
                password = rs.getString("password");
                nameAndSurname=rs.getString("name_and_surname");
                email= rs.getString("email");
                telephone= rs.getString("telephone");
                block = (boolean) session.getAttribute("block");

                session.setAttribute("id",id);
                session.setAttribute("password",password);
                session.setAttribute("name_and_surname",nameAndSurname);
                session.setAttribute("email",email);
                session.setAttribute("telephone",telephone);
                session.setAttribute("block",block);

            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("add_info.jsp");

    }

}
