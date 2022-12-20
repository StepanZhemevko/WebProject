package com.example.registration.servletUser;
import com.example.registration.sql.DBCPDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
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
        String password;
        String id;
        String nameAndSurname;
        String email ;
        String telephone;
        boolean block;
        HttpSession session = request.getSession();
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
                session.setAttribute("nameAndSurname",nameAndSurname);
                session.setAttribute("email",email);
                session.setAttribute("telephone",telephone);
                session.setAttribute("block",block);
            }
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

        response.sendRedirect("add_info.jsp");

    }

}
