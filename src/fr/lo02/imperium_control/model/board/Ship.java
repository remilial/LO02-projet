package fr.lo02.imperium_control.model.board;

import java.io.Serializable;

public class Ship implements Serializable {
    private final int id;
    private final String owner;  // Player identifier (could be color or player name)
    private boolean active;      // Whether the ship is in play

    // Constructor
    public Ship(int id, String owner) {
        this.id = id;
        this.owner = owner;
        this.active = true;  // Ships are active upon creation
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isActive() {
        return active;
    }

    // Methods to manage ship status
    public void destroy() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", active=" + active +
                '}';
    }
}
