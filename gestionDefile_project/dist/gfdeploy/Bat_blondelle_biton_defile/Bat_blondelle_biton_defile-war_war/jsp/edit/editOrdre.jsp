<%-- 
    Document   : editOrdre
    Created on : Jan 8, 2023, 11:05:47 AM
    Author     : matti
--%>

<%@page import="java.util.Map"%>
<%@page import="entites.Vetement"%>
<%@page import="java.util.List"%>
<%@page import="entites.Defile"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/stylesheetEdit.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Votre recherche — gestionDefile</title>
        <script src="https://cdn.jsdelivr.net/npm/sortablejs@latest/Sortable.min.js"></script>
        <jsp:useBean id="listeDefiles" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
            <% List<Defile> leDefile = listeDefiles;%>
            <% Map<Defile, List<Vetement>> defileVetements = (Map<Defile, List<Vetement>>) request.getAttribute("defileVetements"); %>
            
            <div class="navbar">
                <nav>
                    <a class="logo" href="menu.jsp"><h1>gestion<strong>Defile</strong></h1></a>
                    <c:choose>
                        <c:when test="${!empty sessionScope.roleAccount}">
                            <ul>
                                <li><a class="navbarMenu" href="menu.jsp">Dashboard</a></li>
                                <li><a class="navbarMenu" href="logout.jsp">Déconnexion</a></li>
                                <li><p id="roleAccount">${sessionScope.roleAccount}</p></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <ul>
                                <li><a class="navbarMenu" href="login.jsp">Connexion</a></li>
                                <li><p id="roleAccount">Grand public</p></li>
                            </ul>
                        </c:otherwise>
                    </c:choose>
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
                <center><h1>Modifier l'ordre des vêtements</h1></center>
                <form method="post" action="servletDefile">
                    <label for="idDefile">Défilé concerné</label>
                    <select id="idDefile" name="idDefile" required">
                        <option disabled selected value="">Veuillez choisir un défilé</option>
                        <% for (Defile d : leDefile) { %>
                            <option value="<%=d.getId()%>"
                            data-nom="<%=d.getNomDefile()%>"
                            data-lieu="<%=d.getLeLieu().getId()%>"
                            data-couturier="<%=d.getLeCouturier().getId()%>"><%=d.getNomDefile()%></option>
                        <% } %> 
                    </select>
                    <input type="hidden" name="action" value="choixDefile">
                    <br>
                    <input type="submit" name="submit" value="Valider">
                </form>
            </div>
        
         <script>
            document.getElementById('idDefile').addEventListener('change', function() {
                console.log('hello');
            });
        </script>   
    </body>
</html>
