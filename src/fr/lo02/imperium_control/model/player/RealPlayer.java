package fr.lo02.imperium_control.model.player;

import fr.lo02.imperium_control.model.command.Command;
import fr.lo02.imperium_control.model.command.CommandType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RealPlayer extends Player {
    private Scanner scanner;

    public RealPlayer(String name, int initialShipCount) {
        super(name, initialShipCount);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public List<Command> chooseCommands() {
        Scanner scanner = new Scanner(System.in);
        List<Command> availableCommands = List.of(
                new Command(CommandType.EXPAND),
                new Command(CommandType.EXPLORE),
                new Command(CommandType.EXTERMINATE)
        );
        List<Command> chosenCommands = new ArrayList<>();

        System.out.println("\nAvailable Commands:");
        for (int i = 0; i < availableCommands.size(); i++) {
            System.out.println(i + ": " + availableCommands.get(i).getType());
        }

        System.out.println("\nPlease choose the order of your commands by entering the indices (e.g., 0 1 2):");

        while (chosenCommands.size() < 3) {
            try {
                String input = scanner.nextLine();
                String[] indices = input.split("\\s+");

                if (indices.length != 3) {
                    System.out.println("You must select exactly 3 commands. Try again:");
                    continue;
                }

                chosenCommands.clear(); // Clear previous choices if retrying
                for (String index : indices) {
                    int commandIndex = Integer.parseInt(index);

                    if (commandIndex < 0 || commandIndex >= availableCommands.size()) {
                        throw new IllegalArgumentException("Invalid index: " + commandIndex);
                    }

                    Command command = availableCommands.get(commandIndex);

                    if (chosenCommands.contains(command)) {
                        throw new IllegalArgumentException("Duplicate commands are not allowed.");
                    }

                    chosenCommands.add(command);
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter indices (e.g., 0 1 2):");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Try again:");
            }
        }

        System.out.println("You have chosen the following commands:");
        chosenCommands.forEach(cmd -> System.out.println(cmd.getType()));

        return chosenCommands;
    }
}