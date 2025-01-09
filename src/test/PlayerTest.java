package test;

import model.board.Hex;
import model.board.SystemType;
import model.player.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerInitialization() {
        Player player = new Player("TestPlayer", 15);
        assertEquals("TestPlayer", player.getName());
        assertEquals(15, player.getRemainingShips());
        assertTrue(player.getOccupiedHexes().isEmpty(), "Occupied hexes should be empty on initialization.");
    }

    @Test
    public void testAddShipToHex() {
        Player player = new Player("TestPlayer", 15);
        Hex hex = new Hex(1, SystemType.LEVEL1);

        player.addShipToHex(hex);
        assertEquals(14, player.getRemainingShips(), "Remaining ships should decrease by 1 after deployment.");
        assertTrue(player.getOccupiedHexes().contains(hex), "Hex should be added to occupied hexes.");
        assertTrue(hex.isOccupied(), "Hex should be marked as occupied.");
    }

    @Test
    public void testAddShipWhenNoShipsRemaining() {
        Player player = new Player("TestPlayer", 0); // No ships remaining
        Hex hex = new Hex(1, SystemType.LEVEL1);

        Exception exception = assertThrows(IllegalStateException.class, () -> player.addShipToHex(hex));
        assertEquals("No remaining ships to place.", exception.getMessage());
        assertFalse(player.getOccupiedHexes().contains(hex), "Hex should not be added to occupied hexes.");
    }

    @Test
    public void testMultipleShipDeployment() {
        Player player = new Player("TestPlayer", 15);
        Hex hex1 = new Hex(1, SystemType.LEVEL1);
        Hex hex2 = new Hex(2, SystemType.LEVEL2);

        player.addShipToHex(hex1);
        player.addShipToHex(hex2);

        assertEquals(13, player.getRemainingShips(), "Remaining ships should decrease by 2 after deploying 2 ships.");
        List<Hex> occupiedHexes = player.getOccupiedHexes();
        assertEquals(2, occupiedHexes.size(), "Occupied hexes should contain both deployed hexes.");
        assertTrue(occupiedHexes.contains(hex1));
        assertTrue(occupiedHexes.contains(hex2));
    }

    @Test
    public void testPlayerToString() {
        Player player = new Player("TestPlayer", 15);
        String expected = "Player{name='TestPlayer', remainingShips=15}";
        assertEquals(expected, player.toString(), "Player toString() should match expected format.");
    }
}