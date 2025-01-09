package fr.lo02.imperium_control.model.player.strategy;

import fr.lo02.imperium_control.model.command.Command;
import fr.lo02.imperium_control.model.command.CommandType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomStrategy implements Strategy {

    @Override
    public List<Command> chooseCommands() {
        List<CommandType> commandTypes = List.of(
                CommandType.EXPAND,
                CommandType.EXPLORE,
                CommandType.EXTERMINATE
        );

        List<CommandType> shuffledCommands = new ArrayList<>(commandTypes);
        Collections.shuffle(shuffledCommands);

        List<Command> chosenCommands = new ArrayList<>();
        for (CommandType type : shuffledCommands) {
            chosenCommands.add(new Command(type));
        }

        return chosenCommands;
    }
}
