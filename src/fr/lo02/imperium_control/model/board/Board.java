package fr.lo02.imperium_control.model.board;

import fr.lo02.imperium_control.model.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board implements Serializable {
    private static Board instance;
    private List<Sector> sectors;
    private final int BOARD_SIZE = 3;  // 3x3 Board size
    private Map<Hex, Player> hexOwnership; // Tracks which player owns which hex

    // Singleton: Returns the single instance of Board
    public static synchronized Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    // Private constructor to prevent direct instantiation
    private Board() {
        sectors = new ArrayList<>();
        initializeBoard();
    }

    // Initialize sectors with the central Tri-Prime sector
    private void initializeBoard() {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                Sector sector = new Sector(x, y);
                sectors.add(sector);

                // Place the Level 3 (Tri-Prime) hex in the center
                if (x == BOARD_SIZE / 2 && y == BOARD_SIZE / 2) {
                    sector.getHexes().get(0).setSystemType(SystemType.LEVEL3);
                }
            }
        }
    }

    // Method to deploy ships during initial setup
    // Board.java
    // Board.java
    public boolean deployShip(Player player, int sectorX, int sectorY, int hexId) {
        Sector sector = getSector(sectorX, sectorY);
        if (sector == null) {
            System.out.println("Sector not found.");
            return false;
        }

        Hex hex = sector.getHex(hexId);
        if (hex == null) {
            System.out.println("Invalid hex for deployment.");
            return false;
        }

        // Check capacity
        int shipsInHex = player.getShipsInHex(hex);
        if (shipsInHex >= hex.getCapacity()) {
            System.out.println("Hex capacity reached. Cannot deploy more ships.");
            return false;
        }

        // Deploy ship
        player.addShipToHex(hex);
        System.out.println("Ship deployed successfully at sector (" + sectorX + ", " + sectorY + "), hex ID: " + hexId);
        return true;
    }

    // Helper to get a sector by its coordinates
    public Sector getSector(int x, int y) {
        return sectors.stream()
                .filter(sector -> sector.getX() == x && sector.getY() == y)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sector not found at coordinates: (" + x + ", " + y + ")"));
    }

    // Get list of all sectors
    public List<Sector> getSectors() {
        return sectors;
    }

    // Reset the board (e.g., for restarting the game)
    public void resetBoard() {
        sectors.clear();
        initializeBoard();
    }

    // Debug: Display the board state
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Board:\n");
        for (Sector sector : sectors) {
            sb.append(sector).append("\n");
        }
        return sb.toString();
    }

    public Player getOwnerOfHex(Hex hex) {
        return hexOwnership.get(hex);
    }

    public int getShipsInHex(Hex hex) {
        Player owner = getOwnerOfHex(hex);
        if (owner == null) {
            return 0; // No ships if hex is unoccupied
        }
        return owner.getShipsInHex(hex);
    }

    public boolean isOccupiedByOpponent(Hex hex, Player player) {
        Player owner = getOwnerOfHex(hex);
        return owner != null && !owner.equals(player); // Hex is occupied by another player
    }

    public void updateOwnership(Hex hex, Player player) {
        hexOwnership.put(hex, player); // Update the ownership of the hex
    }
}
