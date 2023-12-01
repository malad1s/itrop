package com.my.filters;

import com.my.enity.UserEnity;
import com.my.errors.ErrorPage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ModeratorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp=(HttpServletResponse)servletResponse;
        HttpSession session = req.getSession();
        UserEnity userSession = (UserEnity) session.getAttribute("user");
        if(userSession.getRole().getId()!=2){
            req.setAttribute("error", ErrorPage.errorNotModer);
            req.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(req , resp);
        }else{
            filterChain.doFilter(req,resp);
        }
    }
}
