package model.command;

import model.player.Player;
import model.board.Sector;
import model.board.Ship;

public class Command {
    private CommandType type;
    private Player player;

    public Command(CommandType type, Player player) {
        this.type = type;
        this.player = player;
    }

    public void execute() {
        switch (type) {
            case EXPAND:
                expand();
                break;
            case EXPLORE:
                explore();
                break;
            case EXTERMINATE:
                exterminate();
                break;
            default:
                System.out.println("Unknown command.");
        }
    }

    private void expand() {
        System.out.println(player.getName() + " is expanding.");
        Ship newShip = new Ship("S" + (player.getShips() + 1), player.getName(), player);
        if (player.getCurrentSector() != null && player.getCurrentSector().hasCapacityForShip()) {
            player.getCurrentSector().addShip(newShip);
            player.addShip(newShip);
            System.out.println("A new ship has been added to sector (" + player.getCurrentSector().getX() + ", " + player.getCurrentSector().getY() + ").");
        } else {
            System.out.println("No capacity to expand in the current sector.");
        }
    }

    private void explore() {
        System.out.println(player.getName() + " is exploring.");
        for (Sector neighbor : player.getCurrentSector().getNeighbors()) {
            if (neighbor.hasCapacityForShip()) {
                Ship ship = player.getActiveShip();
                player.getCurrentSector().moveShipToSector(ship, neighbor);
                System.out.println(player.getName() + " moved to sector (" + neighbor.getX() + ", " + neighbor.getY() + ").");
                return;
            }
        }
        System.out.println("No sectors available to explore.");
    }

    private void exterminate() {
        System.out.println(player.getName() + " is exterminating.");
        Sector currentSector = player.getCurrentSector();

        for (Sector neighbor : currentSector.getNeighbors()) {
            if (!neighbor.getShips().isEmpty()) {
                Ship invadingShip = player.getActiveShip();
                Ship defendingShip = neighbor.getShips().get(0);

                int invadingCount = currentSector.getShips().size();
                int defendingCount = neighbor.getShips().size();

                int destroyed = Math.min(invadingCount, defendingCount);

                for (int i = 0; i < destroyed; i++) {
                    currentSector.removeShip(invadingShip);
                    neighbor.removeShip(defendingShip);
                }

                if (currentSector.getShips().size() > 0) {
                    System.out.println(player.getName() + " has taken control of sector (" + neighbor.getX() + ", " + neighbor.getY() + ").");
                    currentSector.moveShipToSector(invadingShip, neighbor);
                } else {
                    System.out.println("Both fleets were destroyed.");
                }
                return;
            }
        }
        System.out.println("No enemy ships to exterminate.");
    }
}