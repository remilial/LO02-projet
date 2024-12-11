import model.Player;

import java.util.HashMap;
import java.util.Map;

public class Hex {
    private SystemType systemType;
    private Map<Player, Integer> ships; // Combien de vaisseaux par joueur

    public Hex(SystemType systemType) {
        this.systemType = systemType;
        this.ships = new HashMap<>();
    }

    public SystemType getSystemType() {
        return systemType;
    }

    public int getShipCount(Player p) {
        return ships.getOrDefault(p, 0);
    }

    public void addShips(Player p, int count) {
        ships.put(p, getShipCount(p) + count);
    }

    public void removeShips(Player p, int count) {
        int current = getShipCount(p);
        int newCount = current - count;
        if (newCount <= 0) {
            ships.remove(p);
        } else {
            ships.put(p, newCount);
        }
    }

    public Player getController() {
        // Contrôle: hex système + au moins 1 vaisseau
        if (systemType == SystemType.NONE) return null;
        // Si un player a des vaisseaux, il contrôle
        for (Map.Entry<Player,Integer> e : ships.entrySet()) {
            if (e.getValue() > 0) {
                return e.getKey();
            }
        }
        return null; // Personne ne contrôle si aucune armée
    }
}
