package fr.lo02.imperium_control.model.player.strategy;

import fr.lo02.imperium_control.model.command.Command;
import java.util.List;

public interface Strategy {
    List<Command> chooseCommands();
}
