package com.example.registration.Filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebFilter("/AdminServlet")
public class CheckRoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        PrintWriter out=servletResponse.getWriter();
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        boolean admin = (boolean) httpServletRequest.getSession().getAttribute("admin");

        if(admin){

            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            out.print("U are not Admin");
        }

    }

    @Override
    public void destroy() {
    }
}

