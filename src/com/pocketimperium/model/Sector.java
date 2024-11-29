package com.pocketimperium.model;
import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.List;

public class Sector {
    private int id;
    List<System> systemes;
    Position position;

    public Sector(int id) {
        this.id = id;
        this.systemes = new ArrayList<>();
    }

}
