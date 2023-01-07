<%-- 
    Document   : lesOrganisateurs
    Created on : Jan 4, 2023, 7:39:26 PM
    Author     : matti
--%>

<%@page import="java.util.List"%>
<%@page import="entites.Organisateur"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/stylesheetShow.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Les organisateurs — gestionDefile</title>
        <jsp:useBean id="listeOrganisateurs" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'admin'}">
            <% List<Organisateur> leOrganisateur = listeOrganisateurs; %>
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
                <center><h1>Les organisateurs</h1></center>
                
                <div class="table">
                    <table>
                    <tr>   
                      <th>Nom</th>                      
                      <th>Société</th>
                      <th>Téléphone</th>
                      <th>Adresse</th>
                    </tr>
                    <% for (Organisateur o :leOrganisateur) {%>
                    <tr>
                      <td><%= o.getPrenom() %> <%= o.getNom().toUpperCase() %></td>                      
                      <td><%= o.getNomSociete()%></a></td>
                      <td><%= o.getTelephone()%></td>
                      <td><%= o.getAdresse()%> <%= o.getCp() %> <%= o.getVille() %></td> 
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

