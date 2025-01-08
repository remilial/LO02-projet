package test;

import model.board.Hex;
import model.board.Sector;
import model.board.SystemType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SectorTest {

    @Test
    public void testSectorInitialization_TriPrime() {
        // Test pour le secteur central (1,1) - Tri-Prime
        Sector triPrimeSector = new Sector(1, 1);

        assertEquals(1, triPrimeSector.getHexes().size(), "Le secteur Tri-Prime doit avoir 1 hex.");
        assertEquals(SystemType.LEVEL3, triPrimeSector.getHexes().get(0).getSystemType(),
                "L'hex du secteur Tri-Prime doit être de niveau 3.");
    }

    @Test
    public void testSectorInitialization_Standard() {
        // Test pour les secteurs non centraux
        Sector sector = new Sector(0, 0);
        assertEquals(6, sector.getHexes().size(), "Un secteur standard doit avoir 6 hex.");

        long level2Count = sector.getHexes().stream()
                .filter(hex -> hex.getSystemType() == SystemType.LEVEL2)
                .count();

        long level1Count = sector.getHexes().stream()
                .filter(hex -> hex.getSystemType() == SystemType.LEVEL1)
                .count();

        // Vérifie qu'il y a exactement 1 hex de niveau 2 et 2 de niveau 1
        assertEquals(1, level2Count, "Il doit y avoir 1 hex de niveau 2.");
        assertEquals(2, level1Count, "Il doit y avoir 2 hex de niveau 1.");
    }

    @Test
    public void testHexesAreUnique() {
        Sector sector = new Sector(0, 0);

        long uniqueHexTypes = sector.getHexes().stream()
                .map(Hex::getSystemType)
                .distinct()
                .count();

        assertTrue(uniqueHexTypes > 1, "Les hex doivent avoir des niveaux différents.");
    }

    @Test
    public void testToString() {
        Sector sector = new Sector(2, 2);
        System.out.println(sector);
        assertNotNull(sector.toString(), "La méthode toString() ne doit pas retourner null.");
    }

    @Test
    public void testNoExtraLevel3Hex() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Sector sector = new Sector(x, y);
                long level3Count = sector.getHexes().stream()
                        .filter(hex -> hex.getSystemType() == SystemType.LEVEL3)
                        .count();

                if (x == 1 && y == 1) {
                    assertEquals(1, level3Count, "Le secteur central doit avoir 1 hex de niveau 3.");
                } else {
                    assertEquals(0, level3Count, "Les secteurs non centraux ne doivent pas avoir d'hex de niveau 3.");
                }
            }
        }
    }
}
