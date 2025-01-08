package model.board;

import java.io.Serializable;

public class Hex implements Serializable {
    private final int id;
    private SystemType systemType;
    private boolean occupied;

    // Constructeur
    public Hex(int id, SystemType systemType) {
        this.id = id;
        this.systemType = systemType;
        this.occupied = false;
    }

    // Accesseurs (Getters)
    public int getId() {
        return id;
    }

    public SystemType getSystemType() {
        return systemType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    // Modificateurs (Setters)
    public void setSystemType(SystemType systemType) {
        this.systemType = systemType;
    }

    public void occupy() {
        this.occupied = true;
    }

    public void vacate() {
        this.occupied = false;
    }

    // Affichage d'informations pour débuggage
    @Override
    public String toString() {
        return "Hex{" +
                "id=" + id +
                ", systemType=" + systemType +
                ", occupied=" + occupied +
                '}';
    }
}
