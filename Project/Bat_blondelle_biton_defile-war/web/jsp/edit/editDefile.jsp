<%-- 
    Document   : editDefile
    Created on : Jan 2, 2023, 12:23:14 PM
    Author     : matti
--%>

<%@page import="java.util.Arrays"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="entites.Lieu"%>
<%@page import="entites.Couturier"%>
<%@page import="entites.Defile"%>
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
        <title>Modifier un défilé — gestionDefile</title>
        <script src="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag/dist/js/multi-select-tag.js"></script>
        <jsp:useBean id="listeDefiles" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeLieux" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeInvit" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
        <c:choose>
        <c:when test="${sessionScope.roleAccount == 'organisateur'}">
            <% List<Defile> leDefile = listeDefiles;%>
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
                <h1>Modifier un défilé</h1>
                <form method="post" action="servletDefile">
                    <center><span id="status"><% out.println(attribut); %></span></center>
                    
                    <div class="formContainer">
                        <div class="left">
                            <label for="idDefile">Défilé à modifier</label>
                            <select id="idDefile" name="idDefile" required onchange="updateInfosDefile()">
                                <option disabled selected value="">Veuillez choisir un défilé</option>
                                <% for (Defile d : leDefile) { %>
                                    <% Date date = d.getDateDefile();
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    String dateString = formatter.format(date);
                                    int size = d.getLesInvites().size();
                                    List<Couturier> listeCouturiersInvites = d.getLesInvites();
                                    String[] couturierIds = new String[listeCouturiersInvites.size()];
                                    for (int i = 0; i < listeCouturiersInvites.size(); i++) {
                                        Couturier couturier = listeCouturiersInvites.get(i);
                                        couturierIds[i] = Long.toString(couturier.getId());
                                    }%>
                                    <option value="<%=d.getId()%>"
                                    data-nom="<%=d.getNomDefile()%>"
                                    data-date="<%=dateString%>"
                                    data-lieu="<%=d.getLeLieu().getId()%>"
                                    data-couturier="<%=d.getLeCouturier().getId()%>"
                                    data-invites="<%=Arrays.toString(couturierIds)%>"><%=d.getNomDefile()%></option>
                                <% } %>
                            </select>
                            <label for="dateDefile">Date du défilé<span class="required">*</span></label>
                            <input type="date" id="dateDefile" name="dateDefile">
                            <label for="nomDefile">Nom du défilé<span class="required">*</span></label>
                            <input type="text" id="nomDefile" name="nomDefile" required placeholder="Chanel - Collection Automne/Hiver 2022">
                            
                        </div>
                        <div class="right">
                            <label for="idLieu">Lieu<span class="required">*</span></label>
                            <select name="idLieu" id="idLieu">
                                <option class="disabled" value="" disabled selected>Veuillez choisir un lieu</option>
                                <% for (Lieu l :leLieu) {%>
                                    <option value ="<%=l.getId()%>"><%=l.getNom()%> - <%=l.getAdresse()%> <%=l.getCp()%> <%=l.getVille()%></option>
                                <% }%>
                            </select>
                            <label for="idCouturier">Couturier<span class="required">*</span></label>
                            <select name="idCouturier" id="idCouturier">
                                <option value="" disabled selected>Veuillez choisir un couturier</option>
                                <% for (Couturier c :leCouturier) {%>
                                    <option value ="<%=c.getId()%>"><%=c.getPrenom()%> <%=c.getNom()%></option>
                                <% }%>
                            </select>
                            <label for="idInvites">Invités - maintenir ctrl pour la sélection</label>
                            <select id="idInvites" name="idInvites" multiple>
                                <% for (Couturier c :leCouturier) {%>
                                    <option value ="<%=c.getId()%>"><%=c.getPrenom()%> <%=c.getNom()%></option>
                                <% }%>
                            </select>
                            <input type="hidden" name="accountID" value="${sessionScope.accountID}" required>
                            <input type="hidden" name="action" value="submitEditDefile"> 
                            <label>&nbsp;</label>
                            <div class="submitReset">
                                <input type="submit" name="submit" value="Modifier">
                                <input type="submit" name="delete" value="Supprimer"
                                onclick="return confirm('Voulez-vous vraiment supprimer ce défilé ?');">
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
            
            
            function updateInfosDefile() {
                var selectedOption = document.getElementById("idDefile").selectedOptions[0];
                console.log(selectedOption.dataset.invites);

                document.getElementById("dateDefile").value = selectedOption.dataset.date;
                document.getElementById("nomDefile").value = selectedOption.dataset.nom;
                document.getElementById("idLieu").value = selectedOption.dataset.lieu;
                document.getElementById("idCouturier").value = selectedOption.dataset.couturier;
                
                var element = document.getElementById("idInvites"); 
                var values = selectedOption.dataset.invites;
                for (var i = 0; i < element.options.length; i++) {
                    element.options[i].selected = values.indexOf(element.options[i].value) >= 0;
                }
            }
        </script>   
    </body>
</html>
