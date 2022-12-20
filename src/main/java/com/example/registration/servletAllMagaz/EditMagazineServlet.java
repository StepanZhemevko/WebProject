package com.example.registration.servletAllMagaz;

import com.example.registration.sql.DBCPDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "EditMagazineServlet", value = "/EditMagazineServlet")
public class EditMagazineServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("magazineId"));
        String name= request.getParameter("magazineName");
        int prise= Integer.parseInt(request.getParameter("magazinePrise"));
        String description= request.getParameter("magazineDescription");
        String imageLink= request.getParameter("imageLink");
        int categoryId= Integer.parseInt(request.getParameter("magazineCat"));
        int publisherId= Integer.parseInt(request.getParameter("magazinePub"));

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DBCPDataSource.getConnection();
            Statement pst = con.createStatement();
            String sql = "update mydb.magazines set magazines_name = '"+name+"', prise = '"+prise+"', description = '"+description+"', image_link= '"+imageLink+"',categories_id = '"+categoryId+"',publishers_id='"+publisherId+"' where id ='"+id+"' ";
            pst.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("store.jsp");

       }
}
