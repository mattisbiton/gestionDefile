<%-- 
    Document   : editAccessoire
    Created on : Jan 1, 2023, 2:52:41 PM
    Author     : matti
--%>

<%@page import="entites.Vestimentaire"%>
<%@page import="entites.Bijoux"%>
<%@page import="entites.Chaussures"%>
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
        <title>Modifier un accessoire — gestionDefile</title>
        <jsp:useBean id="listeBijoux" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeChaussures" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeVestimentaires" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'couturier'}">
            <% List<Bijoux> leBijou = listeBijoux;%>
            <% List<Chaussures> lesChaussures = listeChaussures;%>
            <% List<Vestimentaire> AccessoireVestimentaire = listeVestimentaires;%>
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
                <h1>Modifier un accessoire</h1>
                <form method="post" action="servletDefile">
                    <center><span id="status"><% out.println(attribut); %></span></center>
                    
                    <center>
                        
                    </center>
                    
                    <div class="formContainerGrid">
                        <div class="left">
                            <select id="typeAccessoire" name="typeAccessoire" required onchange="showDiv()">
                                <option value="" disabled selected>Veuillez choisir la catégorie</option>
                                <option value="bijoux">Bijoux</option>
                                <option value="chaussures">Chaussures</option>
                                <option value="vestimentaire">Vestimentaires</option>
                            </select>
                            
                            <div class="conditionDiv" id="bijoux" style="display:none;">
                                <select class="selectList" id="idBijou" name="idBijou" onchange="updateInfosBijoux()">
                                    <option class="disabled" value="" disabled selected>Veuillez choisir un bijou</option>
                                    <% for (Bijoux b :leBijou) {%>
                                        <option value ="<%=b.getId()%>"
                                        data-nom="<%=b.getNom()%>"
                                        data-prixachat="<%=b.getPrixAchat()%>"
                                        data-prixloc="<%=b.getPrixLoc()%>"
                                        data-prixassu="<%=b.getPrixAssurance()%>"
                                        ><%=b.getNom()%></option>
                                    <% }%>
                                </select>
                                <input type="hidden" name="idSelectedBijoux" value="">
                                <label for="nomBijou">Nom</label>
                                <input type="text" id="nomBijou" name="nomBijou">
                                <label for="prixAchatBijou">Prix d'achat</label>
                                <input type="number" id="prixAchatBijou" name="prixAchatBijou" min="0">
                                <label for="prixLocationBijou">Prix de location</label>
                                <input type="number" id="prixLocationBijou" name="prixLocationBijou" min="0">
                                <label for="prixAssuranceBijou">Prix de l'assurance</label>
                                <input type="number" id="prixAssuranceBijou" name="prixAssuranceBijou" min="0">
                            </div>
                            <div class="conditionDiv" id="chaussures" style="display:none;"> 
                                <select class="selectList" id="idChaussures" name="idChaussures" onchange="updateInfosChaussures()">
                                    <option class="disabled" value="" disabled selected>Veuillez choisir une paire de chaussures</option>
                                    <% for (Chaussures ch :lesChaussures) {%>
                                        <option value ="<%=ch.getId()%>"
                                        data-nom="<%=ch.getNom()%>"
                                        data-prixachat="<%=ch.getPrixAchat()%>"
                                        data-prixloc="<%=ch.getPrixLoc()%>"
                                        data-taille="<%=ch.getTaille()%>"
                                        ><%=ch.getNom()%> (Taille <%=ch.getTaille()%>)</option>
                                    <% }%>
                                </select>
                                <input type="hidden" name="idSelectedChaussures" value="">
                                <label for="nomChaussures">Nom</label>
                                <input type="text" id="nomChaussures" name="nomChaussures">
                                <label for="prixAchatChaussures">Prix d'achat</label>
                                <input type="number" id="prixAchatChaussures" name="prixAchatChaussures" min="0">
                                <label for="prixLocationChaussures">Prix de location</label>
                                <input type="number" id="prixLocationChaussures" name="prixLocationChaussures" min="0">
                                <label for="tailleChaussures">Taille</label>
                                <input type="number" id="tailleChaussures" min=0 name="tailleChaussures">
                            </div>
                            <div class="conditionDiv" id="vestimentaire" style="display:none;">
                                <select class="selectList" id="idVestimentaire" name="idVestimentaire" onchange="updateInfosVestimentaire()">
                                    <option class="disabled" value="" disabled selected>Veuillez choisir un accessoire vestimentaire</option>
                                    <% for (Vestimentaire v :AccessoireVestimentaire) {%>
                                        <option value ="<%=v.getId()%>"
                                        data-nom="<%=v.getNom()%>"
                                        data-prixachat="<%=v.getPrixAchat()%>"
                                        data-prixloc="<%=v.getPrixLoc()%>"
                                        data-typeves="<%=v.getType()%>"><%=v.getNom()%></option>
                                    <% }%>
                                </select>
                                <input type="hidden" name="idSelectedVestimentaire" value="">
                                <label for="nomVestimentaire">Nom</label>
                                <input type="text" id="nomVestimentaire" name="nomVestimentaire">
                                <label for="prixAchatVestimentaire">Prix d'achat</label>
                                <input type="number" id="prixAchatVestimentaire" name="prixAchatVestimentaire" min="0">
                                <label for="prixLocationVestimentaire">Prix de location</label>
                                <input type="number" id="prixLocationVestimentaire" name="prixLocationVestimentaire" min="0">
                                <label for="typeVestimentaire">Type</label>
                                <select id="typeVestimentaire" name="typeVestimentaire">
                                    <option value="" disabled selected>Veuillez choisir le type</option>
                                    <option value="echarpe">Écharpe</option>
                                    <option value="ceinture">Ceinture</option>
                                    <option value="chapeau">Chapeau</option>
                                </select>
                            </div>

                            <input type="hidden" name="accountID" value="${sessionScope.accountID}">
                            <input type="hidden" name="action" value="submitEditAccessoire">
                            <label>&nbsp;</label>
                            <div class="submitReset">
                                <input type="submit" name="submit" value="Modifier">
                                <input type="submit" name="delete" value="Supprimer"
                                onclick="return confirm('Voulez-vous vraiment supprimer cet accessoire ?');">
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
            
            function showDiv() {
                var divs = document.getElementsByClassName("conditionDiv");
                for (var i = 0; i < divs.length; i++) {
                    divs[i].style.display = "none";
                }
  
                var accessoire = document.getElementById('typeAccessoire').value;
                document.getElementById(accessoire).style.display = "flex";
                document.getElementById(accessoire).style.flexDirection = "column";
            }
            
            function updateInfosBijoux() {
                var selectedOption = document.getElementById("idBijou").selectedOptions[0];

                document.getElementById("nomBijou").value = selectedOption.dataset.nom;
                document.getElementById("prixAchatBijou").value = selectedOption.dataset.prixachat;
                document.getElementById("prixLocationBijou").value = selectedOption.dataset.prixloc;
                document.getElementById("prixAssuranceBijou").value = selectedOption.dataset.prixassu;
            }
            
            function updateInfosChaussures() {
                var selectedOption = document.getElementById("idChaussures").selectedOptions[0];

                document.getElementById("nomChaussures").value = selectedOption.dataset.nom;
                document.getElementById("prixAchatChaussures").value = selectedOption.dataset.prixachat;
                document.getElementById("prixLocationChaussures").value = selectedOption.dataset.prixloc;
                document.getElementById("tailleChaussures").value = selectedOption.dataset.taille;
            }
            
            function updateInfosVestimentaire() {
                var selectedOption = document.getElementById("idVestimentaire").selectedOptions[0];

                document.getElementById("nomVestimentaire").value = selectedOption.dataset.nom;
                document.getElementById("prixAchatVestimentaire").value = selectedOption.dataset.prixachat;
                document.getElementById("prixLocationVestimentaire").value = selectedOption.dataset.prixloc;
                document.getElementById("typeVestimentaire").value = selectedOption.dataset.typeves;
            }
            
            
        </script>   
    </body>
</html>