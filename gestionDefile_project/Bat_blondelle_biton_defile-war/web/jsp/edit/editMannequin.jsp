<%-- 
    Document   : editMannequin
    Created on : Jan 1, 2023, 12:27:14 PM
    Author     : matti
--%>

<%@page import="entites.Mannequin"%>
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
        <title>Modifier un mannequin — gestionDefile</title>
        <jsp:useBean id="listeEditMannequins" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'couturier'}">
            <% List<Mannequin> leMannequin = listeEditMannequins;%>
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
                <h1>Modifier un mannequin</h1>
                <form method="post" action="servletDefile">
                    <center><span id="status"><% out.println(attribut); %></span></center>
                    
                    <div class="formContainer">
                        <div class="left">
                            <label for="idMannequin">Mannequin à modifier</label>
                            <select id="idMannequin" name="idMannequin" required onchange="updateInfosMannequin()">
                                <option disabled selected value="">Veuillez choisir un mannequin</option>
                                <% for (Mannequin m : leMannequin) { %>
                                    <option value="<%=m.getId()%>"
                                    data-id="<%=m.getId()%>"
                                    data-prenom="<%=m.getPrenom()%>" 
                                    data-nom="<%=m.getNom()%>" 
                                    data-poids="<%=m.getPoids()%>" 
                                    data-taille="<%=m.getTaille()%>" 
                                    data-pp="<%=m.getPrixPresta()%>" 
                                    data-phone="<%=m.getTelephone()%>" 
                                    data-adresse="<%=m.getAdresse()%>" 
                                    data-cp="<%=m.getCp()%>" 
                                    data-ville="<%=m.getVille()%>"
                                    data-pw="<%=m.getMdp()%>"><%=m.getPrenom()%> <%=m.getNom()%></option>
                                <% } %>
                            </select> 
                            <input type="hidden" value="" id="idMannequinSelected" name="idMannequinSelected">
                            <label for="prenomMannequin">Prénom</label>
                            <input type="text" id="prenomMannequin" name="prenomMannequin" value="" required>
                            <label for="nomMannequin">Nom</label>
                            <input type="text" id="nomMannequin" name="nomMannequin" value="" required>
                            <label for="tailleMannequin">Taille (en cm)</label>
                            <input type="number" id="tailleMannequin" name="tailleMannequin" value="" min="0" required>
                            <label for="poidsMannequin">Poids (en kg)</label>
                            <input type="number" id="poidsMannequin" name="poidsMannequin" value="" min="0" required>
                            <label for="prixPresta">Prix de la prestation</label>
                            <input type="number" id="prixPresta" name="prixPresta" value="" min="0" required>
                        </div>
                        <div class="right">
                            <label for="phoneMannequin">Téléphone</label>
                            <input type="tel" id="phoneMannequin" name="phoneMannequin" value="" required>
                            <label for="adresseMannequin">Adresse</label>
                            <input type="text" id="adresseMannequin" name="adresseMannequin" required>
                            <label for="codePostal">Code postal</label>
                            <input type="text" id="codePostal" name="codePostal" required>
                            <label for="villeMannequin">Ville</label>
                            <input type="text" id="villeMannequin" name="villeMannequin" required>
                            <label for="pwMannequin1">Mot de passe</label>
                            <input type="text" id="pwMannequin1" name="pwMannequin1" required autocomplete="off">
                            <label for="pwMannequin2">Confirmation mot de passe</label>
                            <input type="text" id="pwMannequin2" name="pwMannequin2" required autocomplete="off">
                            <input type="hidden" name="accountID" value="${sessionScope.accountID}">
                            <input type="hidden" name="action" value="submitEditMannequin">
                            
                            <div class="submitReset">
                                <input type="submit" name="submit" value="Modifier"
                                onclick="return checkPasswords()">
                                <input type="submit" name="delete" value="Supprimer"
                                onclick="return confirm('Voulez-vous vraiment supprimer ce mannequin ?');">
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
            
            function checkPasswords() {
                var pw1 = document.getElementById("pwMannequin1").value;
                var pw2 = document.getElementById("pwMannequin2").value;
                
                if(pw1 !== pw2) {
                    alert("Les mots de passe ne correspondent pas. Veuillez vérifier les informations.");
                    return false;
                }
                
                return true;
            }
            
            function updateInfosMannequin() {
                // Get the selected option element
                var selectedOption = document.getElementById("idMannequin").selectedOptions[0];

                // Use the data attributes of the selected option to update the input elements
                document.getElementById("idMannequinSelected").value = selectedOption.dataset.id;
                document.getElementById("prenomMannequin").value = selectedOption.dataset.prenom;
                document.getElementById("nomMannequin").value = selectedOption.dataset.nom;
                document.getElementById("poidsMannequin").value = selectedOption.dataset.poids;
                document.getElementById("tailleMannequin").value = selectedOption.dataset.taille;
                document.getElementById("prixPresta").value = selectedOption.dataset.pp;
                document.getElementById("phoneMannequin").value = selectedOption.dataset.phone;
                document.getElementById("adresseMannequin").value = selectedOption.dataset.adresse;
                document.getElementById("codePostal").value = selectedOption.dataset.cp;
                document.getElementById("villeMannequin").value = selectedOption.dataset.ville;
                document.getElementById("pwMannequin1").value = selectedOption.dataset.pw;
                document.getElementById("pwMannequin2").value = selectedOption.dataset.pw;
            }
        </script>   
    </body>
</html>
