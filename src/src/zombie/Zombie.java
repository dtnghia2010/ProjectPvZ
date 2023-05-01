package zombie;

import java.awt.*;

public class Zombie {
    private int hp, dmg, type;
    private final int width = 80, height = 140;
    private float spd = 2f;

    public boolean isSlowed() {
        return isSlowed;
    }

    public void setSlowed(boolean slowed) {
        isSlowed = slowed;
    }

    public void setSpd(float spd) {
        this.spd = spd;
    }

    private int x, y;
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
    public Zombie(int x, int y, int type) {
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
                dmg = 9;
                break;
            case 1:
                hp = 130;
                dmg = 9;
                break;
            case 2:
                hp = 90;
                dmg = 20;
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

    public boolean isAlived() {
        return isAlived;
    }
    public int X() {
        return x;
    }

    public int Y() {
        return y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void move() {
        x -= spd;
    }
//    public void bite(FakePlant fakePlant) {
//
//    }
    public void hurt() {

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
