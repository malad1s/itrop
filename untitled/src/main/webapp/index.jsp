<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<body>


<%
    out.println("Your IP address is " + request.getRemoteAddr());
%>
<p>Today's date: <%= (new java.util.Date())%></p>

<c:forEach var="user" items="${users}">
    <p>${user}</p>
</c:forEach>

<c:forEach var="i" begin="1" end="5">
    <h1>${i}</h1>
</c:forEach>
<a href="/start">start</a>
<a href="/home">home</a>
<a href="/login">login</a>
</body>
</html>
