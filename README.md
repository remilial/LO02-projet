# Pocket Imperium

## Description
Pocket Imperium est une adaptation logicielle en Java du jeu de stratégie éponyme. Ce projet respecte les principes de la programmation orientée objet et intègre des patrons de conception (MVC, Strategy, Singleton, etc.). Le jeu propose une expérience pour trois joueurs, humains ou virtuels, avec des stratégies variées. L'application permet de sauvegarder et de reprendre des parties.


---

## Structure du Projet

PocketImperium/  
│  
├── src/  
│   ├── controller/  
│   │   ├── GameController.java       # Gère la boucle principale du jeu  
│   │   ├── SaveManager.java          # Gestion des sauvegardes/chargements  
│   │   └── CommandHandler.java       # Traite les commandes des joueurs/bots  
│   │  
│   ├── model/  
│   │   ├── board/  
│   │   │   ├── Board.java            # Grille de jeu à 3x3 secteurs  
│   │   │   ├── Sector.java           # Contient les hexes, contrôle les navires  
│   │   │   ├── Hex.java              # Représentation individuelle des tuiles hexagonales  
│   │   │   ├── Ship.java             # Classe pour représenter les vaisseaux  
│   │   │   └── SystemType.java       # Enumération des niveaux des systèmes (I, II, III)  
│   │   │  
│   │   ├── player/  
│   │   │   ├── Player.java           # Classe abstraite pour les données des joueurs  
│   │   │   ├── RealPlayer.java       # Gère les joueurs humains  
│   │   │   ├── BotPlayer.java        # Gère les bots  
│   │   │   └── Strategy.java         # Interface pour les stratégies du bot  
│   │   │  
│   │   ├── command/  
│   │   │   ├── Command.java          # Représentation des commandes des joueurs  
│   │   │   └── CommandType.java      # Enum des types de commandes disponibles  
│   │   │  
│   │   └── Game.java                 # Gestion globale du jeu  
│  
│   ├── view/  
│   │   ├── cli/  
│   │   │   └── ConsoleView.java      # Interface CLI (console)  
│   │   └── gui/  
│   │       ├── GuiView.java          # Interface graphique Swing/JavaFX  
│   │       └── GameEventHandlers.java # Gestion des événements GUI  
│  
│   └── Main.java                     # Point d'entrée de l'application  
│  
└── test/  
├── GameTest.java                 # Tests unitaires du jeu  
├── PlayerTest.java               # Tests des actions des joueurs  
└── SectorTest.java               # Tests des interactions sectorielles

---

## Composants Principaux
- **Controller Layer (controller/)** : Gère la logique du jeu et la coordination entre les modèles et les vues.
- **Model Layer (model/)** : Contient la logique métier et la représentation des éléments du jeu.
- **View Layer (view/)** : Interfaces CLI et GUI pour l'interaction avec l'utilisateur.
- **Main Class (Main.java)** : Point de démarrage de l'application.
- **Test Layer (test/)** : Tests unitaires pour assurer la stabilité du jeu.

---

## Commandes et Classes Clés
- **GameController** : Supervise la boucle de jeu et les phases principales.
- **SaveManager** : Gère la persistance (sauvegarde/chargement).
- **CommandHandler** : Traite les commandes des joueurs.
- **Board** : Grille du jeu (3x3).
- **Sector** : Gère les hexes et les vaisseaux.
- **Player (abstract)** : Classe de base pour les joueurs (humains et bots).
- **Strategy** : Stratégies des bots (Exploit, Explore, Exterminate).
- **ConsoleView / GuiView** : Interface CLI ou GUI.

---

## Répartition des Tâches
### Rayan
1. [ ] Board.java – Grille de jeu 3x3.
2. [ ] Sector.java – Gestion des secteurs et vaisseaux.
3. [ ] Hex.java – Tuiles hexagonales.
4. [ ] SystemType.java – Enumération des niveaux.
5. [ ] Ship.java – Gestion des vaisseaux.
6. [ ] Strategy.java – Stratégie IA pour BotPlayer.
7. [ ] SaveManager.java – Sauvegarde/chargement de la partie.
8. [ ] CommandHandler.java – Gestion de l'exécution des commandes (en lien avec Command).
9. [ ] GameEventHandlers.java – Événements GUI.
10. [ ] ConsoleView / GuiView – Interfaces utilisateurs.
11. [ ] Tests – Secteurs, vaisseaux, sauvegarde.

### Ali
1. [ ] Command.java – Représentation des commandes.
2. [x] CommandType.java – Enum pour les types de commandes.
3. [x] Player.java – Classe abstraite des joueurs.
4. [x] RealPlayer.java – Joueur humain.
5. [x] BotPlayer.java – Joueur IA.
6. [ ] Game.java – Gestion du déroulement du jeu.
7. [ ] Main.java – Lancement du jeu.
8. [ ] GameController.java – Gestion des tours de jeu.
9. [ ] ConsoleView / GuiView – Interfaces utilisateurs (CLI/GUI).
10. [ ] Tests – Joueurs, commandes, logiq ue de jeu.