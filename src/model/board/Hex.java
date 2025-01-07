package model.board;
import java.io.Serializable;

public class Hex implements Serializable {
    private final int id;
    private SystemType systemType;
    private Ship ship;  // Ship present in this hex

    public Hex(int id, SystemType systemType) {
        this.id = id;
        this.systemType = systemType;
        this.ship = null;  // No ship initially
    }

    public int getId() {
        return id;
    }

    public SystemType getSystemType() {
        return systemType;
    }

    public void setSystemType(SystemType systemType) {
        this.systemType = systemType;
    }

    // Check if the hex has a ship
    public boolean hasShip() {
        return ship != null;
    }

    @Override
    public String toString() {
        return switch (systemType) {
            case LEVEL1 -> "1";
            case LEVEL2 -> "2";
            case LEVEL3 -> "3";
            default -> " ";
        };
    }
}
