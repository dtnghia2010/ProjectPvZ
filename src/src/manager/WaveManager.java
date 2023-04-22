package manager;

import event.Wave;
import scenes.Playing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaveManager {
    private int size;
    private Playing playing;
    private Wave[] waves;
    private Timer timer;
    public WaveManager(Playing playing) {
        this.playing = playing;
        waves = new Wave[1];
        initWaves();
        zombieCreated();
        timer.start();
    }

    private void initWaves() {
        waves[0] = new Wave(2,2);
        size = waves[0].type1();
    }

    private Wave getDegreeWave(int degree) {
        if(degree >= waves.length) {
            System.err.println("Degree of wave is not available!");
            return null;
        }
        return waves[degree];
    }
    public void zombieCreated(){
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(waves[0].type1() > 0) {
                    System.out.println("1");
                    playing.getZombieManager().spawnZombie(1);
                    waves[0].recudeWave();
                }
            }
        });
    }
    public void updates() {
        if(waves[0].type1() > 1) {
            timer.stop();
        }
    }
}
