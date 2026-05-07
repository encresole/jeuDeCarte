========================================
  PROJET JAVA - JEU DE CARTES POKEMON
  Architecture MVC - JavaFX
========================================

========================================
  RAPHAEL — Structures de données + Architecture MVC
  Critères notation : SD + Architecture
========================================

Package : model/
-----------------
Carte.java          → Classe mère abstraite — attributs : nom, attaque, defense, energie, type, image
CarteAttaque.java   → extends Carte — carte d'attaque standard
CarteSoin.java      → extends Carte — carte de soin (restaure des PV)
CarteBuff.java      → extends Carte — carte buff/debuff sur les stats
CarteCombo.java     → extends Carte — carte avec effets combo (2+ cartes)
Deck.java           → ArrayList<Carte> — pioche du joueur, mélange, tirage
MainJeu.java        → ArrayList<Carte> — main courante du joueur
Joueur.java         → nom, pv, pvMax, deck, main, energie, estVivant()
Partie.java         → état du jeu, joueur1, joueur2, tourActuel, estTerminee()
Combat.java         → calcul des dégâts, application des effets, résolution d'un tour
Effet.java          → interface — méthode appliquer(Joueur cible)
EffetSoin.java      → implements Effet — restaure des PV au joueur cible
EffetBuff.java      → implements Effet — augmente attaque ou défense temporairement
EffetDebuff.java    → implements Effet — réduit attaque ou défense de l'adversaire

Structures Java utilisées :
- ArrayList    → pour le Deck et la Main
- HashMap      → pour stocker les effets actifs
- LinkedList   → pour l'historique des actions


========================================
  CHRISTOPHE — Données + Gestion projet + Contrôleur
  Critères notation : Données + Gestion projet
========================================

Package : controller/
-----------------
GameController.java           → Contrôleur principal — relie le Modèle (Raphael) et la Vue (Jonathan)
CarteController.java          → Actions sur les cartes : jouer, défausser, piocher
DeckController.java           → Construction du deck, mélange, gestion de la pioche
BibliothequeController.java   → Recherche multicritères dans la bibliothèque de cartes

Package : data/
-----------------
CarteDAO.java         → Lecture et écriture des cartes depuis le fichier JSON
ChargeurCartes.java   → Charge toutes les cartes au démarrage de l'application
Serialiseur.java      → Sauvegarde et chargement d'une partie en cours

Fichiers de données (resources/) :
-----------------
cartes.json    → Toutes les cartes avec leurs stats (minimum 20 cartes variées)
parties.json   → Sauvegarde des parties en cours

Gestion du projet :
-----------------
- Création et gestion du dépôt GitHub (branches, commits, README)
- Rédaction du cahier des charges
- Préparation du diaporama d'auto-évaluation pour la soutenance (mi-mai)
- Suivi de la progression de l'équipe (GitHub Projects ou Trello)


========================================
  JONATHAN — Interface graphique JavaFX
  Critères notation : Interface graphique
========================================

Package : view/
-----------------
MainApp.java              → Point d'entrée — lance le Stage JavaFX
MenuPrincipal.java        → Écran d'accueil, boutons Jouer / Quitter
PlateauView.java          → Scène principale du jeu
CarteView.java            → Affichage visuel d'une carte (image + stats)
DeckView.java             → Affichage de la pioche
MainJoueurView.java       → Affichage de la main du joueur
InfoJoueurView.java       → PV, énergie, nom du joueur
AnimationCombat.java      → FadeTransition, ScaleTransition pour les attaques
BibliothequeView.java     → Liste de toutes les cartes avec filtre de recherche
ResultatView.java         → Écran de fin de partie (victoire / défaite)

Fichiers FXML (optionnel) :
-----------------
plateau.fxml
menu.fxml
bibliotheque.fxml


========================================
  STRUCTURE COMPLETE DU PROJET
========================================

src/
├── model/
│   ├── Carte.java
│   ├── CarteAttaque.java
│   ├── CarteSoin.java
│   ├── CarteBuff.java
│   ├── CarteCombo.java
│   ├── Deck.java
│   ├── MainJeu.java
│   ├── Joueur.java
│   ├── Partie.java
│   ├── Combat.java
│   ├── Effet.java
│   ├── EffetSoin.java
│   ├── EffetBuff.java
│   └── EffetDebuff.java
├── controller/
│   ├── GameController.java
│   ├── CarteController.java
│   ├── DeckController.java
│   └── BibliothequeController.java
├── data/
│   ├── CarteDAO.java
│   ├── ChargeurCartes.java
│   └── Serialiseur.java
├── view/
│   ├── MainApp.java
│   ├── MenuPrincipal.java
│   ├── PlateauView.java
│   ├── CarteView.java
│   ├── DeckView.java
│   ├── MainJoueurView.java
│   ├── InfoJoueurView.java
│   ├── AnimationCombat.java
│   ├── BibliothequeView.java
│   └── ResultatView.java
└── resources/
    ├── cartes.json
    ├── parties.json
    └── images/


========================================
  REPARTITION DES EFFORTS (par critère)
========================================

Interface graphique :
  Jonathan    70%  (leader)
  Raphael     20%
  Christophe  10%

Structures de données :
  Raphael     70%  (leader)
  Jonathan    20%
  Christophe  10%

Architecture MVC :
  Raphael     50%  (leader)
  Christophe  30%
  Jonathan    20%

Données / jeu de test :
  Christophe  60%  (leader)
  Raphael     25%
  Jonathan    15%

Gestion du projet :
  Christophe  60%  (leader)
  Jonathan    25%
  Raphael     15%


========================================
  RAPPEL IMPORTANT
========================================
- Technologie : Swing et AWT
- Commits réguliers OBLIGATOIRES les mercredis en séance
- Chacun commit depuis son propre compte GitHub
- Soutenance mi-mai : 12-15 min demo + diaporama auto-évaluation
========================================
