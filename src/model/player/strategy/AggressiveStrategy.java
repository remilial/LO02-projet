package model.player.strategy;

import model.command.CommandType;
import model.player.Player;

// Aggressive Strategy
public class AggressiveStrategy implements Strategy {
    @Override
    public CommandType determineNextCommand(Player player) {
        // Prioritize Extermination
        return CommandType.EXTERMINATE;
    }
}
