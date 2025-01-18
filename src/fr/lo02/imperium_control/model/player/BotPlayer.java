package fr.lo02.imperium_control.model.player;

import fr.lo02.imperium_control.model.command.Command;
import fr.lo02.imperium_control.model.player.strategy.Strategy;

import java.util.List;

public class BotPlayer extends Player {
    private Strategy strategy;

    public BotPlayer(String name, int initialShipCount) {
        super(name, initialShipCount);
    }

    @Override
    public List<Command> chooseCommands() {
        return strategy.chooseCommands();
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Object getStrategy() {
        return strategy;
    }
}