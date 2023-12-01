package com.my.controllers;

import com.my.enity.Role;
import com.my.enity.UserEnity;
import com.my.errors.ErrorPage;
import com.my.models.UserService;
import com.my.utils.EmailSend;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jsp/registerPage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        String surname = req.getParameter("surname");
        String firstname = req.getParameter("firstname");
        UserEnity user= new UserService().register(new UserEnity(0,surname,firstname,login,password,new Role(1)));
        if(user!=null){
            HttpSession session = req.getSession();
            UserEnity userSession = (UserEnity) session.getAttribute("user");
            if (userSession == null) {
                userSession=user;
            }
            session.setAttribute("user", userSession);
            user.setPassword(password);
            EmailSend.sendEmailAboutRegistrationOnSiteForUser(user);
            resp.sendRedirect("/user_page");
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/error.jsp");
            req.setAttribute("error", ErrorPage.errorUserRegistration);
            rd.forward(req , resp);
        }
    }
}
