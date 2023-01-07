<%-- 
    Document   : leVestimentaire
    Created on : Jan 3, 2023, 1:56:51 PM
    Author     : matti
--%>

<%@page import="entites.Vestimentaire"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/stylesheetShow.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Vestimentaire — gestionDefile</title>
    </head>
    <body>
        <c:choose>
        <c:when test="${!empty sessionScope.roleAccount }">
            <% Vestimentaire v = (Vestimentaire) request.getAttribute("vestimentaire"); %>
            <% String attribut = (String) request.getAttribute("message"); %>
            
            <div class="navbar">
                <nav>
                    <a class="logo" href="menu.jsp"><h1>gestion<strong>Defile</strong></h1></a>
                    <ul>
                        <li><a class="navbarMenu" href="menu.jsp">Dashboard</a></li>
                        <li><a class="navbarMenu" href="logout.jsp">Déconnexion</a></li>
                        <li><p id="roleAccount">${sessionScope.roleAccount}</p></li>
                    </ul>
                </nav>
            </div>
                    
            <div class="hamburger-menu">
                <input id="menu__toggle" type="checkbox" />
                <label class="menu__btn" for="menu__toggle">
                <span></span>
                </label>

                <ul class="menu__box">
                    <li><p><span id="roleAccountHamburger">${sessionScope.roleAccount}</span></p></li>
                    <li><a class="navbarHamburger" href="/menu.jsp">Dashboard</a></li>
                    <li><a class="navbarHamburger" href="/logout.jsp">Déconnexion</a></li>
                </ul>
            </div>
            
            <div class="content">
                <center><h1>Informations sur l'accessoire vestimentaire</h1></center>
                
                <div class="container">
                    <div class="img">
                        <img src="img/searching.png">
                    </div>
                    <div class="info">
                        <p><b>Nom :</b> <%= v.getNom()%></p><br>
                        <p><b>Type :</b> <% String str = v.getType().toString();
                      String capitalized = str.substring(0, 1).toUpperCase() + str.substring(1);%><%=capitalized%></p><br>
                        <p><b>Couturier :</b> <a href="servletDefile?id=<%= v.getLeCouturier().getId() %>&action=infosCouturier"><%= v.getLeCouturier().getPrenom() %> <%= v.getLeCouturier().getNom() %></a></p><br>
                        <p><b>Prix d'achat :</b> <%= v.getPrixAchat() %></p><br>
                        <p><b>Prix de location :</b> <%= v.getPrixLoc() %></p><br>
                        <p><b>Prix total :</b> <% double bijouTotal = v.getPrixAchat() + v.getPrixLoc(); %>
                          <%=bijouTotal%></p><br>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <% response.sendRedirect("login.jsp"); %>
        </c:otherwise>
        </c:choose>
        
         <script>
            const status = document.getElementById('status');
            
            if(status.textContent.includes("null")) {
                status.style.display = "none";
            } else {
                status.style.display = "";
            }         
            
        </script>   
    </body>
</html>