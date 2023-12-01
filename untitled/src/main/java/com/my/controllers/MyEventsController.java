package com.my.controllers;

import com.my.enity.Event;
import com.my.enity.EventAndReports;
import com.my.enity.UserEnity;
import com.my.models.EventService;
import com.my.models.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet("/my_conferences")
public class MyEventsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserEnity userSession = (UserEnity) session.getAttribute("user");
        String idEvent= req.getParameter("event");
        String unsubscribing= req.getParameter("unsubscribing");

        //if user get events
        List<Event>  events=new ArrayList<>();
        List<EventAndReports> eventNReports=new ArrayList<>();
        if(userSession.getRole().getId()==1){
            if(unsubscribing!=null&&idEvent!=null&&Integer.parseInt(unsubscribing)==1){
                //del from users_events
                new UserService().unsubscribingUserFromEvent(Integer.parseInt(idEvent),userSession.getId());
            }
            events =new UserService().getEventsForUser(userSession.getId());

        }else if(userSession.getRole().getId()==3){
            eventNReports= new EventService().getEventAndReports(userSession.getId());
        }
        req.setAttribute("Events",events);
        req.setAttribute("EventNReports",eventNReports);
        getServletContext().getRequestDispatcher("/jsp/myConferences.jsp").forward(req , resp);
        //if speaker get events+ report where ge was

    }
}
