package com.my.controllers;

import com.my.enity.Event;
import com.my.enity.UserEnity;
import com.my.models.EventService;
import com.my.utils.PDFResult;
import org.apache.tomcat.jni.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/eventPresence")
public class EventPresenceController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idEvent= req.getParameter("event");
        String idUser= req.getParameter("user");
        String presence= req.getParameter("presence");
        if(idUser!=null&&presence!=null){
            new EventService().presenceUser(
                    Integer.parseInt(idEvent),
                    Integer.parseInt(idUser),
                    Integer.parseInt(presence));
            resp.sendRedirect("/eventPresence?event="+idEvent);
        }else{
            List<UserEnity> participants= new EventService().getParticipants(Integer.parseInt(idEvent));
            req.setAttribute("participants",participants);
            req.setAttribute("event",idEvent);
            getServletContext().getRequestDispatcher("/jsp/eventPresence.jsp").forward(req , resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idEvent= req.getParameter("idEvent");
        PDFResult.createUsersPresenceInPDF(
                new EventService().getEvent(Integer.parseInt(idEvent)),
                new EventService().getParticipants(Integer.parseInt(idEvent)));
        resp.setContentType("application/pdf");
        resp.setHeader("Content-Type", "application/pdf");
        File filePDF = new File("./pdf/Event"+idEvent+"Presence.pdf");
        FileInputStream fis = new FileInputStream(filePDF);
        OutputStream os = resp.getOutputStream();
        try {
            resp.setContentLength((int) filePDF.length());
            int byteRead = 0;
            while ((byteRead = fis.read()) != -1) {
                os.write(byteRead);
            }
            os.flush();
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }
        finally {
            os.close();
            fis.close();
        }
        //resp.sendRedirect("/eventPresence?event="+idEvent);
    }
}
