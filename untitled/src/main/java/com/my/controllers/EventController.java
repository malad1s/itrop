package com.my.controllers;

import com.my.enity.Event;
import com.my.enity.Report;
import com.my.enity.ReportAndSpeaker;
import com.my.enity.UserEnity;
import com.my.errors.ErrorPage;
import com.my.models.EventService;
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
import java.util.Collections;
import java.util.List;


@WebServlet("/event")
public class EventController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserEnity user = (UserEnity)session.getAttribute("user");
        String suggestS=req.getParameter("suggest_speaker");
        String idReport=req.getParameter("report");
        int idEvent=Integer.parseInt(req.getParameter("event"));
        String delete=req.getParameter("delete");
        if(delete!=null){
        new EventService().deleteEvent(idEvent);
        resp.sendRedirect("/conferences?page=1&SortByAct=1");
        }else{
            if(suggestS!=null&&idReport!=null){
                new EventService().suggestSpeaker(Integer.parseInt(idReport),Integer.parseInt(suggestS));
            }

            if(user!=null&&user.getRole().getId()==2){
                String idPin= req.getParameter("pin");
                String idUnpin= req.getParameter("unpin");
                if(idPin!=null){
                    new EventService().changePin(Integer.parseInt(idPin),1);
                }
                if(idUnpin!=null){
                    new EventService().changePin(Integer.parseInt(idUnpin),0);
                }
            }
            //получить инфу об собитии
            Event event=new EventService().getEvent(idEvent);

            // получить все топики и их спикеров
            List<ReportAndSpeaker> reports=new EventService().getReportsAndSpeakers(idEvent);
            Collections.sort(reports, ReportAndSpeaker.getComparatorCompareByEnchrined().reversed());

            // добавить страницу и тд
            req.setAttribute("event",event);
            req.setAttribute("reports",reports);

            getServletContext().getRequestDispatcher("/jsp/event.jsp").forward(req , resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idEvent=req.getParameter("idEvent");
        HttpSession session = req.getSession();
        String email=req.getParameter("email");
        UserEnity userSession = (UserEnity)session.getAttribute("user");

        if(userSession==null){
            UserEnity user=new UserService().getUser(email);
            if(user!=null) {
                if(new EventService().checkReg(Integer.parseInt(idEvent),user.getId())!=null){
                    req.setAttribute("error", ErrorPage.errorUserAlreadyRegisterOnEvent);
                    getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(req , resp);
                };
            }else{
                new UserService().createUser(email);
            }
            user=new UserService().getUser(email);
            new EventService().regUser(Integer.parseInt(idEvent),user.getId());
            EmailSend.sendEmailAboutRegistrationOnEventForUser(user,new EventService().getEvent(Integer.parseInt(idEvent)));

            resp.sendRedirect("/event?event="+idEvent);

        }else{
            //check new
            if(new EventService().checkReg(Integer.parseInt(idEvent),userSession.getId())!=null){
                req.setAttribute("error", ErrorPage.errorUserAlreadyRegisterOnEvent);
                getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(req , resp);
            }else{
                //register this
               new EventService().regUser(Integer.parseInt(idEvent),userSession.getId());
                EmailSend.sendEmailAboutRegistrationOnEventForUser(userSession,new EventService().getEvent(Integer.parseInt(idEvent)));
                resp.sendRedirect("event?event="+idEvent);
            }


        }
    }
}
