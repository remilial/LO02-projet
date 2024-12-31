package model.player;

import model.command.Command;
import model.command.CommandType;
import java.util.Random;

public class BotPlayer extends Player {
    private Random random;

    public BotPlayer(String name) {
        super(name);
        this.random = new Random();
    }

    @Override
    public void playTurn() {
        System.out.println(name + " (Bot) is taking a turn.");
        CommandType[] commandOrder = determineCommandOrder();

        for (CommandType commandType : commandOrder) {
            Command command = new Command(commandType, this);
            command.execute();
        }
    }

    private CommandType[] determineCommandOrder() {
        CommandType[] commands = CommandType.values();
        CommandType[] selectedCommands = new CommandType[3];

        for (int i = 0; i < 3; i++) {
            selectedCommands[i] = commands[random.nextInt(commands.length)];
        }
        return selectedCommands;
    }
}