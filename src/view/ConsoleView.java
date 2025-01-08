package view;

import model.board.Board;
import model.board.Sector;
import model.board.Hex;

public class ConsoleView {

    // Codes couleurs ANSI
    private static final String RESET = "\u001B[0m";   // Reset couleur
    private static final String GRAY = "\u001B[37m";   // Niveau 0 (hex neutre)
    private static final String MAGENTA = "\u001B[35m"; // Niveau 1 (rose)
    private static final String ORANGE = "\u001B[33m";  // Niveau 2 (orange/jaune)
    private static final String GREEN = "\u001B[32m";   // Niveau 3 (vert)

    public void displayBoard(Board board) {
        int size = (int) Math.sqrt(board.getSectors().size());
        System.out.println("\n======= Plateau de Jeu =======\n");

        for (int y = size - 1; y >= 0; y--) {
            for (int row = 0; row < 3; row++) {
                for (int x = 0; x < size; x++) {
                    Sector sector = board.getSector(x, y);
                    displayRow(sector, row);
                    System.out.print(" ");  // Espace entre secteurs
                }
                System.out.println();
            }
        }

        // Affiche la légende colorée
        displayLegend();

        System.out.println("\n==============================\n");
    }

    // Affiche une ligne spécifique (0, 1, 2) d'un secteur
    private void displayRow(Sector sector, int row) {
        int startIndex = row * 2;
        System.out.print("|");

        if (startIndex < sector.getHexes().size()) {
            System.out.print(getColoredHex(sector.getHexes().get(startIndex)));
        } else {
            System.out.print("  ");
        }

        if (startIndex + 1 < sector.getHexes().size()) {
            System.out.print(getColoredHex(sector.getHexes().get(startIndex + 1)));
        } else {
            System.out.print("  ");
        }

        System.out.print("|");
    }

    // Légende avec couleurs pour expliquer les hexagones
    private void displayLegend() {
        System.out.println("\nLégende :");
        System.out.println(GRAY + "⬡ : Hex vide (niveau 0)" + RESET);
        System.out.println(MAGENTA + "⬢ : Hex de niveau 1 (rose)" + RESET);
        System.out.println(ORANGE + "⬣ : Hex de niveau 2 (orange)" + RESET);
        System.out.println(GREEN + "⬢ : Hex de niveau 3 (vert, secteur central)" + RESET);
    }

    // Renvoie l'hex coloré en fonction de son niveau
    private String getColoredHex(Hex hex) {
        return switch (hex.getSystemType()) {
            case NONE -> GRAY + "⬡" + RESET;     // Niveau 0 (neutre)
            case LEVEL1 -> MAGENTA + "⬢" + RESET; // Niveau 1 (rose)
            case LEVEL2 -> ORANGE + "⬣" + RESET;  // Niveau 2 (orange)
            case LEVEL3 -> GREEN + "⬢" + RESET;   // Niveau 3 (vert central)
        };
    }
}
