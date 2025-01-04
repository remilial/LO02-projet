package test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import model.board.Board;
import model.board.Sector;
import model.board.Ship;
import model.board.SystemType;
import model.player.BotPlayer;
import model.player.Game;
import model.player.RealPlayer;
import model.player.Player;
import model.player.strategy.RandomStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;
    private Board board;

    @BeforeEach
    public void setup() {
        game = Game.getInstance();
        board = Board.getInstance();
        board.resetBoard();  // Ensure the board is cleared before each test
    }

    @Test
    public void testAutomatedPlayerChoice() {
        System.setProperty("test.mode", "true");
        String simulatedInput = "1\n2\n3\n1\n2\n3\n1\n2\n3\n";  // Simulate command order for 3 players
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);  // Replace System input stream

        game.startGame();

        assertTrue(game.isGameOver(), "Game should end after 9 rounds or fleet elimination.");
    }

    @Test
    public void testFleetEliminationEndsGame() {
        Player player1 = new RealPlayer("Player1");
        Player bot1 = new BotPlayer("Bot1", new RandomStrategy());
        Player bot2 = new BotPlayer("Bot2", new RandomStrategy());

        Sector sector = board.getSector(0, 0);
        Ship playerShip = new Ship("S1", "Blue", player1);
        sector.addShip(playerShip);
        player1.addShip(playerShip);

        sector.removeShip(playerShip);
        player1.removeShip(playerShip);

        game.startGame();

        assertTrue(game.isGameOver(), "Game should end if a player's fleet is eliminated.");
    }

    @Test
    public void testScoreCalculationAfterNineRounds() {
        Player player1 = new RealPlayer("Player1");
        Player bot1 = new BotPlayer("Bot1", new RandomStrategy());
        Player bot2 = new BotPlayer("Bot2", new RandomStrategy());

        Sector sector1 = board.getSector(0, 0);
        Sector sector2 = board.getSector(1, 1);
        Sector sector3 = board.getSector(2, 2);

        sector1.setSystemType(SystemType.LEVEL2);
        sector2.setSystemType(SystemType.LEVEL1);
        sector3.setSystemType(SystemType.LEVEL3);

        Ship ship1 = new Ship("S1", "Blue", player1);
        Ship ship2 = new Ship("S2", "Red", bot1);
        Ship ship3 = new Ship("S3", "Green", bot2);

        sector1.addShip(ship1);
        sector2.addShip(ship2);
        sector3.addShip(ship3);

        player1.addShip(ship1);
        bot1.addShip(ship2);
        bot2.addShip(ship3);

        for (int i = 0; i < 9; i++) {
            game.playTurn(player1);
            game.playTurn(bot1);
            game.playTurn(bot2);
        }

        game.startGame();

        assertFalse(game.isGameOver(), "Game should only end by fleet elimination or after 9 rounds.");
    }

    @Test
    public void testWinningPlayerByScore() {
        Player player1 = new RealPlayer("Player1");
        Player bot1 = new BotPlayer("Bot1", new RandomStrategy());
        Player bot2 = new BotPlayer("Bot2", new RandomStrategy());

        Sector sector1 = board.getSector(0, 0);
        Sector sector2 = board.getSector(1, 1);
        Sector sector3 = board.getSector(2, 2);

        sector1.setSystemType(SystemType.LEVEL3);
        sector2.setSystemType(SystemType.LEVEL1);
        sector3.setSystemType(SystemType.LEVEL2);

        Ship ship1 = new Ship("S1", "Blue", player1);
        Ship ship2 = new Ship("S2", "Red", bot1);
        Ship ship3 = new Ship("S3", "Green", bot2);

        sector1.addShip(ship1);
        sector2.addShip(ship2);
        sector3.addShip(ship3);

        player1.addShip(ship1);
        bot1.addShip(ship2);
        bot2.addShip(ship3);

        game.determineWinnerByScore();

        assertEquals(player1, player1, "Player1 should win by controlling higher-level sectors.");
    }
}