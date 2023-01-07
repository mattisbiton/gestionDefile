<%-- 
    Document   : login
    Created on : Dec 19, 2022, 8:11:30 PM
    Author     : matti
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/stylesheetLogin.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <script src="https://kit.fontawesome.com/9340690aa2.js" crossorigin="anonymous"></script>
        <title>Connexion — gestionDefile</title>
    </head>
    <body>
        <div class="navbar">
            <nav>
                <a class="logo" href="home.jsp"><h1>gestion<strong>Defile</strong></h1></a>
            </nav>
        </div>
        
        <c:choose>
            <c:when test="${ !empty sessionScope.roleAdmin }">
                <center><p>Vous êtes déjà connecté. Vous souhaitez changer de rôle ? <a href="logout.jsp">Déconnectez-vous</a>.</p></center>
            </c:when>
            <c:otherwise>
                <% String attribut = (String) request.getAttribute("message"); %>
            
                <div class="content">
                    <div class="login">
                        <div id="noAccountWrapper" class="noAccountWrapper">
                            <div class="active">
                                <p>Connexion</p>
                            </div>
                            <div class="noAccount" onmouseover="hoverColor('gainsboro')" onmouseout="hoverColor('whitesmoke')">
                                <a href="menu.jsp"><p>Espace grand public</p></a>
                            </div>
                        </div>
                        <div class="form">
                            <form method="post" action="servletDefile">
                                <center>
                                    <p>Rôle à sélectionner</p>
                                </center>
                                <div class="role">
                                    <input type="radio" name="radioRole" class="radioRole admin" id="admin" value="admin" onclick="document.getElementById('login').value=this.value">
                                    <label for="admin">Admin</label>
                                    <input type="radio" name="radioRole" class="radioRole mannequin" id="mannequin" value="mannequin" onclick="document.getElementById('login').value=''" checked>
                                    <label for="mannequin">Mannequin</label>
                                    <input type="radio" name="radioRole" class="radioRole couturier" id="couturier" value="couturier" onclick="document.getElementById('login').value=''">
                                    <label for="couturier">Couturier</label>
                                    <input type="radio" name="radioRole" class="radioRole organisateur" id="organisateur" value="organisateur" onclick="document.getElementById('login').value=''">
                                    <label for="organisateur">Organisateur</label>
                                </div>
                                
                                <div class="formInputs">
                                    <div class="input">
                                        <label for="login">Identifiant</label>
                                        <input type="text" id="login" name="login" placeholder="karl.lagerfeld" required>
                                    </div>
                                    <div class="input">
                                        <label for="pw">Mot de passe</label>
                                        <input type="password" id="pw" name="pw" placeholder="********" required>
                                    </div>
                                    <input type="hidden" name="action" value="login">

                                    <center><span id="status"><% out.println(attribut); %></span></center>
                                    <input type="submit" value="Se connecter">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose> 
                
         <script>
            const status = document.getElementById('status');
            
            if(status.textContent.includes("null")) {
                status.style.display = "none";
            } else {
                status.style.display = "";
            }
            
            function hoverColor(color) {
                document.getElementById('noAccountWrapper').style.transition = "background-color .2s ease-in-out";
                document.getElementById('noAccountWrapper').style.backgroundColor = color;
            }
        </script>   
    </body>
</html>
