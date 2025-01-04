package model.player;

import model.board.Hex;
import model.board.Sector;
import model.board.Ship;
import model.board.SystemType;

import java.util.List;

public abstract class Player {
    protected String name;
    protected Sector currentSector;
    protected Hex currentHex;
    protected int ships;
    protected List<Ship> shipList;

    public Player(String name) {
        this.name = name;
        this.ships = 15;
        this.currentSector = null;
        this.currentHex = null;
        this.shipList = new java.util.ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Sector getCurrentSector() {
        return currentSector;
    }

    public void setCurrentSector(Sector sector) {
        this.currentSector = sector;
    }

    public Hex getCurrentHex() {
        return currentHex;
    }

    public void setCurrentHex(Hex hex) {
        this.currentHex = hex;
    }

    public Ship getActiveShip() {
        return shipList.isEmpty() ? null : shipList.get(0);
    }

    public void addShip(Ship ship) {
        shipList.add(ship);
        ships++;
    }

    public void removeShip(Ship ship) {
        shipList.remove(ship);
        ships--;
    }

    public int getShips() {
        return ships;
    }

    public void moveShipToHex(Ship ship, Hex targetHex) {
        if (currentHex != null) {
            currentHex.setSystemType(SystemType.NONE);  // Liberate current hex
        }
        ship.setCurrentHex(targetHex);
        targetHex.setSystemType(SystemType.LEVEL1);  // Mark new hex as occupied
        setCurrentHex(targetHex);
        System.out.println(name + " moved ship to hex " + targetHex.getId());
    }

    public Ship getShip() {
        if (shipList.isEmpty()) {
            System.out.println("No ships available for " + name);
            return null;
        }
        return shipList.get(0);  // Return the first available ship
    }

    public List<Ship> getShipList() {
        return shipList;
    }



    public abstract void playTurn();
}