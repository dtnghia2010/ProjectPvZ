package manager;

import event.Wave;
import scenes.Playing;

import java.util.Timer;
import java.util.TimerTask;

public class WaveManager {
    private Playing playing;
    private Wave[] waves;
    private int zombieSpawnTickLimit = 60*1;
    private int zombieSpawnTick = zombieSpawnTickLimit;
    private boolean waveTickTimeOver = false;
    private int commonZom = 5, coneHeadZom = 2;
    public WaveManager(Playing playing) {
        this.playing = playing;
        waves = new Wave[1];
        initWaves();
    }

    private void initWaves() {
        waves[0] = new Wave(commonZom,coneHeadZom);
    }

    public boolean isWaveTimeOver() {
        return waveTickTimeOver;
    }

    public int getNextZombie() {
        zombieSpawnTick = 0;
        if(waves[0].type1() > 0) {
            waves[0].recudeWave();
            return 0;
        }
        return -1;
    }

    public boolean isTimeForNewZombie() {
        return zombieSpawnTick >= zombieSpawnTickLimit;
    }

    public boolean isThereMoreZombieInWave() {
        return waves[0].type1() > 0;
    }

    public void updates() {
        if(zombieSpawnTick < zombieSpawnTickLimit) {
            zombieSpawnTick++;
        }
    }


/*    private Wave getDegreeWave(int degree) {
        if(degree >= waves.length) {
            System.err.println("Degree of wave is not available!");
            return null;
        }
        return waves[degree];
    }*/
/*    public void start() {
        timer.schedule(new SpawnZombieTask(),0,1000*ZOMBIE_SPAWN_SET);
    }

    private class SpawnZombieTask extends TimerTask {
        private int zombiesSpawned = 0;
        @Override
        public void run() {
            zombiesToSpawn = waves[0].type1();
            zombieType = 0;
            System.out.println("a zom created");
            playing.getZombieManager().spawnZombie(zombieType);
            zombiesSpawned++;
            if (zombiesSpawned >= zombiesToSpawn) {
                timer.cancel();
            }
        }
    }*/
}
