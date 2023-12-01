package com.my.filters;

import com.my.errors.ErrorPage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeEventFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp=(HttpServletResponse)servletResponse;
        if(req.getMethod().equalsIgnoreCase("POST")){
            String name = req.getParameter("name");
            String date = req.getParameter("date");
            String place = req.getParameter("place");
            String time = req.getParameter("time");
            if(name==""||date==""||place==""||time==""){
                req.setAttribute("error", ErrorPage.errorInfoEvent);
                req.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(req , resp);
            } else {
                filterChain.doFilter(req, resp);
            }
        }else{
            filterChain.doFilter(req, resp);
        }
    }
}
