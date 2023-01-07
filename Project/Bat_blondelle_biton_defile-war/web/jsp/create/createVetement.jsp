<%-- 
    Document   : createVetement
    Created on : Dec 28, 2022, 11:24:32 PM
    Author     : matti
--%>

<%@page import="entites.Defile"%>
<%@page import="entites.Mannequin"%>
<%@page import="entites.Vestimentaire"%>
<%@page import="entites.Chaussures"%>
<%@page import="entites.Bijoux"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/stylesheetCreate.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Créer un vêtement — gestionDefile</title>
        <jsp:useBean id="listeBijoux" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeChaussures" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeVestimentaires" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeMannequins" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeDefiles" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'couturier'}">
            <% List<Bijoux> leBijou = listeBijoux;%>
            <% List<Chaussures> lesChaussures = listeChaussures;%>
            <% List<Vestimentaire> AccessoireVestimentaire = listeVestimentaires;%>
            <% List<Mannequin> leMannequin = listeMannequins;%>
            <% List<Defile> leDefile = listeDefiles;%>
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
            
            <div class="creationForm">
                <h1>Créer un vêtement</h1>
                <form method="post" action="servletDefile">
                    <center><span id="status"><% out.println(attribut); %></span></center>
                    <div class="formContainer">
                        <div class="left">
                            <label for="dateCreation">Date de création<span class="required">*</span></label>
                            <input type="date" name="dateCreation" required>
                            <label for="nomVetement">Nom<span class="required">*</span></label>
                            <input type="text" name="nomVetement" required placeholder="Robe">
                            <label for="prixVetement">Prix<span class="required">*</span></label>
                            <input type="number" name="prixVetement" required min="0" placeholder="1999">
                            <label for="tailleVetement">Taille du vêtement<span class="required">*</span></label>
                            <select name="tailleVetement" required>
                                <option value="" disabled selected>Veuillez choisir une taille</option>
                                <option value="XS">Taille XS</option>
                                <option value="S">Taille S</option>
                                <option value="M">Taille M</option>
                                <option value="L">Taille L</option>
                                <option value="XL">Taille XL</option>
                                <option value="XXL">Taille XXL</option>
                            </select>  
                            <label for="idMannequin">Mannequin<span class="required">*</span></label>
                            <select name="idMannequin" required>
                                <option class="disabled" value="" disabled selected>Veuillez choisir un mannequin</option>
                                <% for (Mannequin m :leMannequin) {%>
                                    <option value ="<%=m.getId()%>"><%=m.getPrenom()%> <%=m.getNom()%></option>
                                <% }%>
                            </select>
                        </div>
                        <div class="right">
                            <!-- ordre -->
                            <label for="idDefile">Défilé concerné<span class="required">*</span></label>
                            <select name="idDefile" required>
                                <option class="disabled" value="" disabled selected>Veuillez choisir un défilé</option>
                                <% for (Defile d :leDefile) {%>
                                    <option value ="<%=d.getId()%>"><%=d.getNomDefile()%></option>
                                <% }%>
                            </select>
                            <label for="idBijou">Bijou</label>
                            <select name="idBijou">
                                <option class="disabled" value="" disabled selected>Veuillez choisir un bijou</option>
                                <% for (Bijoux b :leBijou) {%>
                                    <option value ="<%=b.getId()%>"><%=b.getNom()%></option>
                                <% }%>
                            </select>
                            <label for="idChaussures">Chaussures</label>
                            <select name="idChaussures">
                                <option class="disabled" value="" disabled selected>Veuillez choisir une paire de chaussures</option>
                                <% for (Chaussures ch :lesChaussures) {%>
                                    <option value ="<%=ch.getId()%>"><%=ch.getNom()%> (Taille <%=ch.getTaille()%>)</option>
                                <% }%>
                            </select>
                            <label for="idVestimentaire">Accessoire vestimentaire</label>
                            <select name="idVestimentaire">
                                <option class="disabled" value="" disabled selected>Veuillez choisir un accessoire vestimentaire</option>
                                <% for (Vestimentaire v :AccessoireVestimentaire) {%>
                                    <option value ="<%=v.getId()%>"><%=v.getNom()%></option>
                                <% }%>
                            </select>
                            <label for="intOrdre">Ordre d'apparition<span class="required">*</span></label>
                            <input type="number" min="0" name="intOrdre" required placeholder="2">
                            
                            <input type="hidden" name="accountID" value="${sessionScope.accountID}">
                            <input type="hidden" name="action" value="submitCreateVetement">
                            <label>&nbsp;</label>
                            <div class="submitReset">
                                <input name="submit" type="submit" value="Créer">
                                <input name="submit" type="reset" value="Effacer">
                            </div>
                        </div>
                    </div>
                    
                    
                    
                </form>
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
