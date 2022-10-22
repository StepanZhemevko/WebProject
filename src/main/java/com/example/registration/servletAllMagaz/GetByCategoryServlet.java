package com.example.registration.servletAllMagaz;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "GetByCategoryServlet", value = "/GetByCategoryServlet")
public class GetByCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("selected");

        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
        String dbUname = "root";
        String dbPassword = "011235813Steve";
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
            PreparedStatement pst = con.prepareStatement("select id as cat_id from mydb.categories where category_name='"+category+"'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()){

                session.setAttribute("cat_id",rs.getString("cat_id"));

                dispatcher=request.getRequestDispatcher("by_category.jsp");
            }else {

                request.setAttribute("status","failed");
                dispatcher = request.getRequestDispatcher("store.jsp");
            }
            dispatcher.forward(request,response);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

/*
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<html><body>");
        out.print(category);
        out.print("</html></body>");*/


    }
}
