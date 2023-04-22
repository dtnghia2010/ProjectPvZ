package zombie;

import scenes.Playing;

public class Zombie {
    private int hp, dmg, type;
    private float spd = 0.1f;
    private int x, y;
    private boolean isAlived = true;
    public Zombie(int x, int y, int type) {
        //for safety in the future
        this.x = x;
        this.y = y;
        this.type = type;
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
    public void move() {
        x -= spd;
    }

}
