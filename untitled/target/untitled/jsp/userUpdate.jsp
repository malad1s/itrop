<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.10.2022
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="/jsp/header.jsp" %>
<div class="conteiner mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-lg-5 col-md-8 align-item-center">
            <div class="border border">
                <form action="user_update" method="post">
                    <h1 class="h3 mb-3 fw-normal" style="margin-bottom: 10px" >Change info</h1>
                    <div class="form-floating" style="margin-bottom: 10px">
                        <input type="text" name="surname" class="form-control" id="surname"  value="<%=user.getSurname()%>">
                        <label for="surname">Surname</label>
                    </div>
                    <div class="form-floating" style="margin-bottom: 10px">
                        <input type="text" name="firstname" class="form-control" id="firstname"  value="<%=user.getFirstname()%>">
                        <label for="firstname">Firstname</label>
                    </div>
                    <div class="form-floating" style="margin-bottom: 10px">
                        <input type="text" name="email" class="form-control" id="email" value="<%=user.getEmail()%>">
                        <label for="email">Email</label>
                    </div>
                    <div class="form-floating" style="margin-bottom: 10px">
                        <input  name="password" class="form-control" id="password" >
                        <label for="password">Password</label>
                    </div>
                    <button class="w-100 btn btn-lg btn-primary" type="submit">Change</button>
                    <p class="mt-5 mb-3 text-muted">&copy; 2017–2022 VaV</p>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
