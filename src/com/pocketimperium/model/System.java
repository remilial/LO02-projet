package com.pocketimperium.model;

import java.util.ArrayList;
import java.util.List;

public class System {
    private int niveau;
    private List<Ship> ships;
    public System( int niveau ) {
        this.niveau = niveau;
        this.ships = new ArrayList<>();
    }
}
