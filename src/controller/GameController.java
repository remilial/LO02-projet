package controller;

import model.board.Board;
import view.ConsoleView;

public class GameController {

    private Board board;
    private ConsoleView consoleView;

    public GameController() {
        board = Board.getInstance();
        consoleView = new ConsoleView();
    }

    public void startGame() {
        System.out.println("Démarrage du jeu...");
        consoleView.displayBoard(board);  // Affiche le plateau dès le début
    }
}
