package com.example.registration.servletAllMagaz;

import com.example.registration.sql.OrderStatus;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "MakeOrderServlet", value = "/MakeOrderServlet")
public class MakeOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "ok";
        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
        String dbUname = "root";
        String dbPassword = "011235813Steve";

        HttpSession session = request.getSession();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date start_date = new Date();
        String time_now = formatter.format(start_date);

        String end_date = request.getParameter("end_date");



        int user_id= (int) session.getAttribute("id");
        String temp =  session.getAttribute("magazine_id").toString();
        int magazine_id = Integer.parseInt(temp);
        double magazine_prise = Double.parseDouble(session.getAttribute("magazine_prise").toString());
        String temp2 =  session.getAttribute("balance").toString();
        double new_balance = Double.parseDouble(temp2);
        new_balance = new_balance - magazine_prise;

     /*  PrintWriter out = response.getWriter();
        out.print("<html><body>");
        out.print(user_id+" " + magazine_id+" "+ time_now);
        out.print("</html></body>");*/

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
            String sql="INSERT INTO mydb.subscription (start_date, finish_date, status, user_id, magazines_id) " +
                    "VALUES ('"+time_now+"','"+end_date+"','"+ OrderStatus.ACTIVE+"','"+user_id+"','"+magazine_id+"')";

            String sql2 = "update mydb.wallet set balance = '"+new_balance+"' where user_id= '"+user_id+"'";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.executeUpdate();
            pst.executeUpdate(sql2);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("cabinet.jsp");
        }
}