package model.board;

import java.io.Serializable;

public enum SystemType implements Serializable {
    NONE,   // 0 - Aucun système
    LEVEL1, // 1 - Niveau 1
    LEVEL2, // 2 - Niveau 2
    LEVEL3  // 3 - Niveau 3 (Tri-Prime)
}