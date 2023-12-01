package com.my.controllers;

import com.my.enity.Report;
import com.my.enity.UserEnity;
import com.my.errors.ErrorPage;
import com.my.models.EventService;
import com.my.models.UserService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/createReportBS")
public class CreateReportBSController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("event",req.getParameter("event"));
        getServletContext().getRequestDispatcher("/jsp/createReportBS.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserEnity userSession = (UserEnity) session.getAttribute("user");
        String topic = req.getParameter("topic");
        String event=req.getParameter("idEvent");
        new EventService().createNewReportBySpeaker(
                new Report(Integer.parseInt(event),userSession.getId(),topic,0)
        );
        resp.sendRedirect("/event?event="+event);

    }
}
