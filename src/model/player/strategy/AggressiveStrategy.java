package model.player.strategy;

import model.command.CommandType;
import model.player.BotPlayer;
import model.player.Player;

// Aggressive Strategy
public class AggressiveStrategy implements Strategy {
    @Override
    public CommandType determineNextCommand(Player player) {
        // Prioritize Extermination
        return CommandType.EXTERMINATE;
    }

    @Override
    public void execute(BotPlayer bot, CommandType command) {
        if (command == CommandType.EXTERMINATE) {
            new model.command.Command(CommandType.EXTERMINATE, bot).execute();
        } else {
            new model.command.Command(CommandType.EXPLORE, bot).execute();
        }
    }
}
