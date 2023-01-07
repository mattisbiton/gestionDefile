<%-- 
    Document   : editCouturier
    Created on : Jan 1, 2023, 4:34:44 PM
    Author     : matti
--%>

<%@page import="entites.Couturier"%>
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
        <title>Modifier un couturier — gestionDefile</title>
        <jsp:useBean id="listeCouturiers" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'admin' || sessionScope.roleAccount == 'organisateur' }">
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
            
            <div class="creationForm">
                <h1>Modifier un couturier</h1>
                <form method="post" action="servletDefile">
                    <center><span id="status"><% out.println(attribut); %></span></center>
                    
                    <div class="formContainer">
                        <div class="left">
                            <label for="idCouturier">Couturier à modifier</label>
                            <select id="idCouturier" name="idCouturier" required onchange="updateInfosCouturier()">
                                <option disabled selected value="">Veuillez choisir un couturier</option>
                                <% for (Couturier c : leCouturier) { %>
                                    <option value="<%=c.getId()%>"
                                    data-prenom="<%=c.getPrenom()%>"
                                    data-nom="<%=c.getNom()%>" 
                                    data-maison="<%=c.getNomMaisonCouture()%>"
                                    data-adresse="<%=c.getAdresse()%>" 
                                    data-cp="<%=c.getCp()%>" 
                                    data-ville="<%=c.getVille()%>" 
                                    data-phone="<%=c.getTelephone()%>"
                                    data-pw="<%=c.getMdp()%>"><%=c.getPrenom()%> <%=c.getNom()%> - <%=c.getNomMaisonCouture()%></option>
                                <% } %>
                            </select> 

                            <label for="prenomCouturier">Prénom</label>
                            <input type="text" id="prenomCouturier" name="prenomCouturier" required>
                            <label for="nomCouturier">Nom</label>
                            <input type="text" id="nomCouturier" name="nomCouturier" required>
                            <label for="maisonCouturier">Maison de couture</label>
                            <input type="text" id="maisonCouturier" name="maisonCouturier" required>
                            <label for="phoneCouturier">Téléphone</label>
                            <input type="tel" id="phoneCouturier" name="phoneCouturier" required>
                        </div>
                        <div class="right">
                            <label for="adresseCouturier">Adresse</label>
                            <input type="text" id="adresseCouturier" name="adresseCouturier" required>
                            <label for="cpCouturier">Code Postal</label>
                            <input type="text" id="cpCouturier" name="cpCouturier" required>
                            <label for="villeCouturier">Ville</label>
                            <input type="text" id="villeCouturier" name="villeCouturier" required>
                            <label for="pwCouturier1">Mot de passe</label>
                            <input type="text" id="pwCouturier1" name="pwCouturier1" required autocomplete="off">
                            <label for="pwCouturier2">Confirmation mot de passe</label>
                            <input type="text" id="pwCouturier2" name="pwCouturier2" required autocomplete="off">
                            

                            <input type="hidden" name="action" value="submitEditCouturier">     
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
                var pw1 = document.getElementById("pwCouturier1").value;
                var pw2 = document.getElementById("pwCouturier2").value;
                
                if(pw1 !== pw2) {
                    alert("Les mots de passe ne correspondent pas. Veuillez vérifier les informations.");
                    return false;
                }
                
                return true;
            }
            
            function updateInfosCouturier() {
                var selectedOption = document.getElementById("idCouturier").selectedOptions[0];

                document.getElementById("prenomCouturier").value = selectedOption.dataset.prenom;
                document.getElementById("nomCouturier").value = selectedOption.dataset.nom;
                document.getElementById("maisonCouturier").value = selectedOption.dataset.maison;
                document.getElementById("phoneCouturier").value = selectedOption.dataset.phone;
                document.getElementById("adresseCouturier").value = selectedOption.dataset.adresse;
                document.getElementById("cpCouturier").value = selectedOption.dataset.cp;
                document.getElementById("villeCouturier").value = selectedOption.dataset.ville;
                document.getElementById("pwCouturier1").value = selectedOption.dataset.pw;
                document.getElementById("pwCouturier2").value = selectedOption.dataset.pw;
            }
        </script>   
    </body>
</html>
