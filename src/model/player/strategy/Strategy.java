package model.player.strategy;

import model.command.CommandType;
import model.player.BotPlayer;
import model.player.Player;

// Strategy Interface
public interface Strategy {
    CommandType determineNextCommand(Player player);
    public abstract void execute(BotPlayer bot, CommandType command);
}

