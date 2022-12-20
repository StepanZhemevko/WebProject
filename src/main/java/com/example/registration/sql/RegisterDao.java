package com.example.registration.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
    private String dbUrl = "jdbc:mysql://localhost:3306/mydb";
    private String dbUname = "root";
    private String dbPassword = "011235813Steve";
    public Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public String insert(Member member) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String result = "Ok";
        Connection connection = getConnection();
        String sql="insert into mydb.user (login,password,name_and_surname,email,telephone,admin,block) values(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getLogin());
            preparedStatement.setString(2, member.getPassword());
            preparedStatement.setString(3, member.getNameAndSurname());
            preparedStatement.setString(4, member.getEmail());
            preparedStatement.setString(5, member.getTelephone());
            preparedStatement.setBoolean(6, member.isAdmin());
            preparedStatement.setBoolean(7, member.isBlock());

           preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            result="Not Ok";
        }
return result;
    }
}
