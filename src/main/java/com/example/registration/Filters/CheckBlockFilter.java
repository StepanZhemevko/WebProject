package com.example.registration.Filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/CabinetServlet","/WalletServlet","/StoreServlet"})

public class CheckBlockFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        PrintWriter out=servletResponse.getWriter();
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        boolean block = (boolean) httpServletRequest.getSession().getAttribute("block");

        if(!block){

            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            out.print("U are Blocked by admin");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}