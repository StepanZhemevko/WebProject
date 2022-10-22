package com.example.registration.servletUser;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean block;
        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
        String dbUname = "root";
        String dbPassword = "011235813Steve";
        HttpSession session = request.getSession();
        //RequestDispatcher dispatcher;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
            PreparedStatement pst = con.prepareStatement("select * from mydb.user where login= ? and password = ?");

            pst.setString(1,login);
            pst.setString(2,password);

            ResultSet rs = pst.executeQuery();
            if (rs.next()){

                session.setAttribute("name",rs.getString("login"));
                session.setAttribute("admin",rs.getBoolean("admin"));
                block = rs.getBoolean("block");
                session.setAttribute("block",block);
                session.setAttribute("id",rs.getInt("id"));

                response.sendRedirect("cabinet.jsp");
                //dispatcher.
            }else {
                //dispatcher.
                response.sendRedirect("login.jsp");
                request.setAttribute("status","failed");
            }
            //dispatcher.forward(request,response);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

       /* response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<html><body>");
        out.print(request.getParameter("login"));
        out.print("</html></body>");*/
    }
}
