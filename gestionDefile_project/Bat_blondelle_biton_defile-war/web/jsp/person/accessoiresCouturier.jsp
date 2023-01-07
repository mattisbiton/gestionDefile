<%-- 
    Document   : accessoiresCouturier
    Created on : Jan 4, 2023, 5:52:02 PM
    Author     : matti
--%>

<%@page import="java.util.List"%>
<%@page import="entites.Bijoux"%>
<%@page import="entites.Chaussures"%>
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
        <title>Mes accessoires — gestionDefile</title>
        <jsp:useBean id="listeBijoux" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeChaussures" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeVestimentaires" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'couturier' }">
            <% List<Bijoux> leBijou = listeBijoux;%>
            <% List<Chaussures> lesChaussures = listeChaussures;%>
            <% List<Vestimentaire> leVestimentaire = listeVestimentaires;%>
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
                <center><h1>Mes accessoires</h1></center>
                
                <div class="table">
                    <h2>Bijoux</h2>
                
                <table>
                    <tr>   
                      <th>Nom</th>
                      <th>Prix d'achat</th>
                      <th>Prix de location</th>
                      <th>Prix de l'assurance</th>
                      <th>Total</th>
                    </tr>
                    <% for (Bijoux b :leBijou) {%>
                    <tr>
                      <td><%= b.getNom() %></td>
                      <td><%= b.getPrixAchat() %></td>
                      <td><%= b.getPrixLoc() %>
                      <td><%= b.getPrixAssurance()%></td>
                      <td>
                          <% double sommePrixBij = b.getPrixAssurance() + b.getPrixAchat() + b.getPrixLoc(); %>
                          <%=sommePrixBij%>
                      </td>
                      <td>
                          <% %>
                      </td>  
                    </tr>
                    <% } %>
                  </table>
                </div>
                  
                <div class="table">
                    <h2>Chaussures</h2>

                    <table>
                        <tr>   
                          <th>Nom</th>
                          <th>Pointure</th>
                          <th>Prix d'achat</th>
                          <th>Prix de location</th>
                          <th>Total</th>
                        </tr>
                        <% for (Chaussures c :lesChaussures) {%>
                        <tr>
                          <td><%= c.getNom() %></td>
                          <td><%= c.getTaille() %></td>
                          <td><%= c.getPrixAchat() %></td>
                          <td><%= c.getPrixLoc() %>
                          <td>
                              <% double sommePrixCh = c.getPrixAchat() + c.getPrixLoc(); %>
                              <%=sommePrixCh%>
                          </td>
                          <td>
                              <% %>
                          </td>  
                        </tr>
                        <% } %>
                      </table>
                </div>
                  
                <div class="table">
                    <h2>Accessoires vestimentaires</h2>
                
                <table>
                    <tr>   
                      <th>Nom</th>
                      <th>Type</th>
                      <th>Prix d'achat</th>
                      <th>Prix de location</th>
                      <th>Total</th>
                    </tr>
                    <% for (Vestimentaire v :leVestimentaire) {%>
                    <tr>
                      <td><%= v.getNom() %></td>
                      <% String str = v.getType().toString();
                      String capitalized = str.substring(0, 1).toUpperCase() + str.substring(1);%>
                      <td><%=capitalized%></td>
                      <td><%= v.getPrixAchat() %></td>
                      <td><%= v.getPrixLoc() %>
                      <td>
                          <% double sommePrixVest = v.getPrixAchat() + v.getPrixLoc(); %>
                          <%=sommePrixVest%>
                      </td>
                      <td>
                          <% %>
                      </td>  
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
