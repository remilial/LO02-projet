package fr.lo02.imperium_control.model.player.strategy;

import fr.lo02.imperium_control.model.command.Command;
import fr.lo02.imperium_control.model.command.CommandType;

import java.util.ArrayList;
import java.util.List;

public class ExpansionStrategy implements Strategy {

    @Override
    public List<Command> chooseCommands() {
        List<Command> chosenCommands = new ArrayList<>();
        chosenCommands.add(new Command(CommandType.EXPAND));       // Focus on expanding
        chosenCommands.add(new Command(CommandType.EXPLORE));      // Explore to find opportunities
        chosenCommands.add(new Command(CommandType.EXTERMINATE));  // Exterminate as the last priority

        return chosenCommands;
    }
}
