<%-- 
    Document   : mannequinsCouturier
    Created on : Jan 4, 2023, 5:31:07 PM
    Author     : matti
--%>

<%@page import="java.util.List"%>
<%@page import="entites.Mannequin"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/stylesheetShow.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Mes mannequins — gestionDefile</title>
        <jsp:useBean id="listeMannequins" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'couturier'}">
            <% List<Mannequin> leMannequin = listeMannequins; %>
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
                <center><h1>Mes mannequins</h1></center>
                
                <div class="table">
                    <table>
                    <tr>   
                      <th>Nom</th>                      
                      <th>Poids</th>
                      <th>Taille</th>
                      <th>Prix</th>
                      <th>Téléphone</th>
                      <th>Adresse</th>
                    </tr>
                    <% for (Mannequin m :leMannequin) {%>
                    <tr>
                      <td><%= m.getPrenom() %> <%= m.getNom() %></td>                      
                      <td><%= m.getPoids() %></a></td>
                      <td><%= m.getTaille() %></td>
                      <td><%= m.getPrixPresta() %></td>
                      <td><%= m.getTelephone()%></td>
                      <td><%= m.getAdresse()%> <%= m.getCp() %> <%= m.getVille() %></td> 
                    </tr>
                    <% } %>
                  </table>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <% response.sendRedirect("home.jsp"); %>
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
