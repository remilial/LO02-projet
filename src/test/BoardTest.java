package test;

import model.board.Board;
import model.board.Sector;
import model.board.SystemType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testSingletonInstance() {
        Board board1 = Board.getInstance();
        Board board2 = Board.getInstance();
        assertSame(board1, board2, "L'instance de Board doit être unique (Singleton)");
    }

    @Test
    public void testSectorInitialization() {
        Board board = Board.getInstance();
        assertEquals(9, board.getSectors().size(), "Le plateau doit contenir 9 secteurs (3x3)");

        Sector sector = board.getSector(2, 2);
        assertNotNull(sector, "Le secteur aux coordonnées (2,2) doit exister");
    }

    @Test
    public void testSectorHexes() {
        Board board = Board.getInstance();
        Sector sector = board.getSector(1, 1);
        long level2Count = sector.getHexes().stream()
                .filter(hex -> hex.getSystemType() == SystemType.LEVEL2)
                .count();
        assertEquals(1, level2Count, "Il doit y avoir exactement 1 hex de niveau 2 par secteur");

        long level1Count = sector.getHexes().stream()
                .filter(hex -> hex.getSystemType() == SystemType.LEVEL1)
                .count();
        assertEquals(2, level1Count, "Il doit y avoir exactement 2 hex de niveau 1 par secteur");
    }

    @Test
    public void testBoardReset() {
        Board board = Board.getInstance();
        Sector sectorBeforeReset = board.getSector(1, 1);

        board.resetBoard();
        Sector sectorAfterReset = board.getSector(1, 1);

        assertNotSame(sectorBeforeReset, sectorAfterReset, "Les secteurs doivent être recréés après reset");
    }
}
