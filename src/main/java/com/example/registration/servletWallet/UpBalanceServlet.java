package com.example.registration.servletWallet;

import com.example.registration.sql.DBCPDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "UpBalanceServlet", value = "/UpBalanceServlet")
public class UpBalanceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        double newBalance = Double.parseDouble(request.getParameter("amount"));
        int walletId = (int) session.getAttribute("id");
        double balance = (double) session.getAttribute("balance") ;
        balance = balance+ newBalance;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DBCPDataSource.getConnection();
            Statement pst = con.createStatement();
            String sql = "update mydb.wallet set balance='" + balance + "' where user_id='" + walletId + "'";

            pst.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("wallet.jsp");
    }
}
