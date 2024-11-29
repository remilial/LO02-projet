package com.pocketimperium.model;
import com.pocketimperium.utils.Strategy;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    protected String name;
    protected Color color;
    protected List<Ship> ships;
    protected List<Card> slectedCards;
    protected Strategy strategy;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.ships = new ArrayList<>();
        this.slectedCards = new ArrayList<>();
    }

    public void chooseCard(){

    }

    public void playCard(){

    }

    public void getScore(){

    }
}
