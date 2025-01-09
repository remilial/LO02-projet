package fr.lo02.imperium_control.model.command;

import fr.lo02.imperium_control.model.board.Board;
import fr.lo02.imperium_control.model.board.Hex;
import fr.lo02.imperium_control.model.board.Sector;
import fr.lo02.imperium_control.model.board.SystemType;
import fr.lo02.imperium_control.model.player.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Command {

    private final CommandType type;

    public Command(CommandType type) {
        this.type = type;
    }

    public CommandType getType() {
        return type;
    }

    // Execute the command based on its type
    public void execute(Player player, List<Player> players, Board board) {
        long playersChoosingSameCommand = players.stream()
                .flatMap(p -> p.getCommands().stream())
                .filter(command -> command.getType() == this.type)
                .count();

        switch (type) {
            case EXPAND:
                performExpand(player, playersChoosingSameCommand);
                break;
            case EXPLORE:
                performExplore(player, playersChoosingSameCommand, board);
                break;
            case EXTERMINATE:
                performExterminate(player, playersChoosingSameCommand, board);
                break;
        }
    }

    // Perform Expand Command
    private void performExpand(Player player, long playersChoosingSameCommand) {
        int shipsToAdd = Math.max(0, 3 - (int) playersChoosingSameCommand);

        System.out.println(player.getName() + " is performing EXPAND, adding up to " + shipsToAdd + " ships.");

        for (Map.Entry<Hex, Integer> entry : player.getOccupiedHexes().entrySet()) {
            Hex hex = entry.getKey();

            // Skip hexes without systems
            if (hex.getSystemType() == SystemType.NONE) {
                continue;
            }

            int availableCapacity = hex.getCapacity() - entry.getValue();
            int shipsToDeploy = Math.min(availableCapacity, shipsToAdd);

            for (int i = 0; i < shipsToDeploy; i++) {
                player.addShipToHex(hex);
                shipsToAdd--;
                if (shipsToAdd == 0) break;
            }

            if (shipsToAdd == 0) break;
        }
    }

    // Perform Explore Command
    private void performExplore(Player player, long playersChoosingSameCommand, Board board) {
        int maxFleetMovements = Math.max(1, 2 - (int) playersChoosingSameCommand);

        System.out.println(player.getName() + " is performing EXPLORE with up to " + maxFleetMovements + " fleet movements.");

        for (int i = 0; i < maxFleetMovements; i++) {
            List<Hex> occupiedHexes = player.getOccupiedHexes().keySet().stream().collect(Collectors.toList());
            if (occupiedHexes.isEmpty()) break;

            Hex currentHex = occupiedHexes.get(0); // Just for example, use the first hex
            List<Hex> neighbors = findAdjacentHexes(currentHex, board);

            if (!neighbors.isEmpty()) {
                Hex targetHex = neighbors.get(0); // Move to the first neighbor as an example
                player.removeShipFromHex(currentHex);
                player.addShipToHex(targetHex);
                System.out.println(player.getName() + " moved ships from Hex " + currentHex.getId() + " to Hex " + targetHex.getId());
            }
        }
    }

    // Perform Exterminate Command
    private void performExterminate(Player player, long playersChoosingSameCommand, Board board) {
        int maxInvasions = Math.max(1, 2 - (int) playersChoosingSameCommand);

        System.out.println(player.getName() + " is performing EXTERMINATE with up to " + maxInvasions + " invasions.");

        for (int i = 0; i < maxInvasions; i++) {
            List<Hex> occupiedHexes = player.getOccupiedHexes().keySet().stream().collect(Collectors.toList());
            if (occupiedHexes.isEmpty()) break;

            Hex currentHex = occupiedHexes.get(0); // Example: Pick the first hex
            List<Hex> neighbors = findAdjacentHexes(currentHex, board);

            Hex targetHex = neighbors.stream()
                    .filter(hex -> board.isOccupiedByOpponent(hex, player))
                    .findFirst()
                    .orElse(null);

            if (targetHex != null) {
                System.out.println(player.getName() + " invades Hex " + targetHex.getId());
                int shipsToInvade = player.getShipsInHex(currentHex);
                int defenderShips = board.getShipsInHex(targetHex);

                int losses = Math.min(shipsToInvade, defenderShips);

                for (int j = 0; j < losses; j++) {
                    player.removeShipFromHex(currentHex);
                    board.getOwnerOfHex(targetHex).removeShipFromHex(targetHex);
                }

                if (shipsToInvade > defenderShips) {
                    player.addShipToHex(targetHex);
                }

                maxInvasions--;
                if (maxInvasions == 0) break;
            }
        }
    }

    private List<Hex> findAdjacentHexes(Hex hex, Board board) {
        Sector sector = board.getSector(hex.getSectorX(), hex.getSectorY());
        return sector.getAdjacentHexes(hex);
    }
}