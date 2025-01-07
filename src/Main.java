import model.board.Board;
import model.board.Sector;
import model.board.Hex;
import model.board.SystemType;

public class Main {
    public static void main(String[] args) {
        Board board = Board.getInstance();
        board.displayBoard();

        // Testing sectors and their initialization
        System.out.println("\nAffichage des secteurs:");
        for (Sector sector : board.getSectors()) {
            System.out.println(sector.getInfo());
        }
        System.out.println("\u001B[32mTest green color!\u001B[0m");

    }
}
