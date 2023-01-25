# gestionDefile

Demo : https://youtu.be/RNBxnBmg_n0.

🇺🇸 Java group project made as part of the M2 Advanced Information Systems of iaelyon School of Management. Made by [Chloé BAT](https://github.com/batchloe), [Mattis BITON](https://github.com/mattisbiton/) & [Alexandre BLONDELLE](https://github.com/ablonlex).

🇫🇷 gestionDefile est un projet de groupe réalisé par [Chloé BAT](https://github.com/batchloe), [Mattis BITON](https://github.com/mattisbiton/) et [Alexandre BLONDELLE](https://github.com/ablonlex) dans le cadre du Master 2 Systèmes d'Information Avancés de l'iaelyon School of Management.

## Table des matières
1. [Rappel du sujet](#rappel-du-sujet)
2. [Modélisation](#modélisation)
3. [Configuration](#configuration)
4. [Détails sur le fonctionnement de l'outil](#détails-sur-le-fonctionnement-de-loutil)\
  a. [Grand public](#grand-public)\
  b. [Admin](#admin)\
  c. [Mannequin](#mannequin)\
  d. [Couturier](#couturier)\
  e. [Organisateur](#organisateur)

## Rappel du sujet

Des défilés de mode ont lieu régulièrement. Un vêtement est fait par un couturier et pour un mannequin. Chaque vêtement doit être porté avec les accessoires adéquats (chaussures, bijoux, accessoires vestimentaires (écharpes, ceintures, etc.)). Ces accessoires ont un prix et très souvent même un prix de location et pour certain il faut aussi rajouter un prix pour l'asurance en cas de vol (bijoux). Le vêtement a un coût et le mannequin aussi. Les mannequins peuvent intervenir dans différents défilés, il faut donc vérifier qu'il n'y ait pas de problèmes de date. Un défilé est organisé par une société dans un lieu.

En fonction de la catégorie d'utilisateur à laquelle on appartient, l'accès est différent. L'accès grand public concerne l'accès aux différents défilés qui vont avoir lieu ou ont eu lieu avec les dates, le lieu ainsi que le couturiers et les mannequins concernés. Cela permet donc de trouver un ou plusieurs défilés en fonction d'un lieu de présentation ou d'une date ou plusieurs dates (intervalle de dates) ou en fonction du couturier ou en fonction d'un mannequin lors de ce défilé ou par son coût (approximativement). Les défilés sont affichés triés sur la date, lorsqu'il y en a plus d'un. Les autres utilisateurs ont également accès à cette recherche de défilés.

Les mannequins ne peuvent consulter que ce qu'ils les concernent, c'est-à-dire les vêtements et les accessoires qu'ils doivent porter lors d'un défilé, ainsi que la position du vêtement dans le défilé.

Les couturiers ont accès à tous ce qui concerne un défilé les concernant (ajout, modification et suppression des mannequins, des vêtements, des accessoires et de l'ordre d'apparition des vêtements). On ne doit pas recréer un mannequin ou un vêtement ou un accessoire existant, il faut donc pouvoir le rechercher. Par vêtemenent, l'ensemble des informations lui étant associé doit apparaitre (mannequin et les accessoires) ainsi que l'ordre d'apparition pour un défilé. Le coût du vêtement (mannequin et accessoires) est consultable.

Les organisateurs peuvent créer et modifier tout ce qui concerne les défilés (dates, lieux, les couturiers invités) qu'ils vont organiser. Ils peuvent consulter le coût du défilé.

Il est impossible de modifier ou détruire un élément d'un défilé ayant déjà eu lieu. La modification ou la suppression ne peut concerner qu'un défilé ou un élément d'un défilé qui va avoir lieu.

## Modélisation

![Diagramme de classes du projet gestionDefile](https://i.imgur.com/t0e0iEN.png)

## Configuration

Le code source comprend une Java DB et le projet. La base de données se trouve dans le dossier "gestionDefile" et le projet dans "gestionDefile_project". Pour run le projet avec NetBeans, il faut déposer la base de donnée dans la Database Location - généralement située dans %appdata% > NetBeans > Derby. Le projet est a déposé dans vos documents, généralement Documents > NetBeansProjects.

À l'ouverture du projet dans NetBeans, des erreurs peuvent s'afficher. Il suffit de [faire un clic-droit sur chaque module](https://i.imgur.com/stO25YF.png) (ejb, war, etc.) afin de [résoudre le problème de serveur manquant](https://i.imgur.com/nW83cKe.png). Sélectionnez Glassfish Server et cliquez sur Ok. Enfin, si une erreur se produit au niveau du Persistence Unit, supprimez et recréez le Persistence Unit.

## Détails sur le fonctionnement de l'outil

Au lancement de l'outil, l'utilisateur arrive sur la page d'accueil. Si ce dernier possède un compte, il peut se connecter. Sinon, il peut accéder à l'accès public. Pour se connecter, l'utilisateur a besoin d'un identifiant (de la forme prenom.nom) et d'un mot de passe. À noter qu'à la création d'une personne, l'identifiant est automatiquement créé grâce au prénom et au nom. 

Ci-joint un tableau récapitulatif de tous les comptes créés à ce jour dans l'outil, pour que vous puissez l'essayer en vous mettant dans la peau de plusieurs personnes.

| Rôle  | Identifiant | Mot de passe |
| ------------- | ------------- | ------------ |
| Admin  | admin  | root |
| Mannequin  | cara.delevingne  | 123 |
|  | gigi.hadid  | 123 |
|  | naomi.campbell  | 123 |
|  | david.gandy | 123 |
| Couturier | karl.lagerfeld  | 123 |
|  | paul.poiret  | 123 |
|  | pierre.cardin  | 123 |
|  | coco.chanel  | 123 |
|  | yves.saint-laurent  | 123 |
|  | vivienne.westwood  | 123 |
| Organisateur | guillaume.desanges  | 123 |
|  | yves.carcelle  | 123 |
|  | carine.rolland  | 123 |

Une fois connecté (ou non connecté dans le cas du grand public), l'utilisateur est redirigé vers son dashboard ; ou menu. Ce dernier est organisé en catégories : défilés, lieux, couturieurs, mannequins, vêtements, accessoires. Selon le rôle de l'utilisateur, certaines catégories sont ou ne sont pas affichées à l'écran.

### Grand public

Pour rappel, le public n'a accès qu'à la fonctionnalité de recherche de défilés. La recherche peut se faire selon différents critères : par lieu, date, intervalle de dates, couturier, mannequin ou coût approximatif. Les lieux, couturiers et mannequins sont récupérés dans des listes. Pour la recherche par coût, l'utilisateur peut afficher tous les défilés ayant un coût approximatif à plus ou moins un certain delta (qu'il choisit également).

### Admin

L'admin a accès à la recherche de défilés détaillée ci-dessous. Il a également accès à la création, modification, suppression et visualisation des organisateurs.

### Mannequin

Le mannequin a accès à la recherche de défilés détaillée précédemment. Il a également accès à la visulation de tous ses vêtements et accessoires. Ces derniers sont affichés dans un tableau. Chaque vêtement est relié à un créateur, à un défilé. Le mannequin peut donc également obtenir des détails sur ces objets.

### Couturier

Le couturier a accès à la recherche de défilés détaillée précédemment. Il a également accès à la création, modification, suppression et visualisation de mannequins, de vêtements ainsi que d'accessoires. Il peut aussi modifier l'ordre de passage d'un vêtement dans un défilé.

NB: à la création d'un accessoire, le couturier est amené à renseigner un prix d'achat ou un prix de location. Ainsi, si le prix d'achat est renseigné, le prix de location doit être 0. De même, si un prix de location est renseigné, c'est le prix d'achat qui doit être 0. Dans tous les cas, il existe un prix d'assurance pour les bijoux.

### Organisateur

L'organisateur a accès à la recherche de défilés détaillée précédemment. Il a également accès à création, modification, suppression et visualisation de lieux, de défilés et de couturiers.
