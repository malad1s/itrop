package com.my.controllers;

import com.my.enity.Report;
import com.my.enity.UserEnity;
import com.my.errors.ErrorPage;
import com.my.models.EventService;
import com.my.models.UserService;
import com.my.utils.EmailSend;
import org.apache.catalina.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@WebServlet("/createReport")
public class CreateReportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getAttribute("event");
        List<UserEnity> speakers=new UserService().getSpeakers();
        req.setAttribute("speakers", speakers);
        getServletContext().getRequestDispatcher("/jsp/createReport.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topic = req.getParameter("topic");
        String id_speaker=req.getParameter("speaker");
        System.out.println(id_speaker);
        String idEvent=req.getParameter("idEvent") ;
        new EventService().createReport(
                new Report(
                        Integer.parseInt(idEvent),
                        Integer.parseInt(id_speaker),
                        topic,
                        0
                ));
        EmailSend.sendEmailAboutOfferReportsForSpeaker(
                new UserService().getUser(Integer.parseInt(id_speaker)),
                new EventService().getEvent(Integer.parseInt(idEvent)));
        resp.sendRedirect("/event?event="+idEvent);
    }
}
