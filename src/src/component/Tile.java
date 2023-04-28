package component;

import java.awt.*;
import object.FakePlant;

public class Tile {
    private final int ROWS = 5, COLS = 9;
    private Rectangle bound;
    private Boolean occupied = false;
    private FakePlant fakePlant;
    public Tile(Rectangle bound) {
        this.bound = bound;
    }
    public Rectangle getBound() {
        return bound;
    }
    public void setOccupied(Boolean b) {
        occupied = b;
    }
    public void setFakePlant(FakePlant fakePlant) {
        this.fakePlant = fakePlant;
    }
    public FakePlant getFakePlant() {
        return fakePlant;
    }
    public boolean isOccupied() {
        return occupied;
    }
}

