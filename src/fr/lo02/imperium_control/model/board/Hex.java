package fr.lo02.imperium_control.model.board;

import java.io.Serializable;

public class Hex implements Serializable {
    private final int id; // Unique ID for the hex
    private SystemType systemType; // The type of system in this hex
    private boolean occupied; // Whether the hex is occupied or not
    private int capacity; // The maximum number of ships this hex can sustain
    private int sectorX; // X-coordinate of the sector
    private int sectorY; // Y-coordinate of the sector

    // Constructor
    public Hex(int id, SystemType systemType, int sectorX, int sectorY) {
        this.id = id;
        this.systemType = systemType;
        this.occupied = false;
        this.capacity = calculateCapacity(systemType); // Calculate capacity based on system type
        this.sectorX = sectorX;
        this.sectorY = sectorY;
    }

    // Accessors (Getters)
    public int getId() {
        return id;
    }

    public int getSectorX() {
        return sectorX;
    }

    public int getSectorY() {
        return sectorY;
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
        this.capacity = calculateCapacity(systemType); // Update capacity whenever system type changes
    }

    public void occupy() {
        this.occupied = true; // Mark the hex as occupied
    }

    public void vacate() {
        this.occupied = false; // Mark the hex as unoccupied
    }

    // Calculate capacity based on the formula: level + 1
    private int calculateCapacity(SystemType systemType) {
        switch (systemType) {
            case NONE:
                return 1; // Hex without a system, capacity = 1
            case LEVEL1:
                return 2; // Hex with Level 1 system, capacity = 2
            case LEVEL2:
                return 3; // Hex with Level 2 system, capacity = 3
            case LEVEL3:
                return 4; // Hex with Level 3 system, capacity = 4
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