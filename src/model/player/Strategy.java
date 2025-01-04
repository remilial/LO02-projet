package model.player;

import model.command.Command;
import model.command.CommandType;
import java.util.List;
import java.util.Random;

// Strategy Interface
public interface Strategy {
    CommandType determineNextCommand(Player player);
}

// Aggressive Strategy
class AggressiveStrategy implements Strategy {
    @Override
    public CommandType determineNextCommand(Player player) {
        // Prioritize Extermination
        return CommandType.EXTERMINATE;
    }
}

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

// Random Strategy
class RandomStrategy implements Strategy {
    private Random random = new Random();

    @Override
    public CommandType determineNextCommand(Player player) {
        CommandType[] commands = CommandType.values();
        return commands[random.nextInt(commands.length)];
    }
}
