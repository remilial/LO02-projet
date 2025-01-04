package model.player.strategy;

import model.command.CommandType;
import model.player.Player;

// Expansion Strategy
class ExpansionStrategy implements Strategy {
    @Override
    public CommandType determineNextCommand(Player player) {
        // Prioritize Expand, if no capacity, fallback to Explore
        if (player.getCurrentSector().hasCapacityForShip()) {
            return CommandType.EXPAND;
        } else {
            return CommandType.EXPLORE;
        }
    }
}
