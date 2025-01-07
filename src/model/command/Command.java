package model.command;

import model.player.Player;
import model.board.Sector;
import model.board.Ship;
import java.io.Serializable;

public class Command implements Serializable {
    private CommandType type;
    private Player player;

    public Command(CommandType type, Player player) {
        this.type = type;
        this.player = player;
    }

    public void execute() {
        executeWithEfficiency(1.0);  // Default to full efficiency
    }

    public void executeWithEfficiency(double efficiency) {
        switch (type) {
            case EXPAND:
                expand(efficiency);
                break;
            case EXPLORE:
                explore(efficiency);
                break;
            case EXTERMINATE:
                exterminate(efficiency);
                break;
        }
    }

    private void expand(double efficiency) {
        int shipsToAdd = calculateUnitsToAdd(3, efficiency);
        Sector currentSector = player.getCurrentSector();
        for (int i = 0; i < shipsToAdd; i++) {
            if (currentSector.hasCapacityForShip()) {
                Ship ship = new Ship("Ship" + (player.getShips() + 1), "Blue", player);
                currentSector.addShip(ship);
                player.addShip(ship);
            } else {
                System.out.println("Sector capacity exceeded for expansion.");
                break;
            }
        }
        System.out.println(player.getName() + " added " + shipsToAdd + " ships.");
    }

    private void explore(double efficiency) {
        int fleetsToMove = calculateUnitsToAdd(3, efficiency);
        Sector currentSector = player.getCurrentSector();
        for (int i = 0; i < fleetsToMove; i++) {
            if (!currentSector.getNeighbors().isEmpty()) {
                Sector targetSector = currentSector.getNeighbors().get(0);  // Simplified for now
                player.moveShipToSector(player.getShipList().get(0), targetSector);
                System.out.println(player.getName() + " moved a fleet to sector (" + targetSector.getX() + ", " + targetSector.getY() + ").");
            }
        }
    }

    private void exterminate(double efficiency) {
        int systemsToInvade = calculateUnitsToAdd(3, efficiency);
        Sector currentSector = player.getCurrentSector();
        for (int i = 0; i < systemsToInvade; i++) {
            if (!currentSector.getShips().isEmpty()) {
                Ship enemyShip = currentSector.getShips().get(0);
                if (enemyShip.getOwner() != player) {
                    currentSector.removeShip(enemyShip);
                    System.out.println(player.getName() + " invaded and removed an enemy ship.");
                }
            }
        }
    }

    private int calculateUnitsToAdd(int maxUnits, double efficiency) {
        return (int) Math.ceil(maxUnits * efficiency);  // Ensure rounding up for fairness
    }
}
