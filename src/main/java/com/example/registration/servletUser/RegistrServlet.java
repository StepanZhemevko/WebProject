package com.example.registration.servletUser;

import com.example.registration.sql.Member;
import com.example.registration.sql.RegisterDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RegistrServlet", value = "/RegistrServlet")
public class RegistrServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name_and_surname = request.getParameter("name_and_surname");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");


        Member member = new Member(login,password,name_and_surname,email,telephone);

         RegisterDao registerDao = new RegisterDao();
         registerDao.insert(member);
         response.sendRedirect("login.jsp");

    }
}
