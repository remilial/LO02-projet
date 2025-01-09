package test;

import model.board.Hex;
import model.board.SystemType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HexTest {

    @Test
    public void testHexInitialization() {
        Hex hex = new Hex(1, SystemType.LEVEL1);

        assertEquals(1, hex.getId(), "L'ID de l'hex doit être 1.");
        assertEquals(SystemType.LEVEL1, hex.getSystemType(), "Le type de système doit être LEVEL1.");
        assertFalse(hex.isOccupied(), "L'hex ne doit pas être occupé à l'initialisation.");
    }

    @Test
    public void testSystemTypeChange() {
        Hex hex = new Hex(2, SystemType.NONE);

        hex.setSystemType(SystemType.LEVEL2);
        assertEquals(SystemType.LEVEL2, hex.getSystemType(), "Le type de système doit passer à LEVEL2.");
    }

    @Test
    public void testOccupation() {
        Hex hex = new Hex(3, SystemType.LEVEL1);

        hex.occupy();
        assertTrue(hex.isOccupied(), "L'hex doit être marqué comme occupé après appel à occupy().");

        hex.vacate();
        assertFalse(hex.isOccupied(), "L'hex doit être marqué comme libre après appel à vacate().");
    }

    @Test
    public void testToString() {
        Hex hex = new Hex(4, SystemType.LEVEL2);
        String hexDescription = hex.toString();

        assertTrue(hexDescription.contains("id=4"), "La description doit contenir l'ID de l'hex.");
        assertTrue(hexDescription.contains("LEVEL2"), "La description doit indiquer que l'hex est de type LEVEL2.");
        assertTrue(hexDescription.contains("occupied=false"), "La description doit indiquer que l'hex n'est pas occupé.");
    }
}
