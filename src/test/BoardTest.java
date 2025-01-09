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

        assertSame(board1, board2, "L'instance de Board doit être la même (singleton).");
    }

    @Test
    public void testBoardInitialization() {
        Board board = Board.getInstance();
        assertEquals(9, board.getSectors().size(), "Le plateau doit contenir 9 secteurs (3x3).");

        long centralHexLevel3 = board.getSectors().stream()
                .filter(sector -> sector.getX() == 1 && sector.getY() == 1)
                .flatMap(sector -> sector.getHexes().stream())
                .filter(hex -> hex.getSystemType() == SystemType.LEVEL3)
                .count();

        assertEquals(1, centralHexLevel3, "Il doit y avoir exactement 1 hex de niveau 3 au centre.");
    }

    @Test
    public void testSectorRetrieval() {
        Board board = Board.getInstance();
        Sector sector = board.getSector(0, 0);

        assertNotNull(sector, "Le secteur (0,0) doit exister.");
        assertEquals(0, sector.getX(), "Les coordonnées X du secteur doivent être 0.");
        assertEquals(0, sector.getY(), "Les coordonnées Y du secteur doivent être 0.");
    }

    @Test
    public void testInvalidSectorRetrieval() {
        Board board = Board.getInstance();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            board.getSector(5, 5);
        });

        assertTrue(exception.getMessage().contains("Sector non trouvé"), "L'exception doit indiquer que le secteur est introuvable.");
    }

    @Test
    public void testResetBoard() {
        Board board = Board.getInstance();
        board.resetBoard();

        assertEquals(9, board.getSectors().size(), "Après réinitialisation, le plateau doit toujours contenir 9 secteurs.");
        long centralHexLevel3 = board.getSectors().stream()
                .filter(sector -> sector.getX() == 1 && sector.getY() == 1)
                .flatMap(sector -> sector.getHexes().stream())
                .filter(hex -> hex.getSystemType() == SystemType.LEVEL3)
                .count();

        assertEquals(1, centralHexLevel3, "Après réinitialisation, il doit y avoir un hex de niveau 3 au centre.");
    }
}