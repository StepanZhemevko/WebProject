package com.example.registration.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MagazineDao {
    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DBCPDataSource.getConnection();
        }catch(Exception e){System.out.println(e);}
        return con;
    }
    public static List<Magazine> getRecords(int start, int total){
        List<Magazine> list= new ArrayList<>();
        try{
            Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "select * from magazines limit "+(start-1)+","+total);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Magazine mag=new Magazine();
                mag.setId(rs.getInt(1));
                mag.setName(rs.getString(2));
                mag.setPrise((rs.getDouble(3)));
                mag.setDescription(rs.getString(4));
                mag.setImageLink(rs.getString(5));
                mag.setCatId(rs.getInt(6));
                mag.setPubId(rs.getInt(7));
                list.add(mag);
            }
            con.close();
        }catch(Exception e){System.out.println(e);}
        return list;
    }


}
