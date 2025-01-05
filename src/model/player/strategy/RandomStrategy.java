package model.player.strategy;

import model.command.CommandType;
import model.player.BotPlayer;
import model.player.Player;

import java.util.Random;

public class RandomStrategy implements Strategy {
    private Random random;

    public RandomStrategy() {
        this.random = new Random();
    }

    @Override
    public CommandType determineNextCommand(Player player) {
        CommandType[] commands = CommandType.values();
        return commands[random.nextInt(commands.length)];  // Randomly pick a command
    }

    @Override
    public void execute(BotPlayer bot, CommandType command) {
        new model.command.Command(command, bot).execute();
    }
}