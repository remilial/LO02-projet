package fr.lo02.imperium_control.view;

import fr.lo02.imperium_control.model.board.Board;
import fr.lo02.imperium_control.model.board.Hex;
import fr.lo02.imperium_control.model.board.Sector;
import fr.lo02.imperium_control.model.board.SystemType;

public class ConsoleView {

    private static final String RESET = "\u001B[0m";   // Reset color
    private static final String GRAY = "\u001B[37m";   // Level 0 (neutral)
    private static final String MAGENTA = "\u001B[35m";// Level 1 (pink)
    private static final String ORANGE = "\u001B[33m"; // Level 2 (orange)
    private static final String GREEN = "\u001B[32m";  // Level 3 (green central)

    public void displayBoard(Board board) {
        int size = (int) Math.sqrt(board.getSectors().size());
        System.out.println("\n======= Game Board =======\n");

        for (int y = size - 1; y >= 0; y--) {
            // Print each row of sectors
            for (int row = 0; row < 3; row++) { // Each sector has 3 rows of hexes
                for (int x = 0; x < size; x++) {
                    Sector sector = board.getSector(x, y);

                    // Render rows for each sector
                    if (sector != null) {
                        displayRow(sector, row);
                    } else {
                        System.out.print("     ");
                    }
                    System.out.print(" "); // Add spacing between sectors
                }
                System.out.println();
            }
        }
        displayLegend();
        System.out.println("\n==========================\n");
    }

    private void displayRow(Sector sector, int row) {
        int startIndex = row * 2; // Each row has 2 hexes
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

    private void displayLegend() {
        System.out.println("\nLegend:");
        System.out.println(GRAY + "⬡ : Empty Hex (Level 0)" + RESET);
        System.out.println(MAGENTA + "⬢ : Level 1 Hex (Pink)" + RESET);
        System.out.println(ORANGE + "⬢ : Level 2 Hex (Orange)" + RESET);
        System.out.println(GREEN + "⬢ : Level 3 Hex (Green - Central Sector)" + RESET);
    }

    private String getColoredHex(Hex hex) {
        if (hex.getSystemType() == SystemType.LEVEL3) {
            return GREEN + "⬢" + RESET;
        }

        return switch (hex.getSystemType()) {
            case NONE -> GRAY + "⬡" + RESET;
            case LEVEL1 -> MAGENTA + "⬢" + RESET;
            case LEVEL2 -> ORANGE + "⬢" + RESET;
            case LEVEL3 -> GREEN + "⬢" + RESET;
        };
    }
}