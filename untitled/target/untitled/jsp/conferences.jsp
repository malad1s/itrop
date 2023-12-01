
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 01.10.2022
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.my.enity.Event" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<% List<Event> events= (List<Event>)request.getAttribute("list");
Map<Integer,String> listOfSort =(Map<Integer,String>)request.getAttribute("listOfSort");
Integer countPage= (Integer)request.getAttribute("sizePagination");
String numberPage= (String) request.getAttribute("numberPage");
Integer sortByAct= (Integer) request.getAttribute("sortByAct");
 String sortText="";
    if(request.getParameter("SortSelect")!=null){sortText="&SortSelect="+request.getParameter("SortSelect");}
%>

<html>
<head>
    <link rel="stylesheet" media="screen" href="https://d3j1gkwxfnvq13.cloudfront.net/assets/webpack/bundle-events_page_css-dd4f635048adc8ac9cf6.css">
    <title>Conferences</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body >
<%@ include file="/jsp/header.jsp" %>
<div class="conteiner mt-5" >
    <h1 style="margin-left: 48px">Conferences</h1>
    <form>
        <div class="row g-5">
            <div class="evnt-events-tabs-nav">
                <div class="form-group mt-5 mb-5 px-5" >
                    <form method="post" >
                        <label for="SortSelect">Сортування</label>
                        <select type="text" class="form-control" id="SortSelect" name="SortSelect" style="margin-bottom: 10px; margin-top: 10px" >
                            <%for (Map.Entry<Integer, String> entry : listOfSort.entrySet()){
                            if(request.getParameter("SortSelect")!=null&&entry.getKey()==Integer.parseInt(request.getParameter("SortSelect"))){%>
                            <option selected="selected" value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                            <%} else{%>
                            <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                            <%}}%>
                        </select>
                        <button type="submit" class="btn btn-warning btn-outline-light">Sort</button>
                    </form>

                </div>
                <ul class="evnt-tabs-list nav nav-tabs">
                    <li class="evnt-tab-item nav-item">
                        <%if(request.getParameter("SortByAct")!=null&&Integer.parseInt(request.getParameter("SortByAct"))==1){%>
                        <a class="evnt-tab-link nav-link active" href="/conferences?SortByAct=1<%=sortText%>&page=1">
                            <span class="evnt-tab-text desktop">Upcoming events</span>
                        </a>
                        <%} else {%>
                        <a class="evnt-tab-link nav-link" href="/conferences?SortByAct=1<%=sortText%>&page=1">
                            <span class="evnt-tab-text desktop">Upcoming events</span>
                        </a>
                        <%}%>
                    </li>
                    <li class="evnt-tab-item nav-item">
                        <%if(request.getParameter("SortByAct")!=null&&Integer.parseInt(request.getParameter("SortByAct"))==2){%>
                        <a class="evnt-tab-link nav-link active" href="/conferences?SortByAct=2<%=sortText%>&page=1">
                            <span class="evnt-tab-text desktop">Past Events</span>
                        </a>
                        <%} else{%>
                        <a class="evnt-tab-link nav-link " href="/conferences?SortByAct=2<%=sortText%>&page=1">
                            <span class="evnt-tab-text desktop">Past Events</span>
                        </a>
                        <%}%>
                </li>
                </ul>
            </div>
            <div class="evnt-events-row">
                <%for (Event event: events) {%>
                <div class="evnt-events-column cell-3">
                    <div class="evnt-event-card size-m">
                        <a href="/event?event=<%=event.getId()%>">
                            <div class="evnt-card-wrapper">
                                <div class="evnt-background-container">
                                    <div class="evnt-background">

                                    </div>
                                </div>
                                <div class="evnt-card-heading">
                                    <div class="evnt-event-details-table">
                                        <div class="evnt-details-cell date-cell">
                                            <p><%=event.getDate()%> <%=event.getTime()%></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="evnt-card-body">
                                    <div class="evnt-card-cell">
                                        <div class="evnt-card-cell status-cell">
                                            <span class="undefined"></span>
                                        </div>
                                        <div class="evnt-card-cell event-name-cell">
                                            <div class="evnt-event-name">
                                                <h2 data-original-title="" title=""><%=event.getName()%></h2>
                                                <h3 data-original-title="" title=""><span class="evnt-language"><%=event.getPlace()%></span></h3>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
                <%}%>
            </div>
            <div class="form-group mt-5 mb-5 px-5" >
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <%for(int i=1;i<=countPage;i++){ %>
                        <li class="page-item"><a class="page-link" href="/conferences?SortByAct=<%=sortByAct%><%=sortText%>&page=<%=i%>"><%=i%></a></li>
                        <%}%>
                    </ul>
                </nav>
            </div>
        </div>
    </form>
</div>
</body>
</html>