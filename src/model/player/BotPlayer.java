package model.player;

import model.command.Command;
import model.command.CommandType;
import model.player.strategy.Strategy;

public class BotPlayer extends Player {
    private Strategy strategy;

    public BotPlayer(String name, Strategy strategy) {
        super(name);
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void playTurn() {
        System.out.println(name + " (Bot) is taking a turn.");
        CommandType commandType = strategy.determineNextCommand(this);
        Command command = new Command(commandType, this);
        command.execute();
    }

    public Strategy getStrategy() {
        return strategy;
    }

}