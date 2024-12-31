package model.command;

import model.player.Player;
import model.board.Sector;
import model.board.Hex;
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
        Hex currentHex = player.getCurrentHex();
        Ship ship = player.getActiveShip();
        for (Hex hex : player.getCurrentSector().getHexes()) {
            if (!hex.equals(currentHex)) {
                player.moveShipToHex(ship, hex);
                System.out.println(player.getName() + " moved to hex " + hex.getId());
                return;
            }
        }
        System.out.println("No available hexes to explore.");
    }

    private void exterminate() {
        System.out.println(player.getName() + " is exterminating.");
        // Add extermination logic here
    }
}
