package model.player.strategy;

import model.command.CommandType;
import model.player.Player;

import java.util.Random;

// Random Strategy
class RandomStrategy implements Strategy {
    private Random random = new Random();

    @Override
    public CommandType determineNextCommand(Player player) {
        CommandType[] commands = CommandType.values();
        return commands[random.nextInt(commands.length)];
    }
}
