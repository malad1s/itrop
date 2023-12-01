package com.my.filters;

import com.my.enity.UserEnity;
import com.my.errors.ErrorPage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp=(HttpServletResponse)servletResponse;
        HttpSession session = req.getSession();
        UserEnity userSession = (UserEnity) session.getAttribute("user");
        if (userSession == null) {
            req.setAttribute("error", ErrorPage.errorAuth);
            req.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(req , resp);
        }else{
            filterChain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
