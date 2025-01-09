package view;

import model.board.Board;
import model.board.Sector;
import model.board.Hex;
import model.board.SystemType;

public class ConsoleView {

    private static final String RESET = "\u001B[0m";   // Reset color
    private static final String GRAY = "\u001B[37m";   // Level 0 (neutral)
    private static final String MAGENTA = "\u001B[35m";// Level 1 (pink)
    private static final String ORANGE = "\u001B[33m"; // Level 2 (orange)
    private static final String GREEN = "\u001B[32m";  // Level 3 (green central)

    public void displayBoard(Board board) {
        int size = (int) Math.sqrt(board.getSectors().size());
        System.out.println("\n======= Plateau de Jeu =======\n");

        for (int y = size - 1; y >= 0; y--) {
            // Print each row of sectors
            for (int row = 0; row < 3; row++) {
                for (int x = 0; x < size; x++) {
                    Sector sector = board.getSector(x, y);

                    // Center sector special rendering
                    if (x == size / 2 && y == size / 2) {
                        if (row == 1) {
                            // Explicitly force green for center hex
                            System.out.print("  " + GREEN + "⬢" + RESET + "  ");
                        } else {
                            System.out.print("     ");
                        }
                    } else {
                        displayRow(sector, row);
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
        displayLegend();
        System.out.println("\n==============================\n");
    }

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

    // Legend with colors for hex explanation
    private void displayLegend() {
        System.out.println("\nLégende :");
        System.out.println(GRAY + "⬡ : Hex vide (niveau 0)" + RESET);
        System.out.println(MAGENTA + "⬢ : Hex de niveau 1 (rose)" + RESET);
        System.out.println(ORANGE + "⬣ : Hex de niveau 2 (orange)" + RESET);
        System.out.println(GREEN + "⬢ : Hex de niveau 3 (vert, secteur central)" + RESET);
    }

    private String getColoredHex(Hex hex) {
        // Ensure proper color handling for level 3
        if (hex.getSystemType() == SystemType.LEVEL3) {
            return GREEN + "⬢" + RESET;
        }

        return switch (hex.getSystemType()) {
            case NONE -> GRAY + "⬡" + RESET;
            case LEVEL1 -> MAGENTA + "⬢" + RESET;
            case LEVEL2 -> ORANGE + "⬣" + RESET;
            default -> GRAY + "⬡" + RESET;  // Fallback for undefined cases
        };
    }
}
