package model.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {

    private static Board instance;
    private Sector[][] sectors;

    // ANSI color codes for console output
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";

    // Constructeur privé pour le singleton
    private Board() {
        sectors = new Sector[3][3];  // Plateau 3x3
        initializeSectors();
    }

    // Retourne l'instance unique (Singleton)
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    // Initialise les secteurs du plateau
    private void initializeSectors() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                sectors[x][y] = new Sector(x, y);
            }
        }
    }

    // Retourne un secteur en fonction des coordonnées
    public Sector getSector(int x, int y) {
        if (x >= 0 && x < 3 && y >= 0 && y < 3) {
            return sectors[x][y];
        }
        throw new IllegalArgumentException("Coordonnées invalides");
    }

    // Affiche le plateau avec des indications visuelles colorées
    public void displayBoard() {
        System.out.println("\n=== Carte de Pocket Imperium ===");
        printColumnLabels();

        for (int y = 0; y < 3; y++) {
            for (int row = 0; row < 3; row++) {
                System.out.print((3 - y) + " ");  // Numéro de ligne à gauche
                for (int x = 0; x < 3; x++) {
                    displaySectorRow(sectors[x][y], row);
                    System.out.print("   ");  // Espacement entre secteurs
                }
                System.out.print((3 - y));  // Numéro de ligne à droite
                System.out.println();
            }
            System.out.println();  // Ligne vide pour séparer les secteurs
        }
        printColumnLabels();
    }

    // Affiche les étiquettes de colonnes
    private void printColumnLabels() {
        System.out.print("   ");
        for (int i = 0; i < 3; i++) {
            System.out.printf("  %d   ", i);
        }
        System.out.println();
    }

    // Affiche une rangée d'un secteur avec des indications de vaisseaux et systèmes colorés
    private void displaySectorRow(Sector sector, int row) {
        System.out.print("[");
        for (int i = row * 2; i < (row + 1) * 2; i++) {
            if (i < sector.getHexes().size()) {
                Hex hex = sector.getHexes().get(i);
                System.out.print(getColoredHexDisplay(hex));
            } else {
                System.out.print("   ");
            }
        }
        System.out.print("]");
    }

    // Renvoie une chaîne colorée pour représenter le niveau du système hex
    private String getColoredHexDisplay(Hex hex) {
        String display;
        switch (hex.getSystemType()) {
            case LEVEL1 -> display = YELLOW + " 1 " + RESET;
            case LEVEL2 -> display = BLUE + " 2 " + RESET;
            case LEVEL3 -> display = RED + " 3 " + RESET;
            default -> display = GREEN + " 0 " + RESET;
        }
        return display;
    }

    // Retourne la liste de tous les secteurs du plateau
    public List<Sector> getSectors() {
        List<Sector> sectorsList = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                sectorsList.add(getSector(x, y));
            }
        }
        return sectorsList;
    }

    // Réinitialise le plateau (retire tous les vaisseaux)
    public void resetBoard() {
        for (Sector sector : getSectors()) {
            sector.clearShips();
        }
    }
}