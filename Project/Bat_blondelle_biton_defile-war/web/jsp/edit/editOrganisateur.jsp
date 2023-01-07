<%-- 
    Document   : editOrganisateur
    Created on : Jan 1, 2023, 5:20:59 PM
    Author     : matti
--%>

<%@page import="entites.Organisateur"%>
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
        <title>Modifier un organisateur — gestionDefile</title>
        <jsp:useBean id="listeOrganisateurs" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'admin' || sessionScope.roleAccount == 'organisateur' }">
            <% List<Organisateur> leOrganisateur = listeOrganisateurs;%>
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
                    <li><a class="navbarHamburger" href="menu.jsp">Dashboard</a></li>
                    <li><a class="navbarHamburger" href="logout.jsp">Déconnexion</a></li>
                </ul>
            </div>
            
            <div class="creationForm">
                <h1>Modifier un organisateur</h1>
                <form method="post" action="servletDefile">
                    <center><span id="status"><% out.println(attribut); %></span></center>
                    
                    <div class="formContainer">
                        <div class="left">
                            <label>Organisateur à modifier</label>
                            <select id="idOrganisateur" name="idOrganisateur" required onchange="updateInfosOrganisateur()">
                                <option disabled selected value="">Veuillez choisir un organisateur</option>
                                <% for (Organisateur o : leOrganisateur) { %>
                                    <option value="<%=o.getId()%>"
                                    data-prenom="<%=o.getPrenom()%>"
                                    data-nom="<%=o.getNom()%>" 
                                    data-societe="<%=o.getNomSociete()%>"
                                    data-adresse="<%=o.getAdresse()%>" 
                                    data-cp="<%=o.getCp()%>" 
                                    data-ville="<%=o.getVille()%>" 
                                    data-phone="<%=o.getTelephone()%>"
                                    data-pw="<%=o.getMdp()%>"><%=o.getPrenom()%> <%=o.getNom()%> - <%=o.getNomSociete()%></option>
                                <% } %>
                            </select> 

                            <label for="prenomOrganisateur">Prénom</label>
                            <input type="text" id="prenomOrganisateur" name="prenomOrganisateur" required>
                            <label for="nomOrganisateur">Nom</label>
                            <input type="text" id="nomOrganisateur" name="nomOrganisateur" required>
                            <label for="societeOrganisateur">Société</label>
                            <input type="text" id="societeOrganisateur" name="societeOrganisateur" required>
                            <label for="phoneOrganisateur">Téléphone</label>
                            <input type="tel" id="phoneOrganisateur" name="phoneOrganisateur" required>
                        </div>
                        <div class="right">
                            <label for="adresseOrganisateur">Adresse</label>
                            <input type="text" id="adresseOrganisateur" name="adresseOrganisateur" required>
                            <label for="codePostal">Code Postal</label>
                            <input type="text" id="codePostal" name="codePostal" required>
                            <label for="villeOrganisateur">Ville</label>
                            <input type="text" id="villeOrganisateur" name="villeOrganisateur" required>
                            <label for="pwOrganisateur1">Mot de passe</label>
                            <input type="text" id="pwOrganisateur1" name="pwOrganisateur1" required autocomplete="off">
                            <label for="pwOrganisateur2">Confirmation mot de passe</label>
                            <input type="text" id="pwOrganisateur2" name="pwOrganisateur2" required autocomplete="off">
                            

                            <input type="hidden" name="action" value="submitEditOrganisateur">     
                            <label>&nbsp;</label>
                            <div class="submitReset">
                                <input type="submit" name="submit" value="Modifier"
                                onclick="return checkPasswords()">
                                <input type="submit" name="delete" value="Supprimer"
                                onclick="return confirm('Voulez-vous vraiment supprimer ce couturier ?');">
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
            
            function checkPasswords() {
                var pw1 = document.getElementById("pwOrganisateur1").value;
                var pw2 = document.getElementById("pwOrganisateur2").value;
                
                if(pw1 !== pw2) {
                    alert("Les mots de passe ne correspondent pas. Veuillez vérifier les informations.");
                    return false;
                }
                
                return true;
            }
            
            function updateInfosOrganisateur() {
                var selectedOption = document.getElementById("idOrganisateur").selectedOptions[0];

                document.getElementById("prenomOrganisateur").value = selectedOption.dataset.prenom;
                document.getElementById("nomOrganisateur").value = selectedOption.dataset.nom;
                document.getElementById("societeOrganisateur").value = selectedOption.dataset.societe;
                document.getElementById("phoneOrganisateur").value = selectedOption.dataset.phone;
                document.getElementById("adresseOrganisateur").value = selectedOption.dataset.adresse;
                document.getElementById("codePostal").value = selectedOption.dataset.cp;
                document.getElementById("villeOrganisateur").value = selectedOption.dataset.ville;
                document.getElementById("pwOrganisateur1").value = selectedOption.dataset.pw;
                document.getElementById("pwOrganisateur2").value = selectedOption.dataset.pw;
            }
        </script>   
    </body>
</html>
