package model.player;

import model.board.Sector;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite Player pour les joueurs humains et IA.
 * Gère les vaisseaux, le score et les secteurs contrôlés.
 */
public abstract class Player implements Serializable {
    protected String name;
    protected int score;
    protected List<Sector> controlledSectors;
    protected int ships;

    // Nombre initial de vaisseaux
    private static final int INITIAL_SHIPS = 15;

    /**
     * Constructeur de Player
     * @param name Nom du joueur
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.controlledSectors = new ArrayList<>();
        this.ships = INITIAL_SHIPS;
    }

    // Méthode abstraite pour que les joueurs prennent leur tour
    public abstract void takeTurn();

    // Déploie des vaisseaux dans un secteur donné
    public void deployShips(Sector sector, int shipCount) {
        if (sector == null || shipCount <= 0) {
            throw new IllegalArgumentException("Secteur invalide ou nombre de vaisseaux incorrect.");
        }
        if (shipCount <= ships) {
            ships -= shipCount;
            sector.addShips(shipCount, this);
            System.out.println(name + " déploie " + shipCount + " vaisseaux.");
        } else {
            System.out.println("Pas assez de vaisseaux disponibles.");
        }
    }

    // Ajoute des points au score du joueur
    public void addPoints(int points) {
        this.score += points;
    }

    // Perte de vaisseaux après une bataille ou une phase exploit
    public void loseShips(int count) {
        ships = Math.max(0, ships - count);
    }

    // Capture d'un secteur
    public void captureSector(Sector sector) {
        if (!controlledSectors.contains(sector)) {
            controlledSectors.add(sector);
            sector.setController(this);
            System.out.println(name + " prend le contrôle d'un secteur.");
        }
    }

    // Relâchement d'un secteur
    public void releaseSector(Sector sector) {
        controlledSectors.remove(sector);
        sector.setController(null);
        System.out.println(name + " relâche un secteur.");
    }

    // Calcule le score total basé sur les secteurs contrôlés
    public void calculateScore() {
        int totalScore = 0;
        for (Sector sector : controlledSectors) {
            totalScore += sector.getSystemLevel().getValue();
        }
        this.score = totalScore;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public List<Sector> getControlledSectors() {
        return controlledSectors;
    }

    public int getShips() {
        return ships;
    }

    @Override
    public String toString() {
        return "Joueur : " + name + ", Score : " + score + ", Vaisseaux : " + ships +
                ", Secteurs contrôlés : " + controlledSectors.size();
    }
}