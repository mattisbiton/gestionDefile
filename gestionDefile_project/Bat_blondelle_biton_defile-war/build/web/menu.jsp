<%-- 
    Document   : menuAdmin
    Created on : Dec 19, 2022, 8:29:28 PM
    Author     : matti
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0);
//prevents caching at the proxy server
%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/stylesheetMenu.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Dashboard — gestionDefile</title>
    </head>
    <body>
    <c:choose>
        <c:when test="${ !empty sessionScope.roleAccount }">
            <% String attribut = (String) request.getAttribute("message"); %>
            
            <div class="navbar">
                <nav>
                    <a class="logo" href="home.jsp"><h1>gestion<strong>Defile</strong></h1></a>
                    <ul>
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
                    <li><a class="navbarHamburger" href="logout.jsp">Déconnexion</a></li>
                </ul>
            </div>
                    
            <br>                    
                    <c:choose>
                        <c:when test="${sessionScope.roleAccount == 'admin'}">
                            <div class="container">
                                <div class="menu">
                                    <ul>
                                        <li>Dashboard</li>
                                        <li class="item active" id="defiles">Défilés</li>
                                        <li class="item" id="organisateurs">Organisateurs</li>
                                    </ul>
                                </div>
                                <div class="content">
                                    <div class="message">
                                        <h2>Bonjour, ${sessionScope.nameAccount}<span id="surname"> ${sessionScope.surnameAccount}</span></h2>
                                        <span id="status"><% out.println(attribut); %></span>
                                    </div>
                                    <div class="options admin">
                                <a href="servletDefile?action=rechercheDefiles"><div class="cardsContainer active" id="defiles">
                                    <div class="card">
                                        <div class="cardImg">
                                            <img src="img/defile.jpg">
                                        </div>
                                        <div class="cardDesc">
                                            <p>Rechercher un défilé</p>
                                        </div>
                                    </div>
                                </div></a>
                                <div class="cardsContainer" id="organisateurs">
                                    <a href="jsp/create/createOrganisateur.jsp"><div class="card">
                                        <div class="cardImg">
                                            <img src="img/organisation-defile.jpg">
                                        </div>
                                        <div class="cardDesc">
                                            <p>Créer un organisateur</p>
                                        </div>
                                    </div></a>
                                    <a href="servletDefile?action=editOrganisateur"><div class="card">
                                        <div class="cardImg">
                                            <img src="img/editorg.jpg">
                                        </div>
                                        <div class="cardDesc">
                                            <p>Modifier un organisateur</p>
                                        </div>
                                    </div></a>
                                    <a href="servletDefile?action=lesOrganisateurs"><div class="card">
                                        <div class="cardImg">
                                            <img src="img/lesorgas.jpg">
                                        </div>
                                        <div class="cardDesc">
                                            <p>Tous les organisateurs</p>
                                        </div>
                                    </div></a>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${sessionScope.roleAccount == 'mannequin'}">
                            <div class="container">
                                <div class="menu">
                                    <ul>
                                        <li>Dashboard</li>
                                        <li class="item active" id="defiles">Défilés</li>
                                        <li class="item" id="vetements">Vêtements</li>
                                    </ul>
                                </div>
                                <div class="content">
                                    <div class="message">
                                        <h2>Bonjour, ${sessionScope.nameAccount}<span id="surname"> ${sessionScope.surnameAccount}</span></h2>
                                        <span id="status"><% out.println(attribut); %></span>
                                    </div>
                                    <div class="options mannequin">
                                <a href="servletDefile?action=rechercheDefiles"><div class="cardsContainer active" id="defiles">
                                    <div class="card">
                                        <div class="cardImg">
                                            <img src="img/defile.jpg">
                                        </div>
                                        <div class="cardDesc">
                                            <p>Rechercher un défilé</p>
                                        </div>
                                    </div>
                                </div></a>
                                <div class="cardsContainer" id="vetements">
                                    <a href="servletDefile?action=showVetementsAccessoires"><div class="card">
                                        <div class="cardImg">
                                            <img src="img/vetements.jpg">
                                        </div>
                                        <div class="cardDesc">
                                            <p>Vêtements & accessoires</p>
                                        </div>
                                    </div></a>
                                </div>
                            </div>                            
                        </c:when>
                        <c:when test="${sessionScope.roleAccount == 'couturier'}">
                            <div class="container">
                                <div class="menu">
                                    <ul>
                                        <li>Dashboard</li>
                                        <li class="item active" id="defiles">Défilés</li>
                                        <li class="item" id="mannequins">Mannequins</li>
                                        <li class="item" id="vetements">Vêtements</li>
                                        <li class="item" id="accessoires">Accessoires</li>
                                    </ul>
                                </div>
                                <div class="content">
                                    <div class="message">
                                        <h2>Bonjour, ${sessionScope.nameAccount}<span id="surname"> ${sessionScope.surnameAccount}</span></h2>
                                        <span id="status"><% out.println(attribut); %></span>
                                    </div>
                                    <div class="options couturier">
                                        <div class="cardsContainer active" id="defiles">
                                            <a href="servletDefile?action=rechercheDefiles"><div class="card">
                                                <div class="cardImg">
                                                    <img src="img/defile.jpg">
                                                </div>
                                                <div class="cardDesc">
                                                    <p>Rechercher un défilé</p>
                                                </div>
                                            </div></a>
                                            <a href="servletDefile?action=editOrdre"><div class="card">
                                                <div class="cardImg">
                                                    <img src="img/ordres.jpg">
                                                </div>
                                                <div class="cardDesc">
                                                    <p>Modifier les ordres</p>
                                                </div>
                                            </div></a>
                                        </div>
                                        <div class="cardsContainer" id="mannequins">
                                            <a href="jsp/create/createMannequin.jsp"><div class="card">
                                                <div class="cardImg">
                                                    <img src="img/mannequin.jpg">
                                                </div>
                                                <div class="cardDesc">
                                                    <p>Créer un mannequin</p>
                                                </div>
                                            </div></a>
                                            <a href="servletDefile?action=editMannequin"><div class="card">
                                                <div class="cardImg">
                                                    <img src="img/photoshoot.jpg">
                                                </div>
                                                <div class="cardDesc">
                                                    <p>Modifier un mannequin</p>
                                                </div>
                                                </div>
                                            </a>
                                            <a href="servletDefile?action=mannequinsCouturier"><div class="card">
                                                <div class="cardImg">
                                                    <img src="img/mannequins.jpg">
                                                </div>
                                                <div class="cardDesc">
                                                    <p>Tous mes mannequins</p>
                                                </div>
                                                </div>
                                            </a>
                                        </div>
                                        <div class="cardsContainer" id="vetements">
                                            <a href="servletDefile?action=createVetement"><div class="card">
                                                <div class="cardImg">
                                                    <img src="img/sewing.jpg">
                                                </div>
                                                <div class="cardDesc">
                                                    <p>Créer un vêtement</p>
                                                </div>
                                            </div></a>
                                            <a href="servletDefile?action=editVetement"><div class="card">
                                                <div class="cardImg">
                                                    <img src="img/vetements.jpg">
                                                </div>
                                                <div class="cardDesc">
                                                    <p>Modifier un vêtement</p>
                                                </div>
                                            </div></a>
                                            <a href="servletDefile?action=vetementsCouturier">
                                                <div class="card">
                                                    <div class="cardImg">
                                                        <img src="img/vetements2.jpg">
                                                    </div>
                                                    <div class="cardDesc">
                                                        <p>Tous mes vêtements</p>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                        <div class="cardsContainer" id="accessoires">
                                            <a href="jsp/create/createAccessoire.jsp"><div class="card">
                                                <div class="cardImg">
                                                    <img src="img/accessory.jpg">
                                                </div>
                                                <div class="cardDesc">
                                                    <p>Créer un accessoire</p>
                                                </div>
                                            </div></a>
                                            <a href="servletDefile?action=editAccessoire"><div class="card">
                                                <div class="cardImg">
                                                    <img src="img/accessories.jpg">
                                                </div>
                                                <div class="cardDesc">
                                                    <p>Modifier un accessoire</p>
                                                </div>
                                            </div></a>
                                            <a href="servletDefile?action=accessoiresCouturier"><div class="card">
                                                <div class="cardImg">
                                                    <img src="img/lesaccessoires.jpg">
                                                </div>
                                                <div class="cardDesc">
                                                    <p>Tous mes accessoire</p>
                                                </div>
                                            </div></a>
                                        </div>
                                    </div>
                        </c:when>
                        <c:when test="${sessionScope.roleAccount == 'organisateur'}">
                            <div class="container">
                                <div class="menu">
                                    <ul>
                                        <li>Dashboard</li>
                                        <li class="item active" id="defiles">Défilés</li>
                                        <li class="item" id="lieux">Lieux</li>
                                        <li class="item" id="couturiers">Couturiers</li>                                        
                                    </ul>
                                </div>
                                <div class="content">
                                    <div class="message">
                                        <h2>Bonjour, ${sessionScope.nameAccount}<span id="surname"> ${sessionScope.surnameAccount}</span></h2>
                                        <span id="status"><% out.println(attribut); %></span>
                                    </div>
                                    <div class="options organisateur">
                                        <a href="servletDefile?action=rechercheDefiles"><div class="cardsContainer active" id="defiles">
                                    <div class="card">
                                        <div class="cardImg">
                                            <img src="img/defile.jpg">
                                        </div>
                                        <div class="cardDesc">
                                            <p>Rechercher un défilé</p>
                                        </div>
                                    </div></a>
                                    <a href="servletDefile?action=createDefile"><div class="card">
                                        <div class="cardImg">
                                            <img src="img/defile2.jpg">
                                        </div>
                                        <div class="cardDesc">
                                            <p>Créer un défilé</p>
                                        </div>
                                    </div></a>
                                    <a href="servletDefile?action=editDefile"><div class="card">
                                        <div class="cardImg">
                                            <img src="img/backstage.jpg">
                                        </div>
                                        <div class="cardDesc">
                                            <p>Modifier un défilé</p>
                                        </div>
                                    </div></a>
                                    <a href="servletDefile?action=defilesOrganisateur"><div class="card">
                                        <div class="cardImg">
                                            <img src="img/lesdefiles.jpg">
                                        </div>
                                        <div class="cardDesc">
                                            <p>Tous mes défilés</p>
                                        </div>
                                    </div></a>
                                </div>
                                <div class="cardsContainer" id="lieux">
                                    <a href="jsp/create/createLieu.jsp">
                                        <div class="card">
                                            <div class="cardImg">
                                                <img src="img/location.jpg">
                                            </div>
                                            <div class="cardDesc">
                                                <p>Créer un lieu</p>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="servletDefile?action=editLieu">
                                        <div class="card">
                                            <div class="cardImg">
                                                <img src="img/editlieu.jpg">
                                            </div>
                                            <div class="cardDesc">
                                                <p>Modifier un lieu</p>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="servletDefile?action=lieuxOrganisateur">
                                        <div class="card">
                                            <div class="cardImg">
                                                <img src="img/pinsmap.jpg">
                                            </div>
                                            <div class="cardDesc">
                                                <p>Tous mes lieux</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="cardsContainer" id="couturiers">
                                    <a href="jsp/create/createCouturier.jsp"><div class="card">
                                        <div class="cardImg">
                                            <img src="img/couturier.jpg">
                                        </div>
                                        <div class="cardDesc">
                                            <p>Créer un couturier</p>
                                        </div>
                                    </div></a>
                                    <a href="servletDefile?action=editCouturier"><div class="card">
                                        <div class="cardImg">
                                            <img src="img/couturier2.jpg">
                                        </div>
                                        <div class="cardDesc">
                                            <p>Modifier un couturier</p>
                                        </div>
                                    </div></a>
                                    <a href="servletDefile?action=couturiersOrganisateur"><div class="card">
                                        <div class="cardImg">
                                            <img src="img/lescouturiers.jpg">
                                        </div>
                                        <div class="cardDesc">
                                            <p>Tous les couturiers</p>
                                        </div>
                                    </div></a>
                                </div>
                            </div>                           
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="navbar">
                <nav>
                    <a class="logo" href="home.jsp"><h1>gestion<strong>Defile</strong></h1></a>
                    <ul>
                        <li><a class="navbarMenu" href="login.jsp">Connexion</a></li>
                        <li><p><span id="roleAccount">Grand public</span></p></li>
                    </ul>
                </nav>
            </div>
            
            <div class="hamburger-menu">
                <input id="menu__toggle" type="checkbox" />
                <label class="menu__btn" for="menu__toggle">
                <span></span>
                </label>

                <ul class="menu__box">
                    <li><p><span id="roleAccountHamburger">Grand public</span></p></li>
                    <li><a class="navbarHamburger" href="login.jsp">Connexion</a></li>
                </ul>
            </div>
            
            <br>
            
            <div class="container">
                <div class="menu">
                    <ul>
                        <li>Dashboard</li>
                        <li class="item active" id="defiles">Défilés</li>                                        
                    </ul>
                </div>
                <div class="content">
                    <div class="message">
                        <h2>Bonjour, invité</h2>
                    </div>
                    <div class="options">
                <a href="servletDefile?action=rechercheDefiles"><div class="cardsContainer active" id="defiles">
                            <div class="card">
                                <div class="cardImg">
                                    <img src="img/defile.jpg">
                                </div>
                                <div class="cardDesc">
                                    <p>Rechercher un défilé</p>
                                </div>
                            </div>
                            </div></a>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
    
    <script>
        const menuItems = document.querySelectorAll('.menu li');

        menuItems.forEach(item => {
          item.addEventListener('click', handleClick);
        });
        
        function handleClick(event) {
            const id = event.target.getAttribute('id');
            const cardsContainers = document.querySelectorAll('.cardsContainer');
            cardsContainers.forEach(container => {
              if (container.id === id) {
                container.classList.add('active');
              } else {
                container.classList.remove('active');
              }
            });
            
            const liElements = document.querySelectorAll('li');
            liElements.forEach(li => {
              li.classList.remove('active');
            });

            event.target.classList.add('active');
        }

        const sessionSurname = document.getElementById('surname');
        
        if(sessionSurname.textContent.length < 2) {
            sessionSurname.style.display = "none";
        } else {
            sessionSurname.style.display = "contents";
        }
        
        const status = document.getElementById('status');

        if(status.textContent.includes("null")) {
            status.style.display = "none";
        } else {
            status.style.display = "";
        }        
    </script>
    </body>
</html>
