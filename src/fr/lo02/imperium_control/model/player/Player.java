package fr.lo02.imperium_control.model.player;

import fr.lo02.imperium_control.model.board.Board;
import fr.lo02.imperium_control.model.board.Hex;
import fr.lo02.imperium_control.model.command.Command;
import fr.lo02.imperium_control.model.command.CommandType;

import java.util.*;

public abstract class Player {
    private String name;
    private Map<Hex, Integer> shipsInHex; // Tracks ships in each hex
    private int remainingShips;
    private int score;

    // Constructor
    public Player(String name, int initialShipCount) {
        this.name = name;
        this.remainingShips = initialShipCount;
        this.shipsInHex = new HashMap<>();
        this.score = 0;
    }

    private List<Command> commands = new ArrayList<>();

    // Set the list of commands for the player
    public void setCommands(List<Command> commands) {
        if (commands == null || commands.size() != 3) {
            throw new IllegalArgumentException("A player must choose exactly 3 commands.");
        }
        System.out.println("Setting commands for player " + name + ": " + commands);
        this.commands = new ArrayList<>(commands);
    }

    // Get the list of commands chosen by the player
    public List<Command> getCommands() {
        return new ArrayList<>(commands); // Return a copy to maintain immutability
    }

    // Adds a ship to the specified hex
    public void addShipToHex(Hex hex) {
        if (remainingShips <= 0) {
            throw new IllegalStateException("No remaining ships to place.");
        }

        // Track ships in hex
        shipsInHex.put(hex, shipsInHex.getOrDefault(hex, 0) + 1);
        remainingShips--;

        // Mark hex as occupied if it was empty
        hex.occupy();

        // Update ownership in the board
        Board.getInstance().updateOwnership(hex, this);
    }


    // Removes a ship from the specified hex
    public void removeShipFromHex(Hex hex) {
        if (!shipsInHex.containsKey(hex) || shipsInHex.get(hex) == 0) {
            throw new IllegalStateException("No ships to remove from this hex.");
        }

        // Update ship count and hex occupation
        int updatedCount = shipsInHex.get(hex) - 1;
        shipsInHex.put(hex, updatedCount);
        if (updatedCount == 0) {
            hex.vacate();
        }

        remainingShips++;
    }

    // Returns the number of ships in the specified hex
    public int getShipsInHex(Hex hex) {
        return shipsInHex.getOrDefault(hex, 0);
    }

    // Get the remaining number of ships the player can deploy
    public int getRemainingShips() {
        return remainingShips;
    }

    // Returns a map of all hexes occupied by the player and the count of ships in each hex
    public Map<Hex, Integer> getOccupiedHexes() {
        return shipsInHex;
    }

    // Get the player's current score
    public int getScore() {
        return score;
    }

    // Adds points to the player's score
    public void addScore(int points) {
        this.score += points;
    }

    // Abstract method for choosing commands, to be implemented by subclasses
    public abstract List<Command> chooseCommands();

    // Get the player's name
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', remainingShips=" + remainingShips + ", score=" + score + '}';
    }

    public Optional<Command> getCommandByType(CommandType type) {
        // Searches for a command in the player's command list matching the given type
        return commands.stream()
                .filter(command -> command.getType() == type)
                .findFirst();
    }
}