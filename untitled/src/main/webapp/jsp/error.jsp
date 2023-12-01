<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 07.10.2022
  Time: 21:40
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
<h1>Error<h2>
<%=request.getAttribute("error")%>

</body>
</html>
