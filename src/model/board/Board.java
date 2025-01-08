package model.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {
    private static Board instance;
    private List<Sector> sectors;
    private final int BOARD_SIZE = 3;  // Plateau 3x3

    // Singleton : Retourne une instance unique du Board
    public static synchronized Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    // Constructeur privé pour empêcher l'instanciation directe
    private Board() {
        sectors = new ArrayList<>();
        initializeBoard();
    }

    // Initialisation des secteurs avec placement du secteur central
    private void initializeBoard() {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                Sector sector = new Sector(x, y);
                sectors.add(sector);

                // Place un hex de niveau 3 au centre (ligne du milieu)
                if (x == BOARD_SIZE / 2 && y == BOARD_SIZE / 2) {
                    sector.getHexes().get(0).setSystemType(SystemType.LEVEL3);
                }
            }
        }
    }

    // Récupère la liste des secteurs
    public List<Sector> getSectors() {
        return sectors;
    }

    // Retourne un secteur par ses coordonnées (x, y)
    public Sector getSector(int x, int y) {
        return sectors.stream()
                .filter(sector -> sector.getX() == x && sector.getY() == y)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sector non trouvé aux coordonnées : " + x + "," + y));
    }

    // Affiche l'état du plateau
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Plateau :\n");
        for (Sector sector : sectors) {
            sb.append(sector).append("\n");
        }
        return sb.toString();
    }

    // Réinitialise le plateau (utile pour recommencer une partie)
    public void resetBoard() {
        sectors.clear();
        initializeBoard();
    }
}