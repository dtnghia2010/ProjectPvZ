package component;

import manager.PlantManager;
import scenes.Playing;

import java.awt.*;
import java.util.List;

public class Tile {
    private final int ROWS = 5, COLS = 9;
    private int wTile = 70, hTile = 80;
    private Rectangle bound;
    private Boolean occupied = false;

    public int getwTile() {
        return wTile;
    }

    public int gethTile() {
        return hTile;
    }

    //    private Plant plants;
    private PlantManager plantManager;

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
//
//    public Plant getPlants() {
//        return plants;
//    }
//
//    public void setPlants(Plant plants) {
//        this.plants = plants;
//    }
//
    public List<PlantManager> getPlantManager(Playing playing) {
        return playing.getListOfPlant();
    }

    public void setPlantManager(PlantManager plantManager) {
        this.plantManager = plantManager;
    }
}

