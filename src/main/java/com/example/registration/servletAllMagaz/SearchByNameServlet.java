package com.example.registration.servletAllMagaz;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SearchByNameServlet", value = "/SearchByNameServlet")
public class SearchByNameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("select_name");
        HttpSession session = request.getSession();
        session.setAttribute("mag_name",name);
        response.sendRedirect("by_name.jsp");
    }
}
