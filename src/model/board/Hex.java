package model.board;

import java.io.Serializable;

public class Hex implements Serializable {
    private final int id;
    private SystemType systemType;
    private boolean occupied;
    private int capacity;

    // Constructor
    public Hex(int id, SystemType systemType) {
        this.id = id;
        this.systemType = systemType;
        this.occupied = false;
        this.capacity = calculateCapacity(systemType);
    }

    // Accessors (Getters)
    public int getId() {
        return id;
    }

    public SystemType getSystemType() {
        return systemType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int getCapacity() {
        return capacity;
    }

    // Modifiers (Setters)
    public void setSystemType(SystemType systemType) {
        this.systemType = systemType;
        this.capacity = calculateCapacity(systemType); // Update capacity when system type changes
    }

    public void occupy() {
        this.occupied = true;
    }

    public void vacate() {
        this.occupied = false;
    }

    // Calculate capacity based on the formula: level + 1
    private int calculateCapacity(SystemType systemType) {
        switch (systemType) {
            case NONE:
                return 1; // No system, capacity = 1
            case LEVEL1:
                return 2; // Level 1 system, capacity = 1 + 1
            case LEVEL2:
                return 3; // Level 2 system, capacity = 2 + 1
            case LEVEL3:
                return 4; // Level 3 system, capacity = 3 + 1
            default:
                throw new IllegalArgumentException("Unknown SystemType: " + systemType);
        }
    }

    // Debugging output
    @Override
    public String toString() {
        return "Hex{" +
                "id=" + id +
                ", systemType=" + systemType +
                ", occupied=" + occupied +
                ", capacity=" + capacity +
                '}';
    }
}