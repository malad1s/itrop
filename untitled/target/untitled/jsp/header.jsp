<%@ page import="com.my.enity.UserEnity" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 05.10.2022
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<?xml version="1.0" encoding="UTF-8"?>

<%@ page pageEncoding="utf-8"%>
<%UserEnity user= (UserEnity) session.getAttribute("user");%>

<div >
    <div class="p-3 bg-dark text-white">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
                </a>

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/" class="nav-link px-2 text-secondary">Home</a></li>
                    <li><a href="/conferences?page=1&SortByAct=1" class="nav-link px-2 text-white">Conferences</a></li>
                    <li><a href="/user_page" class="nav-link px-2 text-white">My page</a></li>
                </ul>

                <div class="text-end">
                    <%if(user==null){
                        out.print(" " +
                                "<button type=\"button\" class=\"btn btn-outline-light me-2\"><a href=\"/register\" class=\"nav-link px-2 text-secondary\">Register</a></button>" +
                                "<button type=\"button\" class=\"btn btn-warning\"><a href=\"/login\" class=\"nav-link px-2 text-secondary\">Log in</a></button>");
                    }else{
                        out.print("<button type=\"button\" class=\"btn btn-warning\"><a href=\"/logout\" class=\"nav-link px-2 text-secondary\">Logout</a></button>");
                    }
                    %>

                </div>
            </div>
        </div>
    </div>

</div>
