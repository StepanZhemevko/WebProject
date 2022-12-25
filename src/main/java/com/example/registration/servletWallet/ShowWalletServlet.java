package com.example.registration.servletWallet;

import com.example.registration.sql.DBCPDataSource;
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
        HttpSession session = request.getSession();
        int walletId;
        double balance;
        String temp = session.getAttribute("id").toString();
        int id = Integer.parseInt(temp);
        if (session.getAttribute("id") != null) {
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


                String sql = "SELECT * FROM mydb.wallet WHERE user_id = '" + id + "'";

                ResultSet rs = pst.executeQuery(sql);
                if (rs.next()) {
                    walletId = rs.getInt("user_id");
                    balance = rs.getDouble("balance");
                    session.setAttribute("walletId", walletId);
                    session.setAttribute("balance", balance);
                }

            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    pst.close();
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                response.sendRedirect("wallet.jsp");
            }
        }else {
            response.sendRedirect("error_data.jsp");
        }
    }
}