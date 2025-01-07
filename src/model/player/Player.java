package model.player;

import model.board.Hex;
import model.board.Sector;
import model.board.Ship;
import model.board.SystemType;
import java.io.Serializable;

import java.util.List;

public abstract class Player implements Serializable {
    protected String name;
    protected Sector currentSector;
    protected int ships;
    protected List<Ship> shipList;

    public Player(String name) {
        this.name = name;
        this.ships = 15;
        this.currentSector = null;
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

    public List<Ship> getShipList() {
        return shipList;
    }

    public void moveShipToSector(Ship ship, Sector targetSector) {
        if (currentSector != null && currentSector.getShips().contains(ship)) {
            currentSector.removeShip(ship);
            targetSector.addShip(ship);
            ship.setCurrentSector(targetSector);
            setCurrentSector(targetSector);
            System.out.println(name + " moved ship to sector (" + targetSector.getX() + ", " + targetSector.getY() + ").");
        } else {
            System.out.println("Move failed: Ship not in the current sector.");
        }
    }

    public void moveShipToHex(Ship ship, Hex targetHex) {
        if (ship == null) {
            System.out.println("No ship selected to move.");
            return;
        }

        // Check if the ship is currently in a hex
        Hex currentHex = ship.getCurrentHex();

        // Clear the current hex if occupied
        if (currentHex != null) {
            currentHex.setSystemType(SystemType.NONE);  // Mark current hex as empty
            System.out.println(ship.getId() + " leaves hex " + currentHex.getId());
        }

        // Move the ship to the target hex
        ship.setCurrentHex(targetHex);
        targetHex.setSystemType(SystemType.LEVEL1);  // Mark the target hex as occupied
        ship.setCurrentHex(targetHex);

        System.out.println(ship.getId() + " moves to hex " + targetHex.getId());
    }

    public abstract void playTurn();
}