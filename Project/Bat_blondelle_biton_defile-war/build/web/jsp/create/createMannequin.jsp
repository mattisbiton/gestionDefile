<%-- 
    Document   : createMannequin
    Created on : Dec 26, 2022, 11:07:23 AM
    Author     : matti
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="../../css/stylesheetCreate.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Créer un mannequin — gestionDefile</title>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'couturier'}">
            <% String attribut = (String) request.getAttribute("message"); %>
            
            <div class="navbar">
                <nav>
                    <a class="logo" href="../../menu.jsp"><h1>gestion<strong>Defile</strong></h1></a>
                    <ul>
                        <li><a class="navbarMenu" href="../../menu.jsp">Dashboard</a></li>
                        <li><a class="navbarMenu" href="../../logout.jsp">Déconnexion</a></li>
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
                    <li><a class="navbarHamburger" href="../../menu.jsp">Dashboard</a></li>
                    <li><a class="navbarHamburger" href="../../logout.jsp">Déconnexion</a></li>
                </ul>
            </div>
            
            <div class="creationForm">
                <h1>Créer un mannequin</h1>
                <form method="post" action="../../servletDefile">
                    <center><span id="status"><% out.println(attribut); %></span></center>
                    <div class="formContainer">
                        <div class="left">
                            <label for="prenomMannequin">Prénom<span class="required">*</span></label>
                            <input type="text" name="prenomMannequin" required placeholder="Karl">
                            <label for="nomMannequin">Nom<span class="required">*</span></label>
                            <input type="text" name="nomMannequin" required placeholder="Lagerfeld">
                            <label for="poidsMannequin">Taille (en cm)<span class="required">*</span></label>
                            <input type="text" name="poidsMannequin" required placeholder="177">
                            <label for="tailleMannequin">Poids (en kg)<span class="required">*</span></label>
                            <input type="text" name="tailleMannequin" required placeholder="60">
                            <label for="prixPresta">Prix de la prestation<span class="required">*</span></label>
                            <input type="number" name="prixPresta" min=0 required placeholder="3750">
                            <label for="phoneMannequin">Téléphone<span class="required">*</span></label>
                            <input type="tel" name="phoneMannequin" required placeholder="0612345678">
                            
                        </div>
                        <div class="right">
                            <label for="adresseMannequin">Adresse<span class="required">*</span></label>
                            <input type="text" name="adresseMannequin" required placeholder="21 Rue Saint-Guillaume">
                            <label for="codePostal">Code postal<span class="required">*</span></label>
                            <input type="text" name="codePostal" required placeholder="75007">
                            <label for="villeMannequin">Ville<span class="required">*</span></label>
                            <input type="text" name="villeMannequin" required placeholder="Paris">
                            <label for="pwMannequin1">Mot de passe<span class="required">*</span></label>
                            <input type="password" name="pwMannequin1" required placeholder="********">
                            <label for="pwMannequin2">Confirmation mot de passe<span class="required">*</span></label>
                            <input type="password" name="pwMannequin2" required placeholder="********">
                            <input type="hidden" name="accountID" value="${sessionScope.accountID}">
                            <input type="hidden" name="action" value="createMannequin">
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
            <% response.sendRedirect("../../home.jsp"); %>
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
