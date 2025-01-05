package view.cli;

import model.board.Board;
import model.board.Sector;
import model.player.Game;
import model.player.Player;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    // Display the initial game start message
    public void displayGameStartMessage() {
        System.out.println("\nBienvenue dans Pocket Imperium !");
        System.out.println("Le jeu de stratégie galactique commence maintenant !");
        System.out.println("Préparez-vous à conquérir l'univers !");
    }

    // Display the current state of the board (simple overview)
    public void displayBoardState(Board board) {
        System.out.println("\n--- État du plateau ---");
        List<Sector> sectors = board.getSectors();
        for (Sector sector : sectors) {
            System.out.println("Secteur (" + sector.getX() + ", " + sector.getY() + ")");
            System.out.println("Type: " + sector.getSystemType());
            System.out.println("Vaisseaux: " + sector.getShips().size() + "\n");
        }
    }

    // Display whose turn it is
    public void displayPlayerTurn(Player player) {
        System.out.println("\nC'est au tour de " + player.getName() + " de jouer.");
        System.out.println("Veuillez choisir votre action :");
        System.out.println("[1] Expand (Expansion de flotte)");
        System.out.println("[2] Explore (Explorer de nouveaux secteurs)");
        System.out.println("[3] Exterminate (Envahir les systèmes ennemis)");
    }

    // Get player's command input and return selection
    public int getPlayerCommandInput() {
        int choice = -1;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print("Entrez votre choix (1-3) : ");
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) {
                    valid = true;
                } else {
                    System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 3.");
                }
            } catch (Exception e) {
                System.out.println("Entrée incorrecte. Veuillez entrer un nombre valide.");
                scanner.nextLine();  // Clear invalid input
            }
        }
        return choice;
    }

    // Display game over message
    public void displayGameOver() {
        System.out.println("\n--- Fin de la partie ---");
        System.out.println("Merci d'avoir joué à Pocket Imperium !");
    }

    // Show available options at game start (new game, load, exit)
    public int showMainMenu() {
        System.out.println("\nMenu Principal :");
        System.out.println("1. Nouvelle Partie");
        System.out.println("2. Charger une Partie");
        System.out.println("3. Voir les Règles");
        System.out.println("4. Quitter");
        System.out.print("Choisissez une option : ");

        int choice = -1;
        boolean valid = false;

        while (!valid) {
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 4) {
                    valid = true;
                } else {
                    System.out.println("Option invalide. Réessayez.");
                }
            } catch (Exception e) {
                System.out.println("Entrée incorrecte. Veuillez entrer un nombre entre 1 et 4.");
                scanner.nextLine();
            }
        }
        return choice;
    }
}