package model.board;

public class Board {
    private static Board instance;  // Singleton instance
    private Sector[][] sectors;

    // Private constructor to prevent instantiation
    private Board() {
        sectors = new Sector[3][3];
        initializeBoard();
    }

    // Public method to get the single instance
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    // Board initialization
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sectors[i][j] = new Sector(i, j);
            }
        }
    }

    // Get a sector by coordinates
    public Sector getSector(int x, int y) {
        if (x >= 0 && x < 3 && y >= 0 && y < 3) {
            return sectors[x][y];
        } else {
            throw new IllegalArgumentException("Invalid coordinates");
        }
    }

    // Display the board
    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(sectors[i][j].getInfo() + " ");
            }
            System.out.println();
        }
    }
}