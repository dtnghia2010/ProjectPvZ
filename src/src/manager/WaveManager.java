package manager;

import event.Wave;
import scenes.Playing;

import java.util.Timer;
import java.util.TimerTask;

public class WaveManager {
    private Playing playing;
    private Wave[] waves;
    private long ZOMBIE_SPAWN_SET = 2;
    private int zombieType = 0;
    private int zombiesToSpawn = 2;
    private Timer timer;;
    public WaveManager(Playing playing) {
        this.playing = playing;
        waves = new Wave[1];
        initWaves();
        timer = new Timer();
        start();
    }

    private void initWaves() {
        waves[0] = new Wave(2,2);
    }

    private Wave getDegreeWave(int degree) {
        if(degree >= waves.length) {
            System.err.println("Degree of wave is not available!");
            return null;
        }
        return waves[degree];
    }
/*    public void updates() {
        if(waves[0].type1() >= 1) {
            double timePerUpdate = 1000000000.0 / ZOMBIE_SPAWN_SET;
            long lastUpdate = System.nanoTime();
            long now;
            now = System.nanoTime();
            while(true) {
                if (now - lastUpdate >= timePerUpdate) {
                    lastUpdate = now;
                    System.out.println("1 zombie created");
                    playing.getZombieManager().spawnZombie(0);
                    waves[0].recudeWave();
                }
                if(waves[0].type1() < 1) {
                    break;
                }
            }
        }
    }*/
    public void start() {
        timer.schedule(new SpawnZombieTask(),2000,2000);
    }

    private class SpawnZombieTask extends TimerTask {
        private int zombiesSpawned = 0;
        @Override
        public void run() {
            System.out.println("a zom created");
            playing.getZombieManager().spawnZombie(zombieType);
            zombiesSpawned++;
            if (zombiesSpawned >= zombiesToSpawn) {
                timer.cancel();
            }
        }
    }
}
