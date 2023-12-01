package com.my.controllers;

import com.my.enity.UserEnity;
import com.my.errors.ErrorPage;
import com.my.models.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pwd = req.getParameter("password");
        UserEnity user=new UserService().login(login,pwd);
        if(user!=null){
            HttpSession session = req.getSession();
            UserEnity userSession = (UserEnity) session.getAttribute("user");
            if (userSession == null) {
                userSession=user;
            }
            session.setAttribute("user", userSession);
            resp.sendRedirect("/user_page");
        }else {
            req.setAttribute("error", ErrorPage.errorUserLogIn);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/error.jsp");
            rd.forward(req , resp);
        }
    }
}
