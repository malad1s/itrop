<%@ page import="com.my.enity.Event" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 07.10.2022
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<%@ include file="/jsp/header.jsp" %>
<%
    Event event =(Event)request.getAttribute("event");
%>

<div class="conteiner mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-lg-5 col-md-8 align-item-center">
            <div class="border border">
                <form action="/changeEvent" method="post">
                    <input type="hidden"  name="idEvent" value="<%=event.getId() %>">
                    <h1 class="h3 mb-3 fw-normal" style="margin-bottom: 10px" >Change event</h1>

                    <div class="form-floating" style="margin-bottom: 10px">
                        <input type="text" name="name" class="form-control" id="name" value="<%=event.getName()%>">
                        <label for="name">Name</label>
                    </div>
                    <div class="form-floating" style="margin-bottom: 10px">
                        <input type="text" name="date" value="<%=event.getDate()%>" class="form-control" id="date" placeholder="Date" min="<%=new Date().getYear()+1900%>-<%=new Date().getMonth()+1%>-<%=new Date().getDate()%>">
                        <label for="date">Date</label>
                    </div>
                    <div class="form-floating" style="margin-bottom: 10px">
                        <input type="time" name="time" value="<%=event.getTime()%>" class="form-control" id="time" placeholder="Time">
                        <label for="time">Date</label>
                    </div>
                    <div class="form-floating" style="margin-bottom: 10px">
                        <input type="text" name="place" value="<%=event.getPlace()%>"class="form-control" id="place" placeholder="Place">
                        <label for="place">Place</label>
                    </div>
                    <button class="w-100 btn btn-lg btn-primary" type="submit">Change</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
