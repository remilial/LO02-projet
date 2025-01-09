package fr.lo02.imperium_control.model.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sector implements Serializable {
    private final int x, y; // Sector coordinates
    private List<Hex> hexes; // Hexes within the sector
    private static final Random random = new Random();
    private boolean isTriPrime; // Indicates if the sector is Tri-Prime (Level 3)

    // Constructor
    public Sector(int x, int y) {
        this.x = x;
        this.y = y;
        this.hexes = new ArrayList<>();
        this.isTriPrime = (x == 1 && y == 1); // Center sector (1,1) is Tri-Prime
        initializeHexes();
    }

    // Initialize hexes in the sector
    private void initializeHexes() {
        hexes.clear(); // Reset the list of hexes

        // If the sector is Tri-Prime, add a single Level 3 hex
        if (isTriPrime) {
            hexes.add(new Hex(0, SystemType.LEVEL3, x, y)); // Pass sector coordinates
        } else {
            // Create 6 hexes for the sector
            for (int i = 0; i < 6; i++) {
                hexes.add(new Hex(i, SystemType.NONE, x, y)); // Pass sector coordinates
            }

            // Randomly assign one hex as Level 2
            int level2Index = random.nextInt(6);
            hexes.get(level2Index).setSystemType(SystemType.LEVEL2);

            // Randomly assign two hexes as Level 1
            int level1Index1, level1Index2;
            do {
                level1Index1 = random.nextInt(6);
            } while (level1Index1 == level2Index); // Ensure uniqueness for Level 2

            do {
                level1Index2 = random.nextInt(6);
            } while (level1Index2 == level2Index || level1Index2 == level1Index1); // Ensure uniqueness

            hexes.get(level1Index1).setSystemType(SystemType.LEVEL1);
            hexes.get(level1Index2).setSystemType(SystemType.LEVEL1);
        }
    }

    // Retrieve the list of hexes
    public List<Hex> getHexes() {
        return hexes;
    }

    // Get a specific hex by its ID
    public Hex getHex(int id) {
        for (Hex hex : hexes) {
            if (hex.getId() == id) {
                return hex;
            }
        }
        throw new IllegalArgumentException("Hex not found with ID: " + id);
    }

    // Get adjacent hexes of a given hex within this sector
    public List<Hex> getAdjacentHexes(Hex hex) {
        List<Hex> neighbors = new ArrayList<>();
        int hexId = hex.getId();

        // Simplified adjacency logic for hexes in the same sector:
        if (hexId > 0) { // Add the previous hex if it exists
            neighbors.add(hexes.get(hexId - 1));
        }
        if (hexId < hexes.size() - 1) { // Add the next hex if it exists
            neighbors.add(hexes.get(hexId + 1));
        }

        return neighbors;
    }

    // String representation for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Sector (" + x + ", " + y + "): ");
        for (Hex hex : hexes) {
            sb.append("[ID: ").append(hex.getId())
                    .append(", Level: ").append(hex.getSystemType()).append("] ");
        }
        return sb.toString();
    }

    // Accessor for sector X coordinate
    public int getX() {
        return x;
    }

    // Accessor for sector Y coordinate
    public int getY() {
        return y;
    }

    // Check if this sector is Tri-Prime
    public boolean isTriPrime() {
        return isTriPrime;
    }
}