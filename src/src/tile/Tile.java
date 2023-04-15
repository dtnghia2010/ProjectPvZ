package tile;

import object.Plant;

import java.awt.*;

public class Tile {
    private Rectangle bound;
    private Boolean occupied = false;
    private Plant plant;
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
    public void setPlant(Plant plant) {
        this.plant = plant;
    }
    public Plant getPlant() {
        return plant;
    }
}
