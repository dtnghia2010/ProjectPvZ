package Plant;

import component.Tile;
import manager.*;
import manager.TileManager;

import java.awt.*;
import java.util.Iterator;

public class Plant {
    private double plantHP;
    private int plantATK;
    private int sunCost;
    private int frameCountIdleLimit;
    private int frameCountAttackLimit;
    public int getFrameCountIdle() {
        return frameCountIdle;
    }

    public void setFrameCountIdle(int frameCountIdle) {
        this.frameCountIdle = frameCountIdle;
    }

    public int getFrameCDIdle() {
        return frameCDIdle;
    }

    public void setFrameCDIdle(int frameCDIdle) {
        this.frameCDIdle = frameCDIdle;
    }

    private int plantID;
    private int frameCountIdle = 1;
    private int frameCountAttack = 1;
    private int frameCountSun = 0;
    private int frameCDIdle = 0;
    private int frameCDAttack = 0;

    public int getFrameCountAttack() {
        return frameCountAttack;
    }
    public void sunCreatedBySunFlower(sunManager sunManager){
        frameCountSun++;
        if(frameCountSun == 600){
            sunManager.sunCreatedBySunFlower(this);
            frameCountSun = 0;
        }
    }
    public void updateFrameCountIdle(){
        frameCDIdle++;
        if(frameCDIdle%3 == 0){
            frameCountIdle++;
            if(frameCountIdle == frameCountIdleLimit){
                frameCountIdle = 0;
            }
        }
    }
    public void updateFrameCountAttack(){
        frameCDAttack++;
        if(frameCDAttack%4 == 0){
            frameCountAttack++;
            if (frameCountAttack == frameCountAttackLimit) {
                frameCountAttack = 0;
            }
        }
    }

    public void setFrameCountAttack(int frameCountAttack) {
        this.frameCountAttack = frameCountAttack;
    }
    private int explodeCD;
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

    public void setFrameCountAttackLimit(int frameCountAttackLimit) {
        this.frameCountAttackLimit = frameCountAttackLimit;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Plant(double plantHP, int plantID, int ATK, int frameCountIdleLimit){
        this.frameCountIdleLimit = frameCountIdleLimit;
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

    public int getPlantID() {
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