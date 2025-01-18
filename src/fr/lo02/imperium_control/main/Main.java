package fr.lo02.imperium_control.main;

import fr.lo02.imperium_control.controller.GameController;
import fr.lo02.imperium_control.model.player.BotPlayer;
import fr.lo02.imperium_control.model.player.Game;
import fr.lo02.imperium_control.model.player.Player;
import fr.lo02.imperium_control.model.player.RealPlayer;
import fr.lo02.imperium_control.model.player.strategy.AggressiveStrategy;
import fr.lo02.imperium_control.model.player.strategy.ExpansionStrategy;
import fr.lo02.imperium_control.model.player.strategy.RandomStrategy;
import fr.lo02.imperium_control.controller.SaveManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        SaveManager saveManager = new SaveManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("======= Pocket Imperium =======");
            System.out.println("1. Start New Game");
            System.out.println("2. Load Game");
            System.out.println("3. View Rules");
            System.out.println("4. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    startNewGame(gameController, saveManager, scanner);
                    break;
                case "2":
                    loadGame(saveManager);
                    break;
                case "3":
                    displayRules();
                    break;
                case "4":
                    System.out.println("Exiting the game. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void startNewGame(GameController gameController, SaveManager saveManager, Scanner scanner) {
        System.out.println("Starting a new game...");
        System.out.println("Welcome to Pocket Imperium!");

        // Get the number of real players
        int numRealPlayers = getNumberOfRealPlayers(scanner);

        // Create players
        List<Player> players = createPlayers(numRealPlayers);

        // Initialize the game
        Game game = new Game(gameController, players);

        // Game loop with save option
        while (true) {
            System.out.println("=== Game Menu ===");
            System.out.println("1. Continue");
            System.out.println("2. Save and Exit");
            System.out.println("=================");
            System.out.print("Choose an option: ");

            String gameChoice = scanner.nextLine();

            if (gameChoice.equals("1")) {
                game.startGameLoop();
            } else if (gameChoice.equals("2")) {
                saveManager.saveGame(game);
                System.out.println("Game saved. Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loadGame(SaveManager saveManager) {
        Game loadedGame = saveManager.loadGame();
        if (loadedGame != null) {
            System.out.println("Game loaded successfully!");
            loadedGame.startGameLoop();
        } else {
            System.out.println("No save file found. Please start a new game.");
        }
    }

    private static int getNumberOfRealPlayers(Scanner scanner) {
        while (true) {
            System.out.print("Enter the number of real players (1-3): ");
            String input = scanner.nextLine();
            try {
                int numRealPlayers = Integer.parseInt(input);
                if (numRealPlayers >= 1 && numRealPlayers <= 3) {
                    return numRealPlayers;
                } else {
                    System.out.println("Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static List<Player> createPlayers(int numRealPlayers) {
        List<Player> players = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Add real players
        for (int i = 1; i <= numRealPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String playerName = scanner.nextLine();
            players.add(new RealPlayer(playerName, 15));
        }

        // Add bot players to fill up to 3 total players
        int numBots = 3 - numRealPlayers;

        Random random = new Random();
        for (int i = 1; i <= numBots; i++) {
            BotPlayer botPlayer = new BotPlayer("AI" + i, 15);

            // Assign a random strategy to the bot
            int strategyIndex = random.nextInt(3); // Randomly pick 0, 1, or 2
            switch (strategyIndex) {
                case 0 -> botPlayer.setStrategy(new AggressiveStrategy());
                case 1 -> botPlayer.setStrategy(new ExpansionStrategy());
                case 2 -> botPlayer.setStrategy(new RandomStrategy());
            }

            System.out.println("Assigned strategy for " + botPlayer.getName() + ": " +
                    botPlayer.getStrategy().getClass().getSimpleName());
            players.add(botPlayer);
        }

        return players;
    }

    private static void displayRules() {
        System.out.println("======= Rules of Pocket Imperium =======");
        System.out.println("1. Deploy your ships strategically.");
        System.out.println("2. Choose commands to Expand, Explore, or Exterminate.");
        System.out.println("3. Gain control of sectors and score points.");
        System.out.println("4. Sustain your fleets and outscore your opponents.");
        System.out.println("5. The game ends after 9 rounds or when a player is eliminated.");
        System.out.println("========================================");
    }
}
