package zombie;

import scenes.Playing;

public class Zombie {
    private Playing playing;
    private int hp, dmg, spd, type;
    private boolean isAlived = true;
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


}
