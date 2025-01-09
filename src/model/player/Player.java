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
            occupiedHexes.add(hex);
            remainingShips--;
        } else {
            throw new IllegalStateException("No remaining ships to place.");
        }
    }

    // Accessors
    public int getRemainingShips() {
        return remainingShips;
    }

    public List<Hex> getOccupiedHexes() {
        return occupiedHexes;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', remainingShips=" + remainingShips + '}';
    }
}