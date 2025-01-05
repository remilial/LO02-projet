package controller.player;

import model.command.Command;
import model.command.CommandType;
import model.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandler {

    // Execute grouped commands with efficiency scaling
    public void executeCommands(Map<CommandType, List<Player>> groupedCommands) {
        for (Map.Entry<CommandType, List<Player>> entry : groupedCommands.entrySet()) {
            CommandType commandType = entry.getKey();
            List<Player> players = entry.getValue();

            double efficiency = 1.0 / players.size();  // Divide efficiency among players
            System.out.println("Executing " + commandType + " for " + players.size() + " player(s) at " + efficiency * 100 + "% efficiency.");

            for (Player player : players) {
                Command command = new Command(commandType, player);
                command.executeWithEfficiency(efficiency);
            }
        }
    }

    // Prepare commands based on player selections
    public Map<CommandType, List<Player>> groupCommands(Map<Player, List<CommandType>> chosenCommands, int phaseIndex) {
        Map<CommandType, List<Player>> groupedCommands = new HashMap<>();

        for (Map.Entry<Player, List<CommandType>> entry : chosenCommands.entrySet()) {
            Player player = entry.getKey();
            CommandType commandType = entry.getValue().get(phaseIndex);

            groupedCommands.computeIfAbsent(commandType, k -> new java.util.ArrayList<>()).add(player);
        }
        return groupedCommands;
    }
}
