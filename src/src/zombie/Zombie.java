package zombie;

import Object.FakePlant;

public class Zombie {
    private int hp, dmg, type;
    private float spd = 10f;
    private int x, y;
    private boolean isAlived = true;
    public Zombie(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
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
    public void move() {
        x -= spd;
    }
    public void bite(FakePlant fakePlant) {

    }
    public void hurt() {

    }

}
