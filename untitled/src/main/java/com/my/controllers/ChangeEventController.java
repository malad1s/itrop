package com.my.controllers;

import com.my.enity.Event;
import com.my.models.EventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changeEvent")
public class ChangeEventController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idEvent= req.getParameter("event");
        //get event
        Event event=new EventService().getEvent(Integer.parseInt(idEvent));
        //take event in page
        req.setAttribute("event",event);
        getServletContext().getRequestDispatcher("/jsp/changeEvent.jsp").forward(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idEvent=req.getParameter("idEvent");
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String place = req.getParameter("place");
        String time = req.getParameter("time");
        new EventService().updateEvent(new Event(Integer.parseInt(idEvent),name,date,place,0,time));
        //email
        resp.sendRedirect("/event?event="+idEvent);

    }
}
