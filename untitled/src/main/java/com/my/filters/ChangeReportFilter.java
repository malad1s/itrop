package com.my.filters;

import com.my.errors.ErrorPage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeReportFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp=(HttpServletResponse)servletResponse;
        if(req.getMethod().equalsIgnoreCase("POST")){
            String topic = req.getParameter("topic");
            String speaker = req.getParameter("speaker");
            if(topic==""||speaker==""){
                req.setAttribute("error", ErrorPage.errorInfoReport);
                req.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(req , resp);
            } else {
                filterChain.doFilter(req, resp);
            }
        }else{
            filterChain.doFilter(req, resp);
        }
    }
}
