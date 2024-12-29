package model.board;

import model.player.Player;

public class Sector {
    private final int x, y;
    private SystemType systemType;
    public Sector(int x, int y) {
        this.x = x;
        this.y = y;
        this.systemType = SystemType.NONE;
    }
    public String getInfo() {
        return "Sector (" + x + ", " + y + ") - " + systemType;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public SystemType getSystemType() {
        return systemType;
    }

    public void setSystemType(SystemType systemType) {
        this.systemType = systemType;
    }

    // A COMPLETER LE PLUS RAPIDEMENT POSSBILE POUR LES TEST
    public void addShips(int shipCount, Player player) {
    }
    public int getSystemLevel() {
    }
}
