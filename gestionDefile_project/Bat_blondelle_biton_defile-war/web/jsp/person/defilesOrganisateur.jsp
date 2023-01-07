<%-- 
    Document   : defilesOrganisateur
    Created on : Jan 3, 2023, 5:59:18 PM
    Author     : matti
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entites.Couturier"%>
<%@page import="java.util.List"%>
<%@page import="entites.Defile"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/stylesheetShow.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Mes défilés — gestionDefile</title>
        <jsp:useBean id="listeDefiles" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeInvit" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'organisateur' }">
            <% List<Defile> leDefile = listeDefiles;%>
            <% List<Couturier> leCouturier = listeInvit;%>
            <% Map<Defile, Double> defileCoutMap = (Map<Defile, Double>) request.getAttribute("defileCoutMap"); %>
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
                <center><h1>Mes défilés</h1></center>
                
                <div class="table">
                    <table>
                        <tr>   
                          <th>Nom</th>
                          <th>Lieu</th>
                          <th>Date</th>
                          <th>Couturier</th>
                          <th>Invités</th>
                          <th>Coût</th>
                        </tr>
                    <% for (Defile d :leDefile) {%>
                    <% 
                        List<Couturier> listeCouturiersInvites = d.getLesInvites();
                        String[] couturierIds = new String[listeCouturiersInvites.size()];
                        String[] couturierNames = new String[listeCouturiersInvites.size()];
                        for (int i = 0; i < listeCouturiersInvites.size(); i++) {
                          Couturier couturier = listeCouturiersInvites.get(i);
                          couturierIds[i] = Long.toString(couturier.getId());
                          couturierNames[i] = couturier.getPrenom() + " " + couturier.getNom();
                        }
                    %>
                        <tr>
                          <td><%= d.getNomDefile() %></td>
                          <td><a href="servletDefile?id=<%= d.getLeLieu().getId() %>&action=infosLieu" target="_blank"><%= d.getLeLieu().getNom() %></a></td>
                          <td><%= d.getDateDefile() %>
                          <td><a href="servletDefile?id=<%= d.getLeCouturier().getId() %>&action=infosCouturier" target="_blank"><%= d.getLeCouturier().getPrenom() %> <%= d.getLeCouturier().getNom() %></a></td>
                          <td>
                              <% for (int i = 0; i < couturierNames.length; i++) { %>
                                <a href="servletDefile?id=<%= couturierIds[i] %>&action=infosCouturier" target="_blank"><%= couturierNames[i] %></a>
                                <br>
                              <% } %>
                          </td>
                          <td> <%= defileCoutMap.get(d) %> </td>  
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
