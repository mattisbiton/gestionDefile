<%-- 
    Document   : editLieu
    Created on : Jan 1, 2023, 4:07:18 PM
    Author     : matti
--%>

<%@page import="entites.Lieu"%>
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
        <title>Modifier un lieu — gestionDefile</title>
        <jsp:useBean id="listeLieux" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'organisateur'}">
            <% List<Lieu> leLieu = listeLieux;%>
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
                <h1>Modifier un lieu</h1>
                <form method="post" action="servletDefile">
                    <center><span id="status"><% out.println(attribut); %></span></center>
                    
                    <div class="formContainer">
                        <div class="left">
                            <label>Lieu à modifier</label>
                            <select id="idLieu" name="idLieu" required onchange="updateInfosLieu()">
                                <option disabled selected value="">Veuillez choisir un lieu</option>
                                <% for (Lieu l : leLieu) { %>
                                    <option value="<%=l.getId()%>"
                                    data-nom="<%=l.getNom()%>" 
                                    data-adresse="<%=l.getAdresse()%>" 
                                    data-cp="<%=l.getCp()%>" 
                                    data-ville="<%=l.getVille()%>" ><%=l.getNom()%> - <%=l.getAdresse()%> <%=l.getCp()%> <%=l.getVille()%></option>
                                <% } %>
                            </select> 

                            <label for="nomLieu">Nom du lieu</label>
                            <input type="text" id="nomLieu" name="nomLieu" required>
                            <label for="adresseLieu">Adresse</label>
                            <input type="text" id="adresseLieu" name="adresseLieu" required>
                        </div>
                        <div class="right">
                            <label for="cpLieu">Code postal</label>
                            <input type="text" id="cpLieu" name="cpLieu" required>
                            <label for="villeLieu">Ville</label>
                            <input type="text" id="villeLieu" name="villeLieu" required>

                            <input type="hidden" name="accountID" value="${sessionScope.accountID}" required>
                            <input type="hidden" name="action" value="submitEditLieu"> 
                            <label>&nbsp;</label>
                            <div class="submitReset">
                                <input type="submit" name="submit" value="Modifier">
                                <input type="submit" name="delete" value="Supprimer"
                                onclick="return confirm('Voulez-vous vraiment supprimer ce lieu ?');">
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
            
            function updateInfosLieu() {
                var selectedOption = document.getElementById("idLieu").selectedOptions[0];

                document.getElementById("nomLieu").value = selectedOption.dataset.nom;
                document.getElementById("adresseLieu").value = selectedOption.dataset.adresse;
                document.getElementById("cpLieu").value = selectedOption.dataset.cp;
                document.getElementById("villeLieu").value = selectedOption.dataset.ville;
            }
        </script>   
    </body>
</html>
