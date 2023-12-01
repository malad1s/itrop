package com.my.controllers;


import com.my.enity.UserEnity;
import com.my.models.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user_page")
public class UserPageController  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/user_page.jsp");
        HttpSession session = req.getSession();
        UserEnity userSession = (UserEnity) session.getAttribute("user");
        if (userSession == null) {
            resp.sendRedirect("/login");
        }else{
           rd.forward(req , resp);
        }
    }
}

