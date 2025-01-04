package test;

import model.board.Board;
import org.junit.jupiter.api.Test;
import model.board.Sector;
import model.board.Ship;
import model.board.SystemType;
import model.player.RealPlayer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SectorTest {

    @Test
    public void testSectorInitialization() {
        Sector sector = new Sector(1, 1);
        assertEquals(1, sector.getX());
        assertEquals(1, sector.getY());
        assertEquals(SystemType.NONE, sector.getSystemType());
    }

    @Test
    public void testAddShipWithinCapacity() {
        Sector sector = new Sector(1, 1);
        sector.setSystemType(SystemType.LEVEL1);  // LEVEL1 allows 2 ships

        RealPlayer player = new RealPlayer("Player1");
        Ship ship1 = new Ship("S1", "Red", player);
        Ship ship2 = new Ship("S2", "Red", player);

        sector.addShip(ship1);
        sector.addShip(ship2);

        // Print debug information
        System.out.println("Current Ship Count: " + sector.getShips().size());
        System.out.println("Expected Capacity: " + (1 + sector.getSystemType().ordinal()));

        // Check capacity logic
        assertFalse(sector.hasCapacityForShip());  // Should be full at 2 ships
    }



    @Test
    public void testAddShipExceedingCapacity() {
        Sector sector = new Sector(1, 1);
        sector.setSystemType(SystemType.LEVEL1);  // Capacity for 2 ships

        RealPlayer player = new RealPlayer("Player1");
        Ship ship1 = new Ship("S1", "Red", player);
        Ship ship2 = new Ship("S2", "Red", player);
        Ship ship3 = new Ship("S3", "Red", player);  // This ship should exceed capacity

        sector.addShip(ship1);
        sector.addShip(ship2);

        assertThrows(IllegalStateException.class, () -> {
            sector.addShip(ship3);  // Expects exception here
        });

        assertEquals(2, sector.getShips().size());  // Sector should contain only 2 ships
    }

    @Test
    public void testRemoveShip() {
        Sector sector = new Sector(1, 1);
        sector.setSystemType(SystemType.LEVEL1);  // Set level to support 2 ships

        RealPlayer player = new RealPlayer("Player1");
        Ship ship1 = new Ship("S1", "Red", player);
        Ship ship2 = new Ship("S2", "Red", player);

        sector.addShip(ship1);
        sector.addShip(ship2);

        // Remove one ship
        sector.removeShip(ship1);
        assertEquals(1, sector.getShips().size());
        assertTrue(sector.hasCapacityForShip());
    }

    @Test
    public void testNeighborDetection() {
        Board board = Board.getInstance();
        Sector sector = board.getSector(1, 1);  // Center sector

        List<Sector> neighbors = sector.getNeighbors();
        assertEquals(4, neighbors.size());  // Center sector should have 4 neighbors

        Sector cornerSector = board.getSector(0, 0);  // Top-left corner
        List<Sector> cornerNeighbors = cornerSector.getNeighbors();
        assertEquals(2, cornerNeighbors.size());  // Corner should have 2 neighbors
    }

    @Test
    public void testMoveShipToAnotherSector() {
        Sector sectorA = new Sector(1, 1);
        Sector sectorB = new Sector(1, 2);
        RealPlayer player = new RealPlayer("Player1");
        Ship ship = new Ship("S1", "Blue", player);

        sectorA.addShip(ship);
        sectorA.moveShipToSector(ship, sectorB);

        assertFalse(sectorA.getShips().contains(ship));
        assertTrue(sectorB.getShips().contains(ship));
    }

    @Test
    public void testRemoveShipRestoresCapacity() {
        Sector sector = new Sector(1, 1);
        sector.setSystemType(SystemType.LEVEL1);  // 2 ship capacity
        RealPlayer player = new RealPlayer("Player1");
        Ship ship1 = new Ship("S1", "Red", player);
        Ship ship2 = new Ship("S2", "Red", player);

        sector.addShip(ship1);
        sector.addShip(ship2);
        sector.removeShip(ship1);

        assertTrue(sector.hasCapacityForShip());
    }

}
