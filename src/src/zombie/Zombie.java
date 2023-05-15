package zombie;

import Audio.Audio;
import Plant.Plant;

import java.awt.*;

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
    private int frameCountMove = 0;
    private double frameCDMove = 0;
    private int frameCountMoveLimit;
    private int frameCountEat = 0;
    private int frameCDEat = 0;
    private int frameCountEatLimit;
    private boolean isAlived = true;
    private boolean isDead = false;
    private Rectangle bound;
    private boolean isSlowed = false;

    public boolean isDead() {
        return isDead;
    }
    public void setDead(boolean dead) {
        isDead = dead;
    }

    public Zombie(double x, double y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bound = new Rectangle((int)this.x+20,(int)this.y,width-20,height);
        setStatus(this.type);
        if(this.type == 0){
            frameCountMoveLimit = 51;
            frameCountEatLimit = 23;
        } else if (this.type == 1) {
            frameCountMoveLimit = 65;
        }
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

    public boolean isCollided() {
        return isCollided;
    }

    public int getFrameCountEat() {
        return frameCountEat;
    }

    public void updateFrameCountMove(){
        if(!isCollided){
            frameCDMove++;
            if(frameCDMove%0.5 == 0){
                frameCountMove++;
                if(type == 0){
                    if(frameCountMove > 5 && frameCountMove <47)
                        bound.x--;
                    if(frameCountMove == frameCountMoveLimit-1){
                        x = x-frameCountMove-1+8;
                        frameCountMove = 0;
                    }
                } else if (type == 1){
                    if(frameCountMove > 15 && frameCountMove <60)
                        bound.x--;
                    if(frameCountMove == frameCountMoveLimit-1){
                        x = x-frameCountMove-1+18;
                        frameCountMove = 0;
                    }
                }
            }
        }
    }
    public void updateFrameCountEat(){
        if(isCollided){
            frameCDEat++;
            if(frameCDEat%3 == 0){
                frameCountEat++;
            }
            if(frameCountEat == frameCountEatLimit){
                frameCountEat = 0;
            }
        } else {
            frameCountEat = 0;
        }
    }


    public int getFrameCountMove() {
        return frameCountMove;
    }

    public void setFrameCountMove(int frameCountMove) {
        this.frameCountMove = frameCountMove;
    }


    public void setFrameCDMove(int frameCDMove) {
        this.frameCDMove = frameCDMove;
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
            Audio.plantDeath();
            isCollided = false;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isWalking() {
        return !isCollided;
    }

}
