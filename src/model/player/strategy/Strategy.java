package model.player.strategy;

import model.command.CommandType;
import model.player.Player;

// Strategy Interface
public interface Strategy {
    CommandType determineNextCommand(Player player);
}

