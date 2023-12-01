<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 02.10.2022
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.my.enity.Event" %>
<%@ page import="com.my.enity.Report" %>
<%@ page import="com.my.enity.UserEnity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.my.enity.ReportAndSpeaker" %>


<%
    List<ReportAndSpeaker> reports= (List<ReportAndSpeaker>)request.getAttribute("reports");
    Event event =(Event)request.getAttribute("event");
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<%@ include file="/jsp/header.jsp" %>

<div  class="alert alert-info mt-2" style="margin-left: 48px">
    <h3>Event</h3>
    <h5>Event name: <span><%=event.getName() %></span></h5>
    <h5>Event date: <span><%=event.getDate() %></span></h5>
    <h5>Event time: <span><%=event.getTime() %></span></h5>
    <h5>Event place: <span><%=event.getPlace() %></span></h5>
    <form action="event" method="post">
        <input type="hidden"  name="idEvent" value="<%=event.getId() %>">

        <%
            if(user==null){
                out.print(" <label for=\"floatingInput\">Email</label>\n" +
                        "    <input type=\"email\" name=\"email\" class=\"form-control\" id=\"floatingInput\" placeholder=\"Email\">");
                out.print("<button class=\"w-100 btn btn-lg btn-primary\" type=\"submit\">Register on this</button>");

            }
        %>
        <%
            if(user!=null){
                if(user.getRole().getId()==1){
                    out.println("<button class=\"w-100 btn btn-lg btn-primary\" type=\"submit\">Register on this</button>");
                }else if(user.getRole().getId()==2){
                    out.println("<a href=\"createReport?event="+event.getId()+"\" class=\"btn btn-warning\">Create report</a>");
                    out.println("<a href=\"changeEvent?event="+event.getId()+"\" class=\"btn btn-warning\">Change info about event</a>");
                    out.println("<a href=\"eventPresence?event="+event.getId()+"\" class=\"btn btn-warning\">Presence</a>");
                    out.println("<a href=\"offers?event="+event.getId()+"\" class=\"btn btn-warning\">Offers</a>");
                    out.println("<a href=\"event?event="+event.getId()+"&delete=1\" class=\"btn btn-warning\">Delete Event</a>");
                }else if(user.getRole().getId()==3){
                    out.println("<a href=\"createReportBS?event="+event.getId()+"\" class=\"btn btn-warning\">Suggest report</a>");
                }

            }
        %>
    </form>
</div>

<br>
<h3>Reports</h3>
<%
    for (ReportAndSpeaker report: reports) {

%>
<div class="col-7"  >
    <% if(report.getSpeaker().getId()==0&&(user==null||user.getRole().getId()==1)){}else{%>
    <div class="alert alert-info mt-2" style="margin-left:48px">
        <%if(report.getReport().getPin()==1) {%>
        <h3><strong> Report pinned</strong></h3>
        <%}%>
        <h3><strong> Report topic:</strong><%=report.getReport().getTopic() %>  </h3>
        <%if(report.getSpeaker().getId()==0) {%>
            <h3><strong> Report Speaker:</strong>Without speaker</h3>
        <%} else { %>
             <h3><strong> Report Speaker:</strong><%=report.getSpeaker().getSurname()%> <%=report.getSpeaker().getFirstname()%></h3>
        <%}%>
        <%if(user!=null&&user.getRole().getId()==2){%>
            <a href="changeReport?event=<%=event.getId()%>&report=<%=report.getReport().getId()%>"class="btn btn-warning" >Change report</a>
            <%if(report.getReport().getPin()==1) {%>
                <a href="event?event=<%=event.getId()%>&unpin=<%=report.getReport().getId()%>"class="btn btn-warning" >Unpin</a>
            <%} else {%>
                <a href="event?event=<%=event.getId()%>&pin=<%=report.getReport().getId()%>"class="btn btn-warning" >Pin</a>
            <%}%>
        <%} else if(user!=null&&user.getRole().getId()==3&&report.getSpeaker().getId()==0){%>
         <a href="event?event=<%=event.getId()%>&report=<%=report.getReport().getId()%>&suggest_speaker=<%=user.getId()%>"class="btn btn-warning" >Suggest myself as Speaker</a>
        <%}%>
    </div>
    <%}%>
</div>
<%}%>

</body>
</html>