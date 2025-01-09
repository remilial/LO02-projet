package model.board;

import model.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {
    private static Board instance;
    private List<Sector> sectors;
    private final int BOARD_SIZE = 3;  // 3x3 Board size

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
    public boolean deployShip(Player player, int sectorX, int sectorY, int hexId) {
        try {
            System.out.println("Attempting to deploy ship for player " + player.getName() +
                    " at sector (" + sectorX + ", " + sectorY + "), hex ID: " + hexId);

            Sector sector = getSector(sectorX, sectorY);
            Hex hex = sector.getHex(hexId);

            System.out.println("Hex details: " + hex);

            // Count ships already in this hex for this player
            long shipsInHex = player.getOccupiedHexes().stream().filter(h -> h.equals(hex)).count();
            if (shipsInHex >= hex.getCapacity()) {
                System.out.println("Hex capacity exceeded for deployment at sector (" + sectorX + ", " + sectorY + "), hex ID: " + hexId);
                return false;
            }

            // Validate hex for deployment
            if (hex.getSystemType() == SystemType.NONE) {
                throw new IllegalArgumentException(
                        "Invalid hex for deployment at sector (" + sectorX + ", " + sectorY + "), hex ID: " + hexId
                );
            }

            player.addShipToHex(hex); // Add ship to the player's state
            System.out.println("Deployment successful for player " + player.getName() + " on hex " + hexId);
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Helper to get a sector by its coordinates
    public Sector getSector(int x, int y) {
        System.out.println("Searching for sector at coordinates: (" + x + ", " + y + ")");
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
}
