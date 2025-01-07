package model.player;

import model.command.Command;
import model.command.CommandType;
import java.util.Scanner;
import java.io.Serializable;

public class RealPlayer extends Player implements Serializable {
    private Scanner scanner;

    public RealPlayer(String name) {
        super(name);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void playTurn() {
        System.out.println(name + ", it's your turn!");
        System.out.println("Choose a command: [1] Expand, [2] Explore, [3] Exterminate");
        int choice = scanner.nextInt();

        CommandType commandType = mapChoiceToCommandType(choice);
        if (commandType != null) {
            Command command = new Command(commandType, this);
            command.execute();
        } else {
            System.out.println("Invalid command. Turn skipped.");
        }
    }

    private CommandType mapChoiceToCommandType(int choice) {
        switch (choice) {
            case 1:
                return CommandType.EXPAND;
            case 2:
                return CommandType.EXPLORE;
            case 3:
                return CommandType.EXTERMINATE;
            default:
                return null;
        }
    }
}   