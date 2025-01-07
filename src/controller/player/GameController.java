package controller.player;

import model.player.Game;
import model.player.RealPlayer;
import model.player.BotPlayer;
import model.player.Player;
import model.player.strategy.RandomStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameController {

    private Game game;
    private Scanner scanner;
    private SaveManager saveManager;

    public GameController() {
        game = Game.getInstance();
        scanner = new Scanner(System.in);
        saveManager = new SaveManager();
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nBienvenue dans Pocket Imperium !");
            System.out.println("1. Démarrer une nouvelle partie");
            if (saveManager.saveExists()) {
                System.out.println("2. Charger une partie");
                System.out.println("3. Supprimer la sauvegarde");
            } else {
                System.out.println("2. Charger une partie (Non disponible)");
                System.out.println("3. Supprimer la sauvegarde (Non disponible)");
            }
            System.out.println("4. Voir les règles du jeu");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            int choice = getValidatedIntInput();

            switch (choice) {
                case 1:
                    startNewGame();
                    break;
                case 2:
                    if (saveManager.saveExists()) {
                        loadGame();
                    } else {
                        System.out.println("Aucune partie à charger.");
                    }
                    break;
                case 3:
                    if (saveManager.saveExists()) {
                        saveManager.deleteSave();
                    } else {
                        System.out.println("Aucune sauvegarde à supprimer.");
                    }
                    break;
                case 4:
                    showGameRules();
                    break;
                case 5:
                    System.out.println("Quitter le jeu. Au revoir !");
                    exit = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void startNewGame() {
        List<Player> players = initializePlayers();
        game.initializePlayers(players);
        saveManager.saveGame(game);  // Save game immediately after initialization
        game.startGame();
        saveManager.saveGame(game);  // Save game after each round
    }

    private void loadGame() {
        Game loadedGame = saveManager.loadGame();
        if (loadedGame != null) {
            this.game = loadedGame;
            game.startGame();
        }
    }

    private List<Player> initializePlayers() {
        List<Player> players = new ArrayList<>();

        int realPlayers = 0;
        while (realPlayers < 1 || realPlayers > 3) {
            System.out.print("Combien de joueurs réels ? (1-3) : ");
            realPlayers = getValidatedIntInput();
        }

        int botPlayers = 3 - realPlayers;

        for (int i = 1; i <= realPlayers; i++) {
            System.out.print("Nom du joueur " + i + " : ");
            String playerName = scanner.nextLine();
            players.add(new RealPlayer(playerName));
        }

        for (int i = 1; i <= botPlayers; i++) {
            players.add(new BotPlayer("Bot" + i, new RandomStrategy()));
        }

        Collections.shuffle(players);
        System.out.println("Ordre de passage (tiré au sort) :");
        for (Player player : players) {
            System.out.println(player.getName());
        }

        return players;
    }

    private void showGameRules() {
        System.out.println("\nRègles du jeu :");
        System.out.println("1. Expansion - Ajoutez des vaisseaux à vos secteurs.");
        System.out.println("2. Exploration - Déplacez votre flotte vers de nouveaux secteurs.");
        System.out.println("3. Extermination - Combattez les ennemis et envahissez leurs systèmes.");
        System.out.println("\nObjectif : Contrôlez le plus grand nombre de secteurs après 9 tours ou éliminez toutes les flottes ennemies.");
    }

    private int getValidatedIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine();  // Clear invalid input
            }
        }
    }

    public SaveManager getSaveManager() {
        return saveManager;
    }
}
