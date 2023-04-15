package object;

import javax.swing.*;
import java.awt.*;

public class Zombie implements Character {
    private int hp, dmg, type;
    private float spd, x, y;

    public Zombie(int type, int x, int y) {
        this.type = type;
        this.x = x; this.y = y;
        this.spd = 1f;

    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpd() {
        return spd;
    }

    public int getHp() {
        return hp;
    }

    public int getDmg() {
        return dmg;
    }

    public int getType() {
        return type;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public void Hit() {

    }

    @Override
    public void getHit() {

    }
}
