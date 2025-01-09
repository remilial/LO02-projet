package test;

import model.board.Hex;
import model.board.Sector;
import model.board.SystemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SectorTest {

    @Test
    public void testSectorInitialization_TriPrime() {
        // Test du secteur central (Tri-Prime)
        Sector triPrimeSector = new Sector(1, 1);  // Secteur au centre

        assertTrue(triPrimeSector.isTriPrime(), "Ce secteur devrait être Tri-Prime.");
        assertEquals(1, triPrimeSector.getHexes().size(), "Tri-Prime devrait contenir 1 hexagone.");

        Hex hex = triPrimeSector.getHexes().get(0);
        assertEquals(SystemType.LEVEL3, hex.getSystemType(), "L'hexagone du secteur Tri-Prime doit être de niveau 3.");
    }

    @Test
    public void testSectorInitialization_Standard() {
        // Test d'un secteur normal (pas Tri-Prime)
        Sector standardSector = new Sector(0, 0);  // Secteur classique

        assertFalse(standardSector.isTriPrime(), "Ce secteur ne devrait pas être Tri-Prime.");
        assertEquals(6, standardSector.getHexes().size(), "Un secteur classique doit contenir 6 hexagones.");

        long level2Count = standardSector.getHexes().stream()
                .filter(hex -> hex.getSystemType() == SystemType.LEVEL2)
                .count();

        long level1Count = standardSector.getHexes().stream()
                .filter(hex -> hex.getSystemType() == SystemType.LEVEL1)
                .count();

        // Vérifie qu'il y a exactement 1 hex de niveau 2 et 2 de niveau 1
        assertEquals(1, level2Count, "Il doit y avoir exactement 1 hexagone de niveau 2.");
        assertEquals(2, level1Count, "Il doit y avoir exactement 2 hexagones de niveau 1.");
    }

    @Test
    public void testHexUniqueness() {
        // Test que tous les hex d'un secteur sont uniques
        Sector sector = new Sector(2, 2);  // Un secteur standard

        long uniqueHexCount = sector.getHexes().stream()
                .map(Hex::getId)
                .distinct()
                .count();

        assertEquals(sector.getHexes().size(), uniqueHexCount, "Les hexagones doivent être uniques par ID.");
    }

    @Test
    public void testToString() {
        // Vérifie que la méthode toString() retourne bien une description du secteur
        Sector sector = new Sector(2, 2);
        String description = sector.toString();
        assertNotNull(description, "La description du secteur ne doit pas être nulle.");
        assertTrue(description.contains("Sector"), "La description doit contenir le mot 'Sector'.");
    }
}
