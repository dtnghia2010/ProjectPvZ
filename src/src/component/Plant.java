package component;

import Projectile.Shooter;
import manager.TileManager;

import java.awt.*;
import java.util.Iterator;

public class Plant implements Shooter {
    private double plantHP;
    private int plantATK;
    private  int plantID;
    private boolean isDangered = false;

    public boolean isDangered() {
        return isDangered;
    }

    public void setDangered(boolean dangered) {
        isDangered = dangered;
    }

    public int getPlantATK() {
        return plantATK;
    }

    public int getATK() {
        return plantATK;
    }

    public void setPlantATK(int plantATK) {
        this.plantATK = plantATK;
    }

    private int tileHold;
    private int x, y;
    private int width, height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Plant(double plantHP, int plantID, int ATK){
        this.plantHP = plantHP;
        this.plantID = plantID;
        this.plantATK = ATK;
    }
    public void setTileHold(int tileHold) {
        this.tileHold = tileHold;
    }

    public int getTileHold() {
        return tileHold;
    }

    public int getID() {
        return plantID;
    }

    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    public double getPlantHP() {
        return plantHP;
    }

    public void setPlantHP(double plantHP) {
        this.plantHP = plantHP;
    }
    public void removePlant(Plant plant, Iterator<Plant> iterator, TileManager tileManager){
        if(plant.getPlantHP() <= 0){
            for(Tile tile:tileManager.getTiles()){
                Rectangle r = tile.getBound();
                if(r.contains(plant.getX()+1,plant.getY()+1)){
                    tile.setOccupied(false);
                    tile.setPlanted(false);
                }
            }
            iterator.remove();
        }
    }
}