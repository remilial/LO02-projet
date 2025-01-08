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

SystemType.java – Enumération des niveaux.
Ship.java – Gestion des vaisseaux.


### Ali
Hex.java – Tuiles hexagonales.
Test – Hex
Sector.java – Gestion des secteurs et vaisseaux.
Test – Sector
Board.java – Grille de jeu 3x3.
Test – Board

Sector.java – Gestion des secteurs et vaisseaux.
Command.java – Représentation des commandes.
CommandType.java – Enum pour les types de commandes.
Player.java – Classe abstraite des joueurs.
RealPlayer.java – Joueur humain.
BotPlayer.java – Joueur IA.
Strategy.java – Stratégie IA pour BotPlayer
Game.java – Gestion du déroulement du jeu.



Test – Command
Test – Ship
Test – Strategy
Test – BotPlayer
Test – RealPlayer
Test – Player
CommandHandler.java – Gestion de l'exécution des commandes (en lien avec Command).
Test –  CommandHandler
ConsoleView – Interfaces utilisateurs.
GameController.java – Gestion des tours de jeu.
SaveManager.java – Sauvegarde/chargement de la partie.
Test –  CommandHandler





### A faire
//Sauvegarde ne marche pas pour le moment
Test – Game
Main.java – Lancement du jeu.
// Optionel
GameEventHandlers.java – Événements GUI.
GuiView – Interfaces utilisateurs.
