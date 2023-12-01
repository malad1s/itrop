package com.my.controllers;

import com.my.enity.UserEnity;
import com.my.models.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user_update")
public class ChangeUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserEnity user = (UserEnity) session.getAttribute("user");
        getServletContext().getRequestDispatcher("/jsp/userUpdate.jsp").forward(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserEnity user = (UserEnity) session.getAttribute("user");
        int idUser=user.getId();
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        String surname = req.getParameter("surname");
        String firstname = req.getParameter("firstname");
        UserEnity newUser=new UserService().updateUser(idUser,surname,firstname,login,password);
        session.setAttribute("user",newUser);
        resp.sendRedirect("/user_page");
    }
}
