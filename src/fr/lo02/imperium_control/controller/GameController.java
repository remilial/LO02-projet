package fr.lo02.imperium_control.controller;

import fr.lo02.imperium_control.model.board.Board;
import fr.lo02.imperium_control.model.command.Command;
import fr.lo02.imperium_control.model.player.Player;
import fr.lo02.imperium_control.view.ConsoleView;

import java.util.List;

public class GameController {
    private final Board board;
    private final ConsoleView consoleView;

    public GameController() {
        this.board = Board.getInstance();
        this.consoleView = new ConsoleView();
    }

    public void displayBoard() {
        consoleView.displayBoard(board);
    }

    public void executeCommand(Command command, Player player, List<Player> players) {
        command.execute(player, players, board);
    }

    public Board getBoard() {
        return board;
    }
}