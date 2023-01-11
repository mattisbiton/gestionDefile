<%-- 
    Document   : showVetementsAccessoires
    Created on : Jan 3, 2023, 11:49:52 AM
    Author     : matti
--%>

<%@page import="java.util.List"%>
<%@page import="entites.Vetement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/stylesheetShow.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Vêtements & accessoires — gestionDefile</title>
        <jsp:useBean id="listeVetements" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'mannequin'}">
            <% List<Vetement> leVetement = listeVetements;%>
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
                <center><h1>Vêtements & accessoires</h1></center>
                
                <div class="table"> 
                    <table>
                    <tr>   
                      <th>Nom</th>
                      <th>Créateur</th>
                      <th>Défilé</th>
                      <th>Ordre</th>
                      <th>Prix</th>
                      <th>Taille</th>
                      <th>Vestimentaire</th>
                      <th>Bijou</th>
                      <th>Chaussures</th>
                    </tr>
                    <% for (Vetement v :leVetement) {%>
                    <tr>
                      <td><%= v.getNomVetement() %></td>
                      <td><a href="servletDefile?id=<%= v.getLeCouturier().getId() %>&action=infosCouturier" target="_blank"><%= v.getLeCouturier().getPrenom() %> <%= v.getLeCouturier().getNom() %></a></td>
                      <td><a href="servletDefile?id=<%= v.getOrdre().getOrdreDuDefile().getId() %>&action=infosDefile" target="_blank"><%= v.getOrdre().getOrdreDuDefile().getNomDefile() %></a></td>
                      <td><%= v.getOrdre().getOrdrePassage() %></td>
                      <td><%= v.getPrixVetement() %></td>
                      <td><%= v.getTaille() %></td>
                      <td><a href="servletDefile?id=<%= v.getAccessoireVestimentaire().getId() %>&action=infosVestimentaire" target="_blank"><%= v.getAccessoireVestimentaire().getNom() %></a></td>
                      <td><a href="servletDefile?id=<%= v.getLeBijoux().getId() %>&action=infosBijou" target="_blank"><%= v.getLeBijoux().getNom() %></a></td>
                      <td><a href="servletDefile?id=<%= v.getLesChaussures().getId() %>&action=infosChaussures" target="_blank"><%= v.getLesChaussures().getNom() %></a></td>   
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
