<%@ page import="com.my.enity.Event" %>
<%@ page import="com.my.enity.EventAndReports" %>
<%@ page import="java.util.List" %>
<%@ page import="com.my.enity.Report" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11.10.2022
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<EventAndReports> eventAndReports= (List<EventAndReports>)request.getAttribute("EventNReports");
    List<Event> events= (List<Event>)  request.getAttribute("Events");
%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>
<%@ include file="/jsp/header.jsp" %>
<%
    for(Event eventUser: events){
%>
<h3><strong> Event name:</strong><%=eventUser.getName() %>  </h3>
<h3><strong> Event Date:</strong><%=eventUser.getDate() %>  </h3>
<h3><strong> Event Time:</strong><%=eventUser.getTime() %>  </h3>
<h3><strong> Event Place:</strong><%=eventUser.getPlace() %>  </h3>
<a href="event?event=<%=eventUser.getId() %>"class="btn btn-warning" >Details</a>
<a href="my_conferences?event=<%=eventUser.getId() %>&unsubscribing=1"class="btn btn-warning" >Unsubscribing</a>
<%}%>
<%
    for(EventAndReports event: eventAndReports){
%>
<h3><strong> Event name:</strong><%=event.getEvent().getName() %>  </h3>
<h3><strong> Event Date:</strong><%=event.getEvent().getDate() %>  </h3>
<h3><strong> Event Place:</strong><%=event.getEvent().getPlace() %>  </h3>
<a href="event?event=<%=event.getEvent().getId() %>"class="btn btn-warning" >SHOW THIS Event</a>
<div class="alert alert-info mt-2" style="margin-left:20px" style="margin-bottom: 5px">
<%
    List<Report> reports = event.getReports();
    for(Report report:reports ){
%>

<h3><strong> Report topic:</strong><%=report.getTopic() %>  </h3>

<%}%></div><%}%>


</body>
</html>
