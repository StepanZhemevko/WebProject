package com.example.registration.servletWallet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "WalletServlet", value = "/WalletServlet")
public class WalletServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/wallet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
        String dbUname = "root";
        String dbPassword = "011235813Steve";
        HttpSession session = request.getSession();
        int count;
        String temp = session.getAttribute("id").toString();
        int id = Integer.parseInt(temp);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
            Statement pst = con.createStatement();
            String sql ="SELECT COUNT(user_id) as cnt FROM mydb.wallet WHERE user_id = '"+id+"'";
            ResultSet rs = pst.executeQuery(sql);
            while(rs.next()) {
                count = Integer.parseInt(rs.getString("cnt"));
                if (count==0){
                    response.sendRedirect("create_wallet.jsp");
                }
                else {
                    response.sendRedirect("wallet.jsp");
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
