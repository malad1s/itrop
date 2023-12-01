package com.my.controllers;

import com.my.dao.EventDaoImpl;
import com.my.enity.ReportAndSpeaker;
import com.my.models.EventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/offers")
public class OffersOnEventController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEvent= Integer.parseInt(req.getParameter("event"));
        String type = req.getParameter("dataType");
        req.setAttribute("event",idEvent);
        String typeChange=req.getParameter("change");
        if(typeChange!=null){
            String action=req.getParameter("act");
            String report=req.getParameter("report");
            String speaker=req.getParameter("speaker");
            new EventService().acceptOrCancelOffersOnEvent(typeChange,action,Integer.parseInt(report),Integer.parseInt(speaker));
            resp.sendRedirect("offers?event="+idEvent);
        }else{
            List<ReportAndSpeaker> offerOnFree=new EventService().getOfferOnFreeReports(idEvent);;
            List<ReportAndSpeaker> offerOnNew;
            req.setAttribute("reports", offerOnFree);
            req.setAttribute("dataType","offerOnFree");
            if(type!=null&&type.equals("offerOnNew")){
                offerOnNew  =new EventDaoImpl().getOfferOnNewReports(idEvent);
                req.setAttribute("reports",offerOnNew);
                req.setAttribute("dataType","offerOnNew");
            }
            getServletContext().getRequestDispatcher("/jsp/offersOnEvent.jsp").forward(req , resp);
        }
    }
}
