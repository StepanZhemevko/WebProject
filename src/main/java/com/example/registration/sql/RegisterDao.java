package com.example.registration.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
    public boolean insert(Member member) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        try {
            connection = DBCPDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            int result = preparedStatement.executeUpdate();

            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
