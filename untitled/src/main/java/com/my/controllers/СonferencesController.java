package com.my.controllers;


import com.my.Patterns.SortEvents.SortByDateActual;
import com.my.Patterns.SortEvents.SortByDateNonActual;
import com.my.Patterns.SortEvents.StrategyCllientSortEvents;
import com.my.enity.Event;
import com.my.models.EventService;
import com.my.utils.Sort;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/conferences")
public class СonferencesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/conferences.jsp");
        List<Event> list=(new EventService().getAllEvents());
        req.setAttribute("listOfSort", Sort.getListSortForEvent());
        String sort=req.getParameter("SortSelect");
        String sortByAct=req.getParameter("SortByAct");
        String page=req.getParameter("page");
        if(sortByAct!=null){
            int sortInt=Integer.parseInt(sortByAct);
            StrategyCllientSortEvents sortedTool=new StrategyCllientSortEvents();
            if(sortInt==1){
                sortedTool.setStrategy(new SortByDateActual());
            }else if(sortInt==2){
                sortedTool.setStrategy(new SortByDateNonActual());
            }
            sortedTool.executeStrategy(list);
            req.setAttribute("sortByAct",sortInt);
        }
        if(sort!=null){
            int sortInt=Integer.parseInt(sort);
            if(sortInt==1){
                Collections.sort(list,Event.getComparatorCompareByDate());
            }else if(sortInt==2){
                Collections.sort(list,Event.getComparatorCompareByDate().reversed());
            }else if(sortInt==3){
                Collections.sort(list,Event.getComparatorCompareByReports().reversed());
            }else if(sortInt==4){
                Collections.sort(list,Event.getComparatorCompareByReports());
            }else if(sortInt==5){
                Collections.sort(list,Event.getComparatorCompareByParticipants().reversed());
            }else if(sortInt==6){
                Collections.sort(list,Event.getComparatorCompareByParticipants());
            }
        }
        if(page!=null){
            int countEvenOnOnePage=8;
            Integer size=(list.size()/countEvenOnOnePage);
            if(list.size()%countEvenOnOnePage>0){
                size+=1;
            }
            req.setAttribute("numberPage",page);
            req.setAttribute("sizePagination",size);
            int fromIndex=(Integer.parseInt(page)-1)*countEvenOnOnePage;
            int toIndex=Integer.parseInt(page)*countEvenOnOnePage;
            if(toIndex>list.size()){
                toIndex=list.size();
            }
            if(fromIndex>list.size()){
                toIndex=0;
            }
            list=list.subList(fromIndex,toIndex);
        }
        req.setAttribute("list",list);
        rd.forward(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
