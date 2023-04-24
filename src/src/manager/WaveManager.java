package manager;

import event.Wave;
import scenes.Playing;

import java.util.Timer;
import java.util.TimerTask;

public class WaveManager {
    private Playing playing;
    private Wave[] waves;
    private long ZOMBIE_SPAWN_SET = 2;
    private int zombieType;
    private int zombiesToSpawn;
    private Timer timer;;
    public WaveManager(Playing playing) {
        this.playing = playing;
        waves = new Wave[1];
        initWaves();
        timer = new Timer();
        start();
    }

    private void initWaves() {
        waves[0] = new Wave(10,2);
    }

    private Wave getDegreeWave(int degree) {
        if(degree >= waves.length) {
            System.err.println("Degree of wave is not available!");
            return null;
        }
        return waves[degree];
    }
    public void start() {
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
    }
}
