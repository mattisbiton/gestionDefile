<%-- 
    Document   : rechercheDefiles
    Created on : Jan 7, 2023, 10:30:35 AM
    Author     : matti
--%>

<%@page import="java.util.List"%>
<%@page import="entites.Lieu"%>
<%@page import="entites.Couturier"%>
<%@page import="entites.Mannequin"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/stylesheetSearch.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Lieu — gestionDefile</title>
        <jsp:useBean id="listeLieux" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeCouturiers" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="listeMannequins" scope="request" class="java.util.List"></jsp:useBean>
    </head>
    <body>
            <% List<Lieu> leLieu = listeLieux;%>
            <% List<Couturier> leCouturier = listeCouturiers;%>
            <% List<Mannequin> leMannequin = listeMannequins;%>
            <% String attribut = (String) request.getAttribute("message"); %>
            
            <div class="navbar">
                <nav>
                    <a class="logo" href="menu.jsp"><h1>gestion<strong>Defile</strong></h1></a>
                    <c:choose>
                        <c:when test="${!empty sessionScope.roleAccount}">
                            <ul>
                                <li><a class="navbarMenu" href="menu.jsp">Dashboard</a></li>
                                <li><a class="navbarMenu" href="logout.jsp">Déconnexion</a></li>
                                <li><p id="roleAccount">${sessionScope.roleAccount}</p></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <ul>
                                <li><a class="navbarMenu" href="login.jsp">Connexion</a></li>
                                <li><p id="roleAccount">Grand public</p></li>
                            </ul>
                        </c:otherwise>
                    </c:choose>
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
            
            <div class="content">
                <center><h1>Recherche de défilés</h1></center>
                
                <div class="searches">
                    <input type="radio" name="radioSearch" class="radioSearch lieu" id="lieu" value="lieu" onclick="document.getElementById('idLieu').value='';" checked>
                    <label for="lieu">Lieu</label>
                    <input type="radio" name="radioSearch" class="radioSearch date" id="date" value="date" onclick="document.getElementById('input-date').value='';">
                    <label for="date">Date</label>
                    <input type="radio" name="radioSearch" class="radioSearch intervalle" id="intervalle" value="intervalle" onclick="document.getElementById('input-intervalle-date-1').value=''; document.getElementById('input-intervalle-date-2').value='';">
                    <label for="intervalle">Intervalle de dates</label>
                    <input type="radio" name="radioSearch" class="radioSearch couturier" id="couturier" value="couturier" onclick="document.getElementById('idCouturier').value='';">
                    <label for="couturier">Couturier</label>
                    <input type="radio" name="radioSearch" class="radioSearch mannequin" id="mannequin" value="mannequin" onclick="document.getElementById('idMannequin').value='';">
                    <label for="mannequin">Mannequin</label>
                    <input type="radio" name="radioSearch" class="radioSearch cout" id="cout" value="cout" onclick="document.getElementById('input-cout').value=''; document.getElementById('input-delta').value='';">
                    <label for="cout">Coût du défilé</label>
                </div>
                
                <div class="searchBar lieu" id="form-lieu">
                        <form action="servletDefile" method="get" class="form" target="_blank">
                            <select class="search-field" id="idLieu" name="idLieu" required">
                                <option disabled selected value="">Veuillez choisir un lieu dans la liste</option>
                                <% for (Lieu l : leLieu) { %>
                                    <option value="<%=l.getId()%>"><%=l.getNom()%> - <%=l.getAdresse()%> <%=l.getCp()%> <%=l.getVille()%></option>
                                <% } %>
                            </select>
                            <input type="hidden" name="action" value="submitSearchLieu">
                            <button type="submit" name="submit" class="search-button">Rechercher</button>
                        </form>
                </div>
                
                <div class="searchBar date" id="form-date">
                        <form action="servletDefile" method="get" class="form" target="_blank">
                            <input id="input-date" name="input-date" type="date" placeholder="Rechercher des défilés par date..." class="search-field date" />
                            <input type="hidden" name="action" value="submitSearchDate">
                            <button type="submit" class="search-button">Rechercher</button>
                        </form>
                </div>
                
                <div class="searchBar intervalle" id="form-intervalle">
                        <form action="servletDefile" method="get" class="form" target="_blank">
                            <input id="input-intervalle-date-1" name="input-date-1" type="date" class="search-intervalle" />
                            <input id="input-intervalle-date-2" name="input-date-2" type="date" class="search-intervalle">
                            <input type="hidden" name="action" value="submitSearchIntervalle">
                            <button type="submit" class="search-intervalle-button">Rechercher</button>
                        </form>
                </div>
                
                <div class="searchBar couturier" id="form-couturier">
                        <form action="servletDefile" method="get" class="form" target="_blank">
                            <select class="search-field" id="idCouturier" name="idCouturier" required">
                                <option disabled selected value="">Veuillez choisir un couturier dans la liste</option>
                                <% for (Couturier c : leCouturier) { %>
                                    <option value="<%=c.getId()%>"><%=c.getPrenom()%> <%=c.getNom()%> - <%=c.getNomMaisonCouture()%></option>
                                <% } %>
                            </select>
                            <input type="hidden" name="action" value="submitSearchCouturier">
                            <button type="submit" class="search-button">Rechercher</button>
                        </form>
                </div>
                
                <div class="searchBar mannequin" id="form-mannequin">
                        <form action="servletDefile" method="get" class="form" target="_blank">
                            <select class="search-field" id="idMannequin" name="idMannequin" required">
                                <option disabled selected value="">Veuillez choisir un mannequin dans la liste</option>
                                <% for (Mannequin m : leMannequin) { %>
                                    <option value="<%=m.getId()%>"><%=m.getPrenom()%> <%=m.getNom()%></option>
                                <% } %>
                            </select>
                            <input type="hidden" name="action" value="submitSearchMannequin">
                            <button type="submit" class="search-button">Rechercher</button>
                        </form>
                </div>
                
                <div class="searchBar cout" id="form-cout">
                        <form action="servletDefile" method="get" class="form" target="_blank">
                            <input id="input-cout" name="input-cout" type="number" placeholder="Coût approximatif des défilés" min="0" class="search-intervalle" />
                            <input id="input-delta" name="input-delta" type="number" placeholder="À ± un delta" min="0" class="search-intervalle">
                            <input type="hidden" name="action" value="submitSearchCout">
                            <button type="submit" class="search-intervalle-button">Rechercher</button>
                        </form>
                </div>
                
            </div>
        
         <script>
            const radioIds = ['lieu', 'date', 'intervalle', 'couturier', 'mannequin', 'cout'];
            const divIds = ['form-lieu', 'form-date', 'form-intervalle', 'form-couturier', 'form-mannequin', 'form-cout'];

            function showDivForSelectedRadio(radioIds, divIds) {
              const radios = radioIds.map(id => document.getElementById(id));
              const divs = divIds.map(id => document.getElementById(id));

              radios.forEach((radio, index) => {
                radio.addEventListener('change', () => {
                  if (radio.checked) {
                    divs.forEach((div, divIndex) => {
                      if (index === divIndex) {
                        div.style.display = 'inline';
                      } else {
                        div.style.display = 'none';
                      }
                    });
                  }
                });
              });
            }

            const defaultRadio = document.getElementById('lieu');
            if (defaultRadio.checked) {
              document.getElementById('form-lieu').style.display = 'inline';
            }

            showDivForSelectedRadio(radioIds, divIds);
            
            const status = document.getElementById('status');
            
            if(status.textContent.includes("null")) {
                status.style.display = "none";
            } else {
                status.style.display = "";
            }         
            
        </script>   
    </body>
</html>

