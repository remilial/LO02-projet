package model.board;

import java.util.ArrayList;
import java.util.List;
import model.player.Player;

public class Sector {
    private final int x, y;
    private SystemType systemType;
    private List<Ship> ships;
    private List<Hex> hexes;

    public Sector(int x, int y) {
        this.x = x;
        this.y = y;
        this.systemType = SystemType.NONE;
        this.ships = new ArrayList<>();
        this.hexes = new ArrayList<>();
        initializeHexes();
    }

    private void initializeHexes() {
        // Pour chaque secteur on peut initialiser des hexes specifiques
        for (int i = 0; i < 6; i++) {
            hexes.add(new Hex(i, SystemType.NONE));
        }
    }

    public String getInfo() {
        return "Sector (" + x + ", " + y + ") - " + systemType + " - Ships: " + ships.size();
    }

    public void addShip(Ship ship) {
        if (!ships.contains(ship)) {  // Verifie que un ship n'est pas dÃ©jÃ  dans la liste
            ships.add(ship);
        }
    }

    public void removeShip(Ship ship) {
        ships.remove(ship);
    }

    public void moveShipToSector(Ship ship, Sector targetSector) {
        removeShip(ship);      // Enlever le vaisseau du secteur d'origine
        targetSector.addShip(ship); //Ajouter le ship dans le secteur cible
    }

    public boolean hasCapacityForShip() {
        int maxShips = 1 + systemType.ordinal(); //Le nombre maximum de ship que le secteur peut supporter
        return ships.size() < maxShips; //Le secteur peut accueillir un ship si la capacite n'est pas depasser
    }
    public List<Hex> getHexes() {
        return hexes;
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

    public List<Ship> getShips() {
        return ships;
    }

    public void setHexes(List<Hex> hexes) {
        this.hexes = hexes;
    }
}