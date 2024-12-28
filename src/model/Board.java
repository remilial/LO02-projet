package model;

public class Board {
    private Sector[][] sectors;
    public Board() {
        sectors = new Sector[3][3];
        initializeBoard();
    }
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sectors[i][j] = new Sector(i, j);
            }
        }
    }
    public Sector getSector(int x, int y) {
        if (x >= 0 && x < 3 && y >= 0 && y < 3) {
            return sectors[x][y];
        } else {
            throw new IllegalArgumentException("Coordonnées invalides");
        }
    }
    public Sector[] getNeighbors(int x, int y) {
        Sector[] neighbors = new Sector[4];
        int index = 0;
        if (x > 0) { neighbors[index++] = sectors[x - 1][y]; } // Haut
        if (x < 2) { neighbors[index++] = sectors[x + 1][y]; } // Bas
        if (y > 0) { neighbors[index++] = sectors[x][y - 1]; } // Gauche
        if (y < 2) { neighbors[index++] = sectors[x][y + 1]; } // Droite
        Sector[] validNeighbors = new Sector[index];
        System.arraycopy(neighbors, 0, validNeighbors, 0, index);
        return validNeighbors;
    }
    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(sectors[i][j].getInfo() + " ");
            }
            System.out.println();
        }
    }
}
