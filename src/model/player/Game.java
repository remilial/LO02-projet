package model.player;

import model.board.Board;
import model.board.Ship;
import model.board.Sector;
import model.board.SystemType;
import model.command.Command;
import model.command.CommandType;
import model.player.strategy.RandomStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static Game instance;  // Singleton instance
    private List<Player> players;
    private int currentTurn;
    private boolean isGameOver;
    private Board board;

    // Private constructor to enforce singleton
    private Game() {
        players = new ArrayList<>();
        board = Board.getInstance();
        currentTurn = 0;
        isGameOver = false;
        initializeGame();
    }

    // Singleton instance retrieval
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    // Initialize the game with 3 players (1 real, 2 bots)
    private void initializeGame() {
        players.add(new RealPlayer("Player1"));
        players.add(new BotPlayer("Bot1", new RandomStrategy()));
        players.add(new BotPlayer("Bot2", new RandomStrategy()));

        // Assign sectors and ships to players
        for (Player player : players) {
            Sector startingSector = board.getSector(players.indexOf(player), 0);
            startingSector.setSystemType(SystemType.LEVEL1);
            player.setCurrentSector(startingSector);

            Ship ship = new Ship("S" + (player.getShips() + 1), "Blue", player);
            startingSector.addShip(ship);
            player.addShip(ship);
        }
    }

    // Game loop
    public void startGame() {
        System.out.println("Starting Pocket Imperium Game...");
        int maxRounds = 9;
        int rounds = 0;

        while (!isGameOver && rounds < maxRounds) {
            for (Player player : players) {
                playTurn(player);
            }
            rounds++;
            checkFleetElimination();
        }

        if (isGameOver) {
            System.out.println("Game Over! A player's fleet has been eliminated.");
        } else {
            System.out.println("9 rounds reached. Calculating score...");
            determineWinnerByScore();
        }
    }

    // Manage player turns (execute all three commands)
    public void playTurn(Player player) {
        System.out.println(player.getName() + "'s turn");

        List<CommandType> commandOrder = chooseCommandOrder(player);
        for (CommandType command : commandOrder) {
            new Command(command, player).execute();
        }
        currentTurn = (currentTurn + 1) % players.size();
    }

    // Allow players to choose command order (RealPlayer) or randomize for bots
    private List<CommandType> chooseCommandOrder(Player player) {
        if (player instanceof RealPlayer) {
            if (Boolean.getBoolean("test.mode")) {
                // Simulate a default order during tests
                return List.of(CommandType.EXPAND, CommandType.EXPLORE, CommandType.EXTERMINATE);
            }

            Scanner scanner = new Scanner(System.in);
            List<CommandType> commandOrder = new ArrayList<>();

            System.out.println("Choose the order of commands (1-3, no repeats):");
            System.out.println("[1] Expand, [2] Explore, [3] Exterminate");

            while (commandOrder.size() < 3) {
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) {
                    CommandType command = CommandType.values()[choice - 1];
                    if (!commandOrder.contains(command)) {
                        commandOrder.add(command);
                    } else {
                        System.out.println("Command already chosen, pick another.");
                    }
                } else {
                    System.out.println("Invalid choice. Pick 1, 2, or 3.");
                }
            }
            return commandOrder;
        } else {
            List<CommandType> commandOrder = new ArrayList<>(List.of(CommandType.EXPAND, CommandType.EXPLORE, CommandType.EXTERMINATE));
            Collections.shuffle(commandOrder);  // Randomize for bots
            return commandOrder;
        }
    }

    // Check if any player's fleet has been eliminated
    private void checkFleetElimination() {
        for (Player player : players) {
            int totalShips = 0;
            for (Sector sector : board.getSectors()) {
                for (Ship ship : sector.getShips()) {
                    if (ship.getOwner() == player) {
                        totalShips++;
                    }
                }
            }
            if (totalShips == 0) {
                System.out.println(player.getName() + "'s fleet has been eliminated!");
                isGameOver = true;
                return;
            }
        }
    }

    // Determine the winner by calculating the score
    public void determineWinnerByScore() {
        Player winner = null;
        int highestScore = 0;

        for (Player player : players) {
            int score = calculateScore(player);
            System.out.println(player.getName() + "'s score: " + score);

            if (score > highestScore) {
                highestScore = score;
                winner = player;
            }
        }

        if (winner != null) {
            System.out.println(winner.getName() + " wins with " + highestScore + " points!");
        } else {
            System.out.println("The game ends in a draw!");
        }
    }

    // Calculate score for a player
    private int calculateScore(Player player) {
        int score = 0;
        for (Sector sector : board.getSectors()) {
            if (!sector.getShips().isEmpty() && sector.getShips().get(0).getOwner() == player) {
                score += sector.getSystemType().ordinal() + 1;
            }
        }
        return score;
    }

    // Getter to check if the game is over (for testing)
    public boolean isGameOver() {
        return isGameOver;
    }
}
