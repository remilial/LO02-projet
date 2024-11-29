package com.pocketimperium.model;
import com.pocketimperium.utils.BoardGame;

import java.util.List;


public class Game {
    private static Game instance;
    List<Player> players;
    BoardGame boardGame;
    private int actualTurn;
    private final int maxTurns = 9;

    private Game(){

    }

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    public void initialiseGame(){

    }

    public void startGame(){

    }

    public void endGame(){

    }

    public void passTurn(){

    }

    public void getScore(){

    }

}
