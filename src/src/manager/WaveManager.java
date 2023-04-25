package manager;

import event.Wave;
import scenes.Playing;

public class WaveManager {
    private Playing playing;
    private Wave[] waves;
    private int zombieSpawnTickLimit = 60 * 3;
    private int zombieSpawnTick = zombieSpawnTickLimit;
    private boolean waveTickTimeOver = false;
    private int curZom = 0;

    public WaveManager(Playing playing) {
        this.playing = playing;
        waves = new Wave[1];
        initWaves();
    }

    private void initWaves() {
        waves[0] = new Wave(5, 3);
    }

    public boolean isWaveTimeOver() {
        return waveTickTimeOver;
    }

    public int getNextZombie() {
        zombieSpawnTick = 0;
        if (waves[0].amountType(curZom) > 0) {
            waves[0].recudeWave(curZom);
            if(waves[0].amountType(curZom) == 0) {
                curZom++;
                return curZom-1;
            }
            return curZom;
        }
        return -1;
    }

    public boolean isTimeForNewZombie() {
        return zombieSpawnTick >= zombieSpawnTickLimit;
    }

    public boolean isThereMoreZombieInWave() {
        return waves[0].amountType(curZom) > 0;
    }

    public void updates() {
        if (zombieSpawnTick < zombieSpawnTickLimit) {
            zombieSpawnTick++;
        }
    }
}
