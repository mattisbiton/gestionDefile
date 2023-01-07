<%-- 
    Document   : home
    Created on : Dec 19, 2022, 8:07:48 PM
    Author     : matti
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/stylesheetHome.css" rel="stylesheet">
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>👗</text></svg>">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Accueil — gestionDefile</title>
    </head>
    <body>
        <div class="wrapper">
            <div class="navbar" id="home">
                <nav>
                    <a class="logo" href="home.jsp"><h1>gestion<strong>Defile</strong></h1></a>

                    <c:choose>
                        <c:when test="${ !empty sessionScope.roleAccount }">
                            <ul>
                               <li><a class="navbarMenu" href="menu.jsp">Dashboard</a></li>
                               <li><a class="navbarMenu" href="logout.jsp">Déconnexion</a></li>
                               <li><p id="roleAccount">${sessionScope.roleAccount}</p></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <ul>
                                <li><a class="navbarMenu" href="menu.jsp">Accès public</a></li>
                                <li><a class="navbarMenu" href="login.jsp">Connexion</a></li>
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

                    <c:choose>
                        <c:when test="${ !empty sessionScope.roleAccount }">
                            <ul class="menu__box">
                               <li><p><span id="roleAccountHamburger">${sessionScope.roleAccount}</span></p></li>
                               <li><a class="navbarHamburger" href="menu.jsp">Dashboard</a></li>
                               <li><a class="navbarHamburger" href="logout.jsp">Déconnexion</a></li>

                            </ul>
                        </c:when>
                        <c:otherwise>
                            <ul class="menu__box">
                                <li><a class="navbarHamburger" href="menu.jsp">Accès public</a></li>
                                <li><a class="navbarHamburger" href="login.jsp">Connexion</a></li>
                            </ul>
                        </c:otherwise>
                    </c:choose>
            </div> 

            <header>
                <img id="behind" src="img/behind.png" class="background1">
                <img id="topleft" src="img/topleft.png" class="background2">
                <img id="bottomright" src="img/bottomright.png" class="foreground">
                <div class="header-title">
                    <p class="reveal">Votre outil de gestion de défilés</p>
                    <h1 name="title">gestion<strong>Defile</strong></h1>
                </div>
                <a href="#about">↓ En savoir plus</a>
            </header>

            <div class="about" id="about">
                    <p><b>gestionDefile</b> est un outil dédié à la gestion de défilés de mode. Il est destiné aux professionnels du secteur, tels que les couturiers, les organisateurs et les mannequins, qui peuvent y accéder pour gérer au mieux leurs activités. Un accès public est également disponible pour que les visiteurs puissent consulter les défilés à venir. <b>gestionDefile</b> vous permet de gérer votre carrière de manière efficace et professionnelle. Connectez-vous dès maintenant !</p>
            </div>
            <div class="roles">
                <div class="person-role right reveal" id="public">
                    <div class="role-icon">
                        <img style="height:400px;aspect-ratio:1;" src="img/Browsing.png" alt="Personne sur son téléphone">
                    </div>
                    <div class="role-desc">
                        <p><span class=logo>gestion<strong>Defile</strong></span> est un outil de gestion de défilés de mode destiné aux professionnels du secteur, mais qui est également accessible au grand public. Même sans créer de compte, les visiteurs peuvent accéder à la recherche de défilés et consulter des informations sur les défilés à venir ou passés.

                        En utilisant notre outil de recherche, les visiteurs peuvent trouver des défilés en fonction de leur date, de leur lieu, du couturier et des mannequins associés. Nous mettons à disposition de nombreuses informations sur chaque défilé, pour que tout un chacun puisse en connaître tous les détails et ne manque aucun événement.</p>
                    </div>
                </div>
                <div class="person-role left reveal" id="mannequin">
                    <div class="role-desc">
                        <p><span class="logo">gestion<strong>Defile</strong></span> est l'outil idéal pour les mannequins souhaitant bien s'organiser. En donnant accès à toutes les informations qui les concernent directement, comme les vêtements et les accessoires qu'ils devront porter lors d'un défilé ainsi que la position du vêtement dans le défilé, il est impossible de perdre le fil. Toutes les informations sont facilement retrouvables d'un simple coup d'œil. L'outil a été conçu pour être le plus simple et le plus pratique possible, afin que les mannequins puissent se concentrer sur ce qui compte le plus : leur travail.</p>
                    </div>   
                    <div class="role-icon">
                        <img src="img/Sitting.png" style="height:400px;aspect-ratio:1.61/2;" alt="Personne sur son ordinateur">
                    </div>             
                </div>
                <div class="person-role right reveal" id="couturier">
                    <div class="role-icon">
                        <img src="img/Standing.png" style="height:400px;aspect-ratio:1.61/2;" alt="Personne debout avec un livre">
                    </div>
                    <div class="role-desc">
                        <p><span class=logo>gestion<strong>Defile</strong></span> est un outil essentiel pour les couturiers souhaitant bien gérer leurs défilés. L'outil donne accès à toutes les informations concernant leurs défilés, comme l'ajout, la modification et la suppression de mannequins, de vêtements et d'accessoires, ainsi que l'ordre d'apparition des vêtements.

                        Les couturiers peuvent également rechercher des mannequins, des vêtements ou des accessoires existants. Pour chaque vêtement, sont affichées toutes les informations qui lui sont associées, ainsi que son ordre d'apparition dans le défilé. Le coût du vêtement est également consultable.

                        <span class=logo>gestion<strong>Defile</strong></span> est le meilleur outil pour aider les couturiers à mieux gérer leur travail de manière efficace et professionnelle.</p>
                    </div>
                </div>
                <div class="person-role left reveal" id="organisateur">
                    <div class="role-desc">
                        <p>Pour les organisateurs, <span class=logo>gestion<strong>Defile</strong></span> donne accès à toutes les fonctionnalités nécessaires pour créer et modifier tout ce qui concerne les défilés qu'ils organisent — comme les dates, les lieux et les couturiers invités.

                        Les organisateurs peuvent également consulter le coût total du défilé grâce à l'outil. <span class=logo>gestion<strong>Defile</strong></span> fournit tous les outils nécessaires pour aider les acteurs dans leur travail d'organisation de défilés de mode.</p>
                    </div>
                    <div class="role-icon">
                        <img src="img/Planning.png" style="width:360px;aspect-ratio:1;" alt="Personne devant un calendrier géant">
                    </div>
                </div>
            </div>
            <footer>
                <a href="#home"><p class="logo">gestion<strong>Defile</strong></p></a>
                <p>Copyright © 2023 C. BAT - M. BITON - A. BLONDELLE</p>
            </footer>
        </div>
        
        <script>
            document.querySelectorAll('a[href^="#"]').forEach(anchor => {
                anchor.addEventListener('click', function (e) {
                    e.preventDefault();

                    document.querySelector(this.getAttribute('href')).scrollIntoView({
                        behavior: 'smooth'
                    });
                });
            });
            /* _________ */

            const ratio = .1
            const options = {
                root: null,
                rootMargin: '0px',
                threshold: ratio
            };
            
            const handleIntersect = function (entries, observer) {
                entries.forEach(function (entry) {
                    if(entry.intersectionRatio > ratio) {
                        entry.target.classList.add('active')
                        observer.unobserve(entry.target)
                    }
                })
            }

            const observer = new IntersectionObserver(handleIntersect, options);
            document.querySelectorAll(".reveal").forEach(function (r) {
                observer.observe(r)
            });
            
            /* _________ */
            
            var stepSize = 2;
            var counter = 0;

            // Function to animate the image
            function floatImageBg() {
              counter += stepSize;
              var newPosY = 1 + 1 * Math.sin(counter / 100);
              document.getElementById('behind').style.top = newPosY + '%';
              document.getElementById('topleft').style.top = (15 - newPosY) + '%';
            }
            
            setInterval(floatImageBg, 30);
            
            function floatImageFg() {
              counter += (stepSize - .5);
              var newPosY = 40 - (1 - 1 * Math.sin(counter / 100));
              document.getElementById('bottomright').style.top = newPosY + '%';
            }

            setInterval(floatImageFg, 20);
        </script>
    </body>
</html>
