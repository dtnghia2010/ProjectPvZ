package zombie;

import scenes.Playing;

public class Zombie {
    private Playing playing;
    private int hp, dmg, spd = 1, type;
    private boolean isAlived = true;

    private int x=900;
    private int y=50;

    public Zombie(int x, int y, int type) {
        this.x=x;
        this.y=y;
        this.type = type;
    }
    public Zombie(Playing playing) {
        //for safety in the future
        this.playing = playing;
    }

    public void dead() {
        isAlived = false;
    }
    public Playing getPlaying() {
        return playing;
    }

    public int getHp() {
        return hp;
    }

    public int getDmg() {
        return dmg;
    }

    public int getSpd() {
        return spd;
    }

    public int getType() {
        return type;
    }

    public boolean isAlived() {
        return isAlived;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

}
