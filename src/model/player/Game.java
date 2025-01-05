package model.player;

import model.board.Board;
import model.board.Ship;
import model.board.Sector;
import model.board.SystemType;
import model.command.Command;
import model.command.CommandType;
import model.player.strategy.RandomStrategy;

import java.util.*;

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
    }

    // Singleton instance retrieval
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    // Game loop
    public void startGame() {
        System.out.println("Starting Pocket Imperium Game...");
        int maxRounds = 9;
        int rounds = 0;

        while (!isGameOver && rounds < maxRounds) {
            playRound();
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

    // Execute a full round where each player chooses the full order of commands
    public void playRound() {
        Map<Player, List<CommandType>> chosenCommands = new HashMap<>();
        for (Player player : players) {
            List<CommandType> commands = chooseCommandOrder(player);
            chosenCommands.put(player, commands);
        }

        // Execute each command phase in order (Expand -> Explore -> Exterminate)
        for (int i = 0; i < 3; i++) {
            Map<CommandType, List<Player>> groupedCommands = new HashMap<>();

            // Group players by their chosen command
            for (Map.Entry<Player, List<CommandType>> entry : chosenCommands.entrySet()) {
                CommandType commandType = entry.getValue().get(i);
                groupedCommands.computeIfAbsent(commandType, k -> new ArrayList<>()).add(entry.getKey());
            }

            // Execute commands, applying numerical efficiency (1, 2, 3 based on order)
            for (Map.Entry<CommandType, List<Player>> group : groupedCommands.entrySet()) {
                int efficiency = Math.max(1, 4 - group.getValue().size()); // 3 for 1 player, 2 for 2 players, 1 for 3 players
                for (Player player : group.getValue()) {
                    Command command = new Command(group.getKey(), player);
                    command.executeWithEfficiency(efficiency);
                }
            }
        }
    }

    // Allow players to choose the full command order
    private List<CommandType> chooseCommandOrder(Player player) {
        if (player instanceof RealPlayer) {
            if (Boolean.getBoolean("test.mode")) {
                return List.of(CommandType.EXPAND, CommandType.EXPLORE, CommandType.EXTERMINATE);
            }

            try (Scanner scanner = new Scanner(System.in)) {
                List<CommandType> commandOrder = new ArrayList<>();

                System.out.println(player.getName() + ", choisissez l'ordre des commandes (1-3, sans répétition) :");
                System.out.println("[1] Expand, [2] Explore, [3] Exterminate");

                while (commandOrder.size() < 3) {
                    try {
                        int choice = scanner.nextInt();
                        if (choice >= 1 && choice <= 3) {
                            CommandType command = CommandType.values()[choice - 1];
                            if (!commandOrder.contains(command)) {
                                commandOrder.add(command);
                            } else {
                                System.out.println("Commande déjà choisie. Choisissez-en une autre.");
                            }
                        } else {
                            System.out.println("Choix invalide. Veuillez entrer 1, 2 ou 3.");
                        }
                    } catch (Exception e) {
                        System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                        scanner.nextLine();  // Clear invalid input
                    }
                }
                return commandOrder;
            }
        } else {
            List<CommandType> commands = new ArrayList<>(List.of(CommandType.EXPAND, CommandType.EXPLORE, CommandType.EXTERMINATE));
            Collections.shuffle(commands);
            return commands;
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
                System.out.println(player.getName() + " a perdu sa flotte. La partie est terminée !");
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
            for (Ship ship : sector.getShips()) {
                if (ship.getOwner() == player) {
                    score += sector.getSystemType().ordinal() + 1;
                }
            }
        }
        return score;
    }

    // Initialize players and set up the game
    public void initializePlayers(List<Player> playerList) {
        this.players = playerList;
        board.resetBoard();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            Sector startingSector = board.getSector(i, 0);

            startingSector.setSystemType(SystemType.LEVEL1);
            for (int j = 0; j < 15; j++) {
                if (startingSector.hasCapacityForShip()) {
                    Ship ship = new Ship("S" + (j + 1), "Blue", player);
                    startingSector.addShip(ship);
                    player.addShip(ship);
                } else {
                    System.out.println("Sector full. Reallocating ship for " + player.getName());
                    Sector fallback = findAvailableSector();
                    fallback.addShip(new Ship("S" + (j + 1), "Blue", player));
                }
            }
            player.setCurrentSector(startingSector);
        }
        System.out.println("Les joueurs ont été initialisés avec succès !");
    }

    // Find an available sector for overflow
    private Sector findAvailableSector() {
        for (Sector sector : board.getSectors()) {
            if (sector.hasCapacityForShip()) {
                return sector;
            }
        }
        throw new IllegalStateException("No available sectors for overflow.");
    }
}