package manager;

import event.Wave;
import scenes.Playing;

public class WaveManager {
    private Playing playing;
    private Wave[] waves;
    private int zombieSpawnTickLimit = 10;
    private int zombieSpawnTick = zombieSpawnTickLimit;
    private int curZom = 0, curWave = 0;

    public WaveManager(Playing playing) {
        this.playing = playing;
        waves = new Wave[3];
        initWaves();
    }

    private void initWaves() {
        waves[0] = new Wave(2, 2,1);
        waves[1] = new Wave(4,3,2);
        waves[2] = new Wave(6,4,3);
    }

    public void readyNewWave() {
        if(curWave < waves.length-1) {
            if(curZom > 2) {
                curWave++;
                curZom = 0;
            }
        }
    }

    public int getNextZombie() {
        zombieSpawnTick = 0;
        if (waves[curWave].amountType(curZom) > 0) {
            waves[curWave].recudeWave(curZom);
            if(waves[curWave].amountType(curZom) == 0) {
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
        System.out.println("curWave:" +  curWave);
        return waves[curWave].amountType(curZom) > 0;
    }

    public void updates() {
        if (zombieSpawnTick < zombieSpawnTickLimit) {
            zombieSpawnTick++;
        }
    }
}
