package manager;

import event.Wave;
import scenes.Playing;

public class WaveManager {
    private Playing playing;
    private Wave[] waves;
    public WaveManager(Playing playing) {
        this.playing = playing;
        waves = new Wave[1];
        initWaves();
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

    public void updates() {
        if(waves[0].type1() > 1) {
            int size = waves[0].type1();
            for(int i = 0; i < size; i++) {
                System.out.println("zombies created");
                playing.getZombieManager().spawnZombie(1);
            }
        }
    }
}
