package manager;

import events.Wave;
import scene.Playing;

import java.util.ArrayList;

public class WaveManager {
    private Playing playing;
    private boolean isPaused = false;
    private ArrayList<Wave> waves;
    private int enemySpawnTickLimit = 60*1;
    private int enemySpawnTick = enemySpawnTickLimit;
    public WaveManager(Playing playing) {
        this.playing = playing;
        waves = new ArrayList<Wave>();
    }
    public void update() {
        if(enemySpawnTick < enemySpawnTickLimit) {
            enemySpawnTick++;
        }
    }
    public boolean isTimeForNewZombie() {
        if(enemySpawnTick >= enemySpawnTickLimit) {
            enemySpawnTick = 0;
            return true;
        }
        return false;
    }
}
