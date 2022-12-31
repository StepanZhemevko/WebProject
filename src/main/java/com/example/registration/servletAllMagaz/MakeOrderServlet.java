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


        int userId = (int) session.getAttribute("id");
        int magazineId = Integer.parseInt((String) session.getAttribute("magazineId"));
        double magazinePrise = Double.parseDouble(session.getAttribute("magazinePrise").toString());
        double newBalance = Double.parseDouble(session.getAttribute("balance").toString());
        newBalance = newBalance - magazinePrise;

        Connection con = null;
        if (session.getAttribute("id") != null || session.getAttribute("magazineId") != null ||
                session.getAttribute("magazinePrise") != null || session.getAttribute("balance") != null) {
            if(newBalance>=0) {
                try {
                    con = DBCPDataSource.getConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Class.forName("com.mysql.jdbc.Driver");

                    String sql = "INSERT INTO mydb.subscription (start_date, finish_date, status, user_id, magazines_id) " +
                            "VALUES ('" + timeNow + "','" + endDate + "','" + OrderStatus.ACTIVE + "','" + userId + "','" + magazineId + "')";

                    String sql2 = "update mydb.wallet set balance = '" + newBalance + "' where user_id= '" + userId + "'";
                    PreparedStatement pst = con.prepareStatement(sql);

                    pst.executeUpdate();
                    pst.executeUpdate(sql2);
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                response.sendRedirect("cabinet.jsp");
            }else {
                    response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("error_data.jsp");
        }
    }
}