package test;

import model.board.Hex;
import model.board.SystemType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HexTest {

    @Test
    public void testHexInitialization() {
        Hex hex = new Hex(1, SystemType.NONE);
        assertEquals(1, hex.getId());
        assertEquals(SystemType.NONE, hex.getSystemType());
        assertFalse(hex.isOccupied());
    }

    @Test
    public void testHexOccupation() {
        Hex hex = new Hex(2, SystemType.LEVEL1);
        assertFalse(hex.isOccupied());

        hex.occupy();
        assertTrue(hex.isOccupied());

        hex.vacate();
        assertFalse(hex.isOccupied());
    }

    @Test
    public void testSetSystemType() {
        Hex hex = new Hex(3, SystemType.LEVEL2);
        assertEquals(SystemType.LEVEL2, hex.getSystemType());

        hex.setSystemType(SystemType.LEVEL3);
        assertEquals(SystemType.LEVEL3, hex.getSystemType());
    }

    @Test
    public void testToString() {
        Hex hex = new Hex(4, SystemType.LEVEL1);
        String expected = "Hex{id=4, systemType=LEVEL1, occupied=false}";
        assertEquals(expected, hex.toString());
    }
}