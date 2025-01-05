package model.board;

import model.player.Player;

public class Ship {
    private String id;
    private String color;
    private boolean isActive;
    private Player owner;
    private Sector currentSector;
    private Hex currentHex;


    public Ship(String id, String color, Player owner) {
        this.id = id;
        this.color = color;
        this.isActive = true;
        this.owner = owner;
        this.currentSector = null;
        this.currentHex = null;
    }

    @Override
    public String toString() {
        return "Ship{id='" + id + "', color='" + color + ", isActive=" +
                isActive + ", owner=" + ", sector=" +
                (currentSector != null ? currentSector.getInfo() : "None") + "}";
    }

    public String getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public boolean isActive() {
        return isActive;
    }

    public Player getOwner() {
        return owner;
    }

    public Sector getCurrentSector() {
        return currentSector;
    }

    public void setCurrentSector(Sector currentSector) {
        this.currentSector = currentSector;
    }

    public Hex getCurrentHex() {
        return currentHex;
    }

    public void setCurrentHex(Hex currentHex) {
        this.currentHex = currentHex;
    }

}