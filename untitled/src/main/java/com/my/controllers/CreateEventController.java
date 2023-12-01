package com.my.controllers;

import com.my.enity.Event;
import com.my.enity.UserEnity;
import com.my.errors.ErrorPage;
import com.my.models.EventService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/create_event")
public class CreateEventController  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/createEvent.jsp");
        rd.forward(req , resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String time = req.getParameter("time");
        String date = req.getParameter("date");
        String place = req.getParameter("place");
        new EventService().createEvent(new Event(name,date,place,time));
        resp.sendRedirect("/conferences?page=1&SortByAct=1");
    }
}
