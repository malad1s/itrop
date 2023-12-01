package com.my.filters;

import com.my.errors.ErrorPage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp=(HttpServletResponse)servletResponse;
        if(req.getMethod().equalsIgnoreCase("POST")){
            String login = req.getParameter("email");
            String password = req.getParameter("password");
            String surname = req.getParameter("surname");
            String firstname = req.getParameter("firstname");
            if(login==""||password==""||surname==""||firstname==""||password.length()<8){
                req.setAttribute("error", ErrorPage.errorUserRegistrationAll);
                req.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(req , resp);
            } else {
                filterChain.doFilter(req, resp);
            }
        }else{
            filterChain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
