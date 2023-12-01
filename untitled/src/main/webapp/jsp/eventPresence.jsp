<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 16.10.2022
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<UserEnity> participants= (List<UserEnity>)request.getAttribute("participants");
    String idEvent= (String) request.getAttribute("event");
%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>
<%@ include file="/jsp/header.jsp" %>
<form action="eventPresence" method="post">
    <input  type="hidden" name="idEvent" class="form-control" id="idEvent" value="<%=idEvent%>" >
    <button class="w-100 btn btn-lg btn-primary" type="submit">Save Presense in PDF</button>
</form>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Surname and first name</th>
        <th scope="col">Email</th>
        <th scope="col">Status presence</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
        <%
            for (UserEnity userEnit: participants) {
        %>

        <tr>
            <td><%=userEnit.getSurname()%> <%=userEnit.getFirstname()%></td>
            <td><%=userEnit.getEmail()%></td>
            <%if(userEnit==null){%>
            <td></td>
            <%}else if(userEnit.getPresence()==0){%>
            <td>Didn't came</td>
            <td>
                <a href="/eventPresence?event=<%=idEvent%>&user=<%=userEnit.getId()%>&presence=1"class="btn btn-warning" >Come</a>
            </td>
            <%}else if(userEnit.getPresence()==1){%>
            <td>Came</td>
            <td>
                <a href="/eventPresence?event=<%=idEvent%>&user=<%=userEnit.getId()%>&presence=0"class="btn btn-warning" >Didn't come</a>
            </td>

            <%}%>
        </tr>
        <%}%>
    </tbody>
</table>



</body>
</html>
