package com.example.registration.servletWallet;

import com.example.registration.sql.DBCPDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "CreateWalletServlet", value = "/CreateWalletServlet")
public class CreateWalletServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int walletId = (int) session.getAttribute("id");
        double balance = 0.0;
        if (session.getAttribute("id") !=null){
        Connection con;
        PreparedStatement preparedStatement;
        try {
            con = DBCPDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String sql = "INSERT INTO mydb.wallet (user_id, balance) VALUES ('" + walletId + "','" + balance + "')";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.executeUpdate();
            response.sendRedirect("wallet.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {

                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        }else {
            response.sendRedirect("error_data.jsp");
        }
    }
}
