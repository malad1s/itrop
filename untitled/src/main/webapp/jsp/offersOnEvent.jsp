<%@ page import="com.my.enity.ReportAndSpeaker" %>
<%@ page import="java.util.List" %>
<%@ page import="com.my.enity.Event" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.10.2022
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ReportAndSpeaker> reports= (List<ReportAndSpeaker>)request.getAttribute("reports");
    Integer idEvent=(Integer) request.getAttribute("event");
    String dataType= (String) request.getParameter("dataType");
    if (dataType==null){
        dataType="offerOnFree";
    }

%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" media="screen" href="https://d3j1gkwxfnvq13.cloudfront.net/assets/webpack/bundle-events_page_css-dd4f635048adc8ac9cf6.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<%@ include file="/jsp/header.jsp" %>
<h3>Reports</h3>
<div class="evnt-events-tabs-nav">
    <ul class="evnt-tabs-list nav nav-tabs">
        <li class="evnt-tab-item nav-item">
            <%if(request.getParameter("dataType")!=null&&dataType.equals("offerOnNew")){%>
            <a class="evnt-tab-link nav-link " href="/offers?event=<%=idEvent%>&dataType=offerOnFree">
                <span class="evnt-tab-text desktop">Offers on free reports </span>
            </a>
            <%} else{ %>
            <a class="evnt-tab-link nav-link active" href="/offers?event=<%=idEvent%>&dataType=offerOnFree">
                <span class="evnt-tab-text desktop">Offers on free reports </span>
            </a>
            <% } %>
        </li>
        <li class="evnt-tab-item nav-item">
            <%if(request.getParameter("dataType")!=null&&dataType.equals("offerOnNew")){%>
            <a class="evnt-tab-link nav-link active" href="/offers?event=<%=idEvent%>&dataType=offerOnNew">
                <span class="evnt-tab-text desktop">New reports by speaker</span>
            </a>
            <%} else{ %>
            <a class="evnt-tab-link nav-link " href="/offers?event=<%=idEvent%>&dataType=offerOnNew">
                <span class="evnt-tab-text desktop">New reports by speaker</span>
            </a>
            <% } %>
        </li>
    </ul>
</div>


<%
    for (ReportAndSpeaker report: reports) {

%>
<div class="col-7"  >
    <div class="alert alert-info mt-2" style="margin-left:48px">
        <h3><strong> Report id:</strong><%=report.getReport().getId() %>  </h3>
        <h3><strong> Report topic:</strong><%=report.getReport().getTopic() %>  </h3>
        <h3><strong> Report Speaker:</strong><%=report.getSpeaker().getSurname()%> <%=report.getSpeaker().getFirstname()%></h3>
        <a href="/offers?event=<%=idEvent%>&change=<%=dataType%>&act=accept&report=<%=report.getReport().getId()%>&speaker=<%=report.getSpeaker().getId()%>" class="btn btn-warning btn-outline-light" >Accept</a>
        <a href="/offers?event=<%=idEvent%>&change=<%=dataType%>&act=cancel&report=<%=report.getReport().getId()%>&speaker=<%=report.getSpeaker().getId()%>" class="btn btn-warning btn-outline-light" >Cancel</a>
    </div>
</div>
<%}%>

</body>
</html>
