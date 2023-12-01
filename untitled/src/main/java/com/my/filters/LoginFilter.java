package com.my.filters;

import com.my.enity.UserEnity;
import com.my.errors.ErrorPage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp=(HttpServletResponse)servletResponse;
        if(req.getMethod().equalsIgnoreCase("POST")){
            String login = req.getParameter("login");
            String pwd = req.getParameter("password");
            if(login==""||pwd==""||pwd.length()<8){
                req.setAttribute("error", ErrorPage.errorUserLogInALL);
                req.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(req , resp);
            }else {
                filterChain.doFilter(req, resp);
            }
        }else{
            HttpSession session = req.getSession();
            UserEnity user = (UserEnity) session.getAttribute("user");
            if (user != null) {
                resp.sendRedirect("/user_page");
            }else{
                filterChain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
