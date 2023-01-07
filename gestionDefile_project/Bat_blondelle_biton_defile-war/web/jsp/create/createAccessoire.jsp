<%-- 
    Document   : createAccessoire
    Created on : Dec 28, 2022, 11:24:46 PM
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
        <title>Créer un accessoire — gestionDefile</title>
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
                <h1>Créer un accessoire</h1>
                <form method="post" action="../../servletDefile">
                    <center><span id="status"><% out.println(attribut); %></span></center>
                    <div class="formContainer">
                        <div class="left">
                            <label for="nomAccessoire">Nom<span class="required">*</span></label>
                            <input type="text" name="nomAccessoire" required placeholder="Pigalle Louboutin">
                            <label for="prixAchat">Prix d'achat<span class="required">*</span></label>
                            <input type="number" name="prixAchat" required min="0" placeholder="645">
                            <label for="prixLocation">Prix de location<span class="required">*</span></label>
                            <input type="number" name="prixLocation" required min="0" placeholder="69">
                        </div>
                        <div class="right">
                            <label for="typeAccessoire">Catégorie d'accessoires<span class="required">*</span></label>
                            <select id="typeAccessoire" name="typeAccessoire" required onchange="showDiv()">
                                <option value="" disabled selected>Veuillez choisir la catégorie</option>
                                <option value="bijoux">Bijoux</option>
                                <option value="chaussures">Chaussures</option>
                                <option value="vestimentaire">Vestimentaires</option>
                            </select>
                            
                            <div class="conditionDiv" id="chaussures" style="display:none;">
                                <label for="tailleChaussures">Taille<span class="required">*</span></label>
                                <input type="number" id="tailleChaussures" name="tailleChaussures" placeholder="39">
                            </div>
                            
                            <div class="conditionDiv" id="bijoux" style="display:none;">
                                <label for="prixAssurance">Prix de l'assurance<span class="required">*</span></label>
                                <input type="number" id="prixAssurance" name="prixAssurance" min="0" placeholder="60">
                            </div>  
                            
                            <div class="conditionDiv" id="vestimentaire" style="display:none;">
                                <label for="typeVestimentaire">Type<span class=required>*</span></label>
                                <select id="typeVestimentaire" name="typeVestimentaire">
                                    <option value="" disabled selected>Veuillez choisir le type</option>
                                    <option value="echarpe">Écharpe</option>
                                    <option value="ceinture">Ceinture</option>
                                    <option value="chapeau">Chapeau</option>
                                </select>
                            </div>
                            
                            <input type="hidden" name="accountID" value="${sessionScope.accountID}">
                            <input type="hidden" name="action" value="createAccessoire">
                            <label>&nbsp;</label>
                            <div class="submitReset">
                                <input name=submit type="submit" value="Créer">
                                <input name=submit type="reset" value="Effacer">
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
            
            function showDiv() {
                var divs = document.getElementsByClassName("conditionDiv");
                for (var i = 0; i < divs.length; i++) {
                    divs[i].style.display = "none";
                }
  
                var accessoire = document.getElementById('typeAccessoire').value;
                document.getElementById(accessoire).style.display = "flex";
                document.getElementById(accessoire).style.flexDirection = "column";
            }
            
            var accessoireRequired = document.getElementById('typeAccessoire').value;
            
            if (accessoireRequired === "bijoux") {
                document.getElementById("prixAssurance").setAttribute("required", true);
            } else {
                document.getElementById("prixAssurance").removeAttribute("required");
            }
            
            if (accessoireRequired === "chaussures") {
                document.getElementById("tailleChaussures").setAttribute("required", true);
            } else {
                document.getElementById("tailleChaussures").removeAttribute("required");
            }
            
            if (accessoireRequired === "vestimentaire") {
                document.getElementById("typeVestimentaire").setAttribute("required", true);
            } else {
                document.getElementById("typeVestimentaire").removeAttribute("required");
            }
            
            
        </script>   
    </body>
</html>
