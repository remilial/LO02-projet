package model.player;

import model.board.Sector;
import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected String name;
    protected int score;
    protected List<Sector> controlledSectors;
    protected int ships;

    // Constructeur
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.controlledSectors = new ArrayList<>();
        this.ships = 15;  // Vaisseaux de départ
    }

    // Méthode abstraite pour les tours de jeu (implémentée différemment pour IA et Humain)
    public abstract void takeTurn();

    // Ajout de points au score du joueur
    public void calculateScore() {
        int totalPoints = 0;
        for (Sector sector : controlledSectors) {
            totalPoints += sector.getSystemLevel();  // Supposons que Sector ait une méthode getSystemLevel()
        }
        this.score = totalPoints;
    }


    // Déploiement de vaisseaux dans un secteur
    public void deployShips(Sector sector, int shipCount) {
        if (sector == null) {
            throw new IllegalArgumentException("Le secteur fourni est invalide (null).");
        }
        if (shipCount <= 0) {
            throw new IllegalArgumentException("Le nombre de vaisseaux doit être supérieur à zéro.");
        }
        if (shipCount <= ships) {
            ships -= shipCount;
            sector.addShips(shipCount, this);
            System.out.println(name + " déploie " + shipCount + " vaisseaux dans " + sector);
        } else {
            throw new IllegalArgumentException("Pas assez de vaisseaux disponibles.");
        }
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

    // Ajoute un secteur contrôlé
    public void addControlledSector(Sector sector) {
        if (sector == null) {
            throw new IllegalArgumentException("Impossible d'ajouter un secteur null.");
        }
        if (!controlledSectors.contains(sector)) {
            controlledSectors.add(sector);
        } else {
            System.out.println("Ce secteur est déjà contrôlé par " + name);
        }
    }

    // Enlève un secteur de la liste controlé
    public boolean removeControlledSector(Sector sector) {
        if (controlledSectors.contains(sector)) {
            controlledSectors.remove(sector);
            System.out.println(sector + " n'est plus sous le contrôle de " + name);
            return true;
        } else {
            System.out.println(name + " ne contrôle pas ce secteur.");
            return false;
        }
    }



    @Override
    public String toString() {
        return "Joueur: " + name + ", Score: " + score + ", Vaisseaux: " + ships + ", Secteurs contrôlés: " + controlledSectors.size();
    }

}
