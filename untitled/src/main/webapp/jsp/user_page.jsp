<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25.09.2022
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.my.enity.UserEnity" %>

<html >
<head>
    <title>My page</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<%@ include file="/jsp/header.jsp" %>

<div class="conteiner mt-5 mb-5"  style="margin-left: 48px">
    <h1><strong>Main</strong></h1><br><br>

    <%
        if(user.getRole().getId()==1){
            out.print(" <h2><strong><a href=\"/my_conferences\" class=\"nav-link px-2\">My conferences</a></strong></h2> ");
            out.print(" <h2><strong><a href=\"/user_update\" class=\"nav-link px-2\">Change personal info</a></strong></h2> ");
        }else if(user.getRole().getId()==2){
            out.print("<h2><strong><a href=\"/create_event\" class=\"nav-link px-2\">Create event</a></strong></h2>");
            out.print("<h2><strong><a href=\"/conferences\" class=\"nav-link px-2\">Change events</a></strong></h2>");
        }else if(user.getRole().getId()==3){
            out.print("<h2><strong><a href=\"/my_conferences\" class=\"nav-link px-2\">My events</a></strong></h2>");
        }
    %>


    <br><br><br>

    <h3 class =" mt-3 mb-3">Personal info</h3>
    <h4 class =" mt-1 mb-1">Name : <%=user.getSurname()%> <%=user.getFirstname()%>  </h4>
    <h4 class =" mt-1 mb-1">Email: <%=user.getEmail()%> </h4>
    <h4 class =" mt-1 mb-1">Role: <%=user.getRole().getName()%>  </h4>
</div>


</body>
</html>