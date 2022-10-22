package com.example.registration.servletAllMagaz;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "MakeSubscriptionServlet", value = "/MakeSubscriptionServlet")
public class MakeSubscriptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String time = formatter.format(date);
        session.setAttribute("magazine_id",request.getParameter("magazineId"));
        session.setAttribute("begin_time",time);
        session.setAttribute("magazine_name",request.getParameter("magazineName"));
        session.setAttribute("image",request.getParameter("image"));
        session.setAttribute("magazine_prise",request.getParameter("magazinePrise"));
        response.sendRedirect("subscription.jsp");

    }
}
