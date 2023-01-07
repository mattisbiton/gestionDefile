<%-- 
    Document   : couturiersOrganisateur
    Created on : Jan 4, 2023, 5:08:13 PM
    Author     : matti
--%>

<%@page import="java.util.List"%>
<%@page import="entites.Couturier"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/stylesheetShow.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Les couturiers — gestionDefile</title>
        <jsp:useBean id="listeCouturiers" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'organisateur' }">
            <% List<Couturier> leCouturier = listeCouturiers;%>
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
                <center><h1>Les couturiers</h1></center>
                
                <div class="table">
                    <table>
                    <tr>   
                      <th>Nom</th>
                      <th>Maison</th>
                      <th>Téléphone</th>
                      <th>Adresse</th>
                    </tr>
                    <% for (Couturier c :leCouturier) {%>
                    <tr>
                      <td><%= c.getPrenom()%> <%=c.getNom()%></td>
                      <td><%= c.getNomMaisonCouture() %></td>
                      <td><%= c.getTelephone() %>
                      <td><%= c.getAdresse() %> <%= c.getCp() %> <%= c.getVille() %></td> 
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
