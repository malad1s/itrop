<%@ page import="java.util.List" %>
<%@ page import="com.my.enity.Event" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 09.10.2022
  Time: 20:58
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
<% List<UserEnity> speakers= (List<UserEnity>)request.getAttribute("speakers");
    String idEvent=request.getParameter("event");
    request.setAttribute("idEvent",idEvent);
%>


<div class="conteiner mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-lg-5 col-md-8 align-item-center">
            <div class="border border">
                <form action="/createReport" method="post">
                    <input type="hidden" id="idEvent" name="idEvent" value="<%=idEvent%>">
                    <h1 class="h3 mb-3 fw-normal" style="margin-bottom: 10px" >Create Report</h1>
                    <div class="form-floating" style="margin-bottom: 10px">
                        <h4 class="h5 mb-3 fw-normal" style="margin-bottom: 10px" >Topic</h4>
                        <input type="text" name="topic" class="form-control" id="topic" placeholder="Topic">
                    </div>
                    <div class="form-floating" style="margin-bottom: 10px">
                        <h4 class="h5 mb-3 fw-normal" style="margin-bottom: 10px" >Speaker</h4>
                        <select type="text" class="form-control" id="speaker" name="speaker" style="margin-bottom: 10px; margin-top: 10px" >
                            <option value="0">without speaker</option>
                            <%for (UserEnity speaker: speakers) {%>
                            <option value="<%=speaker.getId()%>"><%=speaker.getSurname()%> <%=speaker.getFirstname()%></option>
                            <%}%>
                        </select>
                    </div>
                    <button class="w-100 btn btn-lg btn-primary" type="submit">Create</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>