package fr.lo02.imperium_control.model.player;

import fr.lo02.imperium_control.controller.GameController;
import fr.lo02.imperium_control.model.command.Command;

import java.util.List;

public class Game {
    private final GameController gameController;
    private final List<Player> players;

    public Game(GameController gameController, List<Player> players) {
        this.gameController = gameController;
        this.players = players;
    }

    public void startGameLoop() {
        System.out.println("Game starts now!");

        // Display the initial board
        System.out.println("\nInitial Board:");
        gameController.displayBoard();

        for (int round = 1; round <= 9; round++) {
            System.out.println("\n=== Round " + round + " ===");

            // Plan phase
            planPhase();
            gameController.displayBoard(); // Display board after the plan phase

            // Perform phase
            performPhase();
            gameController.displayBoard(); // Display board after the perform phase

            // Exploit phase
            exploitPhase();
            gameController.displayBoard(); // Display board after the exploit phase
        }

        System.out.println("\nGame Over!");
        Player winner = determineWinner();
        System.out.println("The winner is " + winner.getName() + "!");
    }

    private void planPhase() {
        System.out.println("\nPlan Phase:");
        for (Player player : players) {
            player.setCommands(player.chooseCommands());
        }
    }

    private void performPhase() {
        System.out.println("\nPerform Phase:");
        for (Command command : players.get(0).getCommands()) { // Commands are executed in order
            for (Player player : players) {
                gameController.executeCommand(command, player, players);
            }
        }
    }

    private void exploitPhase() {
        System.out.println("\nExploit Phase:");
        // Handle sustain and scoring logic
        gameController.getBoard().resetBoard(); // Example reset; replace with actual sustain/scoring
    }

    private Player determineWinner() {
        return players.stream()
                .max((p1, p2) -> Integer.compare(p1.getScore(), p2.getScore()))
                .orElseThrow(() -> new IllegalStateException("No players found!"));
    }
}