package manager;

import object.FakePlant;
import scenes.Playing;
import zombie.Zombie;

import java.awt.*;

public class FakePlantManager {

    private FakePlant fakePlant;
    private Playing playing;
    public FakePlantManager(Playing playing) {
        this.playing = playing;
        fakePlant = new FakePlant();
    }

    public void zombieAtk() {

    }

    public FakePlant getPlant() {
        return fakePlant;
    }
}
