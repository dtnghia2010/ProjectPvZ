package zombie;

import component.Plant;
import manager.ZombieManager;

import java.awt.*;
import java.util.Iterator;

public class Zombie {
    private int hp, dmg, type;
    private final int width = 55, height = 110;
    private float spd = 0.8f;
    private boolean isCollided = false;

    public boolean isSlowed() {
        return isSlowed;
    }

    public void setSlowed(boolean slowed) {
        isSlowed = slowed;
    }

    public void setSpd(float spd) {
        this.spd = spd;
    }

    private double x, y;
    private boolean isAlived = true;
    private boolean isDead = false;
    private boolean isSlowed = false;
    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    private Rectangle bound;
    public Zombie(double x, double y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bound = new Rectangle(new Dimension(width, height));
        setStatus(this.type);
    }

    private void setStatus(int type) {
        switch (type) {
            case 0:
                hp = 70;
                dmg = 30;
                break;
            case 1:
                hp = 130;
                dmg = 30;
                break;
            case 2:
                hp = 90;
                dmg = 80;
                break;
        }
    }

    public void dead() {
        isAlived = false;
    }

    public int getHp() {
        return hp;
    }

    public int getDmg() {
        return dmg;
    }

    public float getSpd() {
        return spd;
    }

    public int getType() {
        return type;
    }

    public void setCollided(boolean collided) {
        isCollided = collided;
    }

    public boolean isAlived() {
        return isAlived;
    }
    public double X() {
        return x;
    }

    public double Y() {
        return y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Rectangle getBound() {
        return bound;
    }

    public void move() {
        if(!isCollided){
            x -= spd;
        }
    }
//    public void bite(FakePlant fakePlant) {
//
//    }
    public void hurt() {

    }
    public void attackPlant(Plant plant){
        plant.setPlantHP(plant.getPlantHP() - dmg);
    }
    public void defeatPlant(Plant plant){
        if(plant.getPlantHP() <= 0){
            isCollided = false;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
