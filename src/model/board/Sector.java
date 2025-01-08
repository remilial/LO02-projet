package model.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sector implements Serializable {
    private final int x, y;
    private List<Hex> hexes;
    private static final Random random = new Random();
    private boolean isTriPrime;  // Indique si le secteur est Tri-Prime (Niveau 3)

    // Constructeur
    public Sector(int x, int y) {
        this.x = x;
        this.y = y;
        this.hexes = new ArrayList<>();
        this.isTriPrime = (x == 1 && y == 1);  // Si le secteur est au centre (1,1), c'est Tri-Prime
        initializeHexes();
    }

    // Initialise les hexagones dans le secteur
    private void initializeHexes() {
        hexes.clear();  // Réinitialise la liste d'hex

        // Si le secteur est Tri-Prime, ajoute un seul hex de niveau 3
        if (isTriPrime) {
            hexes.add(new Hex(0, SystemType.LEVEL3));
        } else {
            // Crée 6 hexagones
            for (int i = 0; i < 6; i++) {
                hexes.add(new Hex(i, SystemType.NONE));
            }

            // Sélectionne aléatoirement un hex pour le niveau 2
            int level2Index = random.nextInt(6);
            hexes.get(level2Index).setSystemType(SystemType.LEVEL2);

            // Sélectionne deux hexes différents pour le niveau 1
            int level1Index1, level1Index2;
            do {
                level1Index1 = random.nextInt(6);
            } while (level1Index1 == level2Index);  // Différent de l'hex niveau 2

            do {
                level1Index2 = random.nextInt(6);
            } while (level1Index2 == level2Index || level1Index2 == level1Index1);  // Différent des deux autres

            hexes.get(level1Index1).setSystemType(SystemType.LEVEL1);
            hexes.get(level1Index2).setSystemType(SystemType.LEVEL1);
        }
    }

    // Récupère la liste des hexagones
    public List<Hex> getHexes() {
        return hexes;
    }

    // Retourne l'hex à un index donné
    public Hex getHex(int id) {
        for (Hex hex : hexes) {
            if (hex.getId() == id) {
                return hex;
            }
        }
        throw new IllegalArgumentException("Hex non trouvé avec l'ID: " + id);
    }

    // Affiche les informations du secteur
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Sector (" + x + ", " + y + "): ");
        for (Hex hex : hexes) {
            sb.append("[ID: ").append(hex.getId())
                    .append(", Level: ").append(hex.getSystemType()).append("] ");
        }
        return sb.toString();
    }

    // Accesseurs pour les coordonnées
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isTriPrime() {
        return isTriPrime;
    }
}