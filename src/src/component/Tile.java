package component;

import java.awt.*;

public class Tile {
    private final int ROWS = 5, COLS = 9;
    private Rectangle bound;
    private Boolean occupied = false;
    public Tile(Rectangle bound) {
        this.bound = bound;
    }
    public Rectangle getBound() {
        return bound;
    }
    public void setOccupied(Boolean b) {
        occupied = b;
    }
    public boolean isOccupied() {
        return occupied;
    }
}

