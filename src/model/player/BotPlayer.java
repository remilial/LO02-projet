package model.player;

import model.player.Strategy;

public class BotPlayer extends Player {
    private Strategy strategy;

    public BotPlayer(String name, Strategy strategy) {
        super(name);
        this.strategy = strategy;
    }

    @Override
    public void takeTurn() {
        strategy.execute(this);
    }
}
