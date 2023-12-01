package com.my.controllers;

import com.my.enity.Event;
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
import java.util.List;

@WebServlet("/changeReport")
public class ChangeReportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idReport= req.getParameter("report");
        String idEvent= req.getParameter("event");

        List<UserEnity> speakers=new UserService().getSpeakers();
        //get event
        Report report=new EventService().getReport(Integer.parseInt(idReport));
        //take event in page
        req.setAttribute("report",report);
        req.setAttribute("speakers", speakers);
        req.setAttribute("event",idEvent);
        getServletContext().getRequestDispatcher("/jsp/changeReport.jsp").forward(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String topic = req.getParameter("topic");
        String speaker = req.getParameter("speaker");
        String idReport = req.getParameter("idReport");
        // change report
        System.out.println(topic);

        new EventService().updateReport(
                new Report(
                        Integer.parseInt(idReport),
                        Integer.parseInt(speaker),
                        topic));
        resp.sendRedirect("/event?event="+req.getParameter("event"));
        //email


    }


}
