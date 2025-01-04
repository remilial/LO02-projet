package test;

import model.board.Board;
import model.board.SystemType;
import model.player.strategy.AggressiveStrategy;
import org.junit.jupiter.api.Test;
import model.board.Sector;
import model.board.Ship;
import model.player.RealPlayer;
import model.player.BotPlayer;
import model.player.Player;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerInitialization() {
        Player player = new RealPlayer("Player1");
        assertEquals("Player1", player.getName());
        assertEquals(15, player.getShips());  // Initial ships (based on constructor)
        assertNull(player.getCurrentSector());
    }

    @Test
    public void testAddShipToPlayer() {
        Player player = new RealPlayer("Player1");
        Ship ship = new Ship("S1", "Red", player);

        player.addShip(ship);
        assertEquals(16, player.getShips());  // Ship count should increase
        assertTrue(player.getShipList().contains(ship));
    }

    @Test
    public void testRemoveShipFromPlayer() {
        Player player = new RealPlayer("Player1");
        Ship ship = new Ship("S1", "Red", player);

        player.addShip(ship);
        player.removeShip(ship);

        assertEquals(15, player.getShips());  // Ship count should decrease
        assertFalse(player.getShipList().contains(ship));
    }

    @Test
    public void testShipMovement() {
        Sector sectorA = new Sector(1, 1);
        Sector sectorB = new Sector(1, 2);
        RealPlayer player = new RealPlayer("Player1");
        Ship ship = new Ship("S1", "Blue", player);

        sectorA.addShip(ship);
        player.setCurrentSector(sectorA);

        player.moveShipToHex(ship, sectorB.getHexes().get(0));
        assertEquals(sectorB.getHexes().get(0), ship.getCurrentHex());
    }

    @Test
    public void testBotPlayerTurnExecution() {
        // Create a starting sector for the bot
        Sector startingSector = new Sector(0, 0);
        Board.getInstance().getSector(0, 0).setSystemType(SystemType.LEVEL1);  // Ensure sector can hold ships

        // Initialize bot player with a strategy
        BotPlayer bot = new BotPlayer("BotPlayer", new model.player.strategy.AggressiveStrategy());
        bot.setCurrentSector(startingSector);  // Set the starting sector for the bot

        // Add a ship to the sector so the bot can perform actions
        Ship ship = new Ship("B1", "Blue", bot);
        startingSector.addShip(ship);
        bot.addShip(ship);

        // Execute bot's turn
        bot.playTurn();

        // Verify that the bot's strategy is not null
        assertNotNull(bot.getStrategy());
        assertEquals(1, startingSector.getShips().size());  // Ensure the ship remains after the turn
    }
}

