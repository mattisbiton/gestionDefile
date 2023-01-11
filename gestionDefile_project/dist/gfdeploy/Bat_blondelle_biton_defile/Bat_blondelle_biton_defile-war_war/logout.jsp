<%-- 
    Document   : logout
    Created on : Dec 19, 2022, 9:42:33 PM
    Author     : matti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% session.invalidate();
        response.sendRedirect("home.jsp"); %>
    </body>
</html>
