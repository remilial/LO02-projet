package model.player;

import model.board.Hex;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Hex> occupiedHexes;
    private int remainingShips;

    public Player(String name, int initialShipCount) {
        this.name = name;
        this.remainingShips = initialShipCount;
        this.occupiedHexes = new ArrayList<>();
    }

    // Place a ship in a hex
    public void addShipToHex(Hex hex) {
        if (remainingShips > 0) {
            if (!hex.isOccupied()) {
                hex.occupy();  // Ensure the hex is marked as occupied
                occupiedHexes.add(hex);
                remainingShips--;
            } else {
                throw new IllegalStateException("Hex is already occupied.");
            }
        } else {
            throw new IllegalStateException("No remaining ships to place.");
        }
    }

    // Accessors
    public String getName() {
        return name;
    }

    public int getRemainingShips() {
        return remainingShips;
    }

    public List<Hex> getOccupiedHexes() {
        return occupiedHexes;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', remainingShips=" + remainingShips + '}';
    }
}