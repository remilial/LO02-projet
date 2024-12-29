package model.player;

import model.board.Sector;
import java.util.Scanner;

public class RealPlayer extends Player {
    private final Scanner scanner = new Scanner(System.in);

    public RealPlayer(String name) {
        super(name);
    }

    @Override
    public void takeTurn() {
        System.out.println("\n--- Tour de " + name + " ---");
        System.out.println("1. Expand (Déployer des vaisseaux)");
        System.out.println("2. Explore (Déplacer des vaisseaux)");
        System.out.println("3. Exterminate (Attaquer)");

        System.out.print("Choisissez une action : ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> expand();
            case 2 -> explore();
            case 3 -> exterminate();
            default -> System.out.println("Choix invalide. Tour passé.");
        }
    }

    private void expand() {
        System.out.println(name + " choisit d'étendre son territoire (Expand).");
        // Logique pour déployer des vaisseaux
        // Ex : demander au joueur où déployer les vaisseaux
    }

    private void explore() {
        System.out.println(name + " choisit d'explorer (Explore).");
        // Logique pour déplacer des vaisseaux
    }

    private void exterminate() {
        System.out.println(name + " lance une attaque (Exterminate).");
        // Logique pour attaquer d'autres secteurs
    }
}