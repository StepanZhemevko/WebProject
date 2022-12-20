package com.example.registration.servletAllMagaz;

import com.example.registration.sql.DBCPDataSource;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        String timeNow = formatter.format(startDate);

        String endDate = request.getParameter("end_date");


        int userId= (int) session.getAttribute("id");
        String temp =  session.getAttribute("magazine_id").toString();
        int magazineId = Integer.parseInt(temp);
        double magazinePrise = Double.parseDouble(session.getAttribute("magazine_prise").toString());
        String temp2 =  session.getAttribute("balance").toString();
        double newBalance = Double.parseDouble(temp2);
        newBalance  = newBalance  - magazinePrise;


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DBCPDataSource.getConnection();
            String sql="INSERT INTO mydb.subscription (start_date, finish_date, status, user_id, magazines_id) " +
                    "VALUES ('"+timeNow+"','"+endDate+"','"+ OrderStatus.ACTIVE+"','"+userId+"','"+magazineId+"')";

            String sql2 = "update mydb.wallet set balance = '"+newBalance+"' where user_id= '"+userId+"'";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.executeUpdate();
            pst.executeUpdate(sql2);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("cabinet.jsp");
        }
}
