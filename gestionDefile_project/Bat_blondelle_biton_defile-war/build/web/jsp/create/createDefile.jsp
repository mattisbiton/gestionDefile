<%-- 
    Document   : createDefile
    Created on : Dec 28, 2022, 1:23:22 PM
    Author     : matti
--%>

<%@page import="entites.Lieu"%>
<%@page import="entites.Couturier"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/stylesheetCreate.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <script src="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag/dist/js/multi-select-tag.js"></script>
        <title>Créer un défilé — gestionDefile</title>
        <jsp:useBean id="listeLieux" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeInvit" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'organisateur'}">
            <% List<Lieu> leLieu = listeLieux;%>
            <% List<Couturier> leCouturier = listeInvit;%>
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
                <h1>Créer un défilé</h1>
                <form method="post" action="servletDefile">
                    <center><span id="status"><% out.println(attribut); %></span></center>
                    <div class="formContainer">
                        <div class="left">
                            <label for="dateDefile">Date du défilé<span class="required">*</span></label>
                            <input type="date" name="dateDefile">
                            <label for="nomDefile">Nom du défilé<span class="required">*</span></label>
                            <input type="text" name="nomDefile" required placeholder="Chanel - Collection Automne/Hiver 2022">
                            <label for="idLieu">Lieu<span class="required">*</span></label>
                            <select name="idLieu">
                                <option class="disabled" value="" disabled selected>Veuillez choisir un lieu</option>
                                <% for (Lieu l :leLieu) {%>
                                    <option value ="<%=l.getId()%>"><%=l.getNom()%> - <%=l.getAdresse()%> <%=l.getCp()%> <%=l.getVille()%></option>
                                <% }%>
                            </select>
                            
                        </div>
                        <div class="right">
                            <label for="idCouturier">Couturier<span class="required">*</span></label>
                            <select name="idCouturier">
                                <option value="" disabled selected>Veuillez choisir un couturier</option>
                                <% for (Couturier c :leCouturier) {%>
                                    <option value ="<%=c.getId()%>"><%=c.getPrenom()%> <%=c.getNom()%></option>
                                <% }%>
                            </select>
                            <label for="idInvites">Invités</label>
                            <select id="idInvites" name="idInvites" multiple>
                                <% for (Couturier c :leCouturier) {%>
                                    <option value ="<%=c.getId()%>"><%=c.getPrenom()%> <%=c.getNom()%></option>
                                <% }%>
                            </select>
                            <input type="hidden" name="accountID" value="${sessionScope.accountID}" required>
                            
                            <input type="hidden" name="action" value="submitDefile">
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
            new MultiSelectTag('idInvites');
            
            const status = document.getElementById('status');
            
            if(status.textContent.includes("null")) {
                status.style.display = "none";
            } else {
                status.style.display = "";
            }
        </script>   
    </body>
</html>