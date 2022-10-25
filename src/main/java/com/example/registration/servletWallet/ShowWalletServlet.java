package com.example.registration.servletWallet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "ShowWalletServlet", value = "/ShowWalletServlet")
public class ShowWalletServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
        String dbUname = "root";
        String dbPassword = "011235813Steve";
        HttpSession session = request.getSession();
        int walletId;
        double balance;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
            Statement pst = con.createStatement();
            String temp = session.getAttribute("id").toString();
            int id = Integer.parseInt(temp);
            String sql ="SELECT * FROM mydb.wallet WHERE user_id = '"+id+"'";

            ResultSet rs = pst.executeQuery(sql);
            if (rs.next()){
                walletId = rs.getInt("user_id");
                balance = rs.getDouble("balance");
                session.setAttribute("wallet_id",walletId);
                session.setAttribute("balance",balance);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("wallet.jsp");
    }
}
